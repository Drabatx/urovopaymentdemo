package com.test.urovopaymentapp.domain.repository

import android.content.Context
import android.device.SEManager
import android.media.AudioManager
import android.media.SoundPool
import android.os.Bundle
import android.os.IInputActionListener
import android.os.RemoteException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lib.card.constant.CardTypeConstant
import com.test.urovopaymentapp.R
import com.test.urovopaymentapp.data.local.preferences.MerchantConfig
import com.test.urovopaymentapp.data.local.preferences.PreferencesConfigs
import com.test.urovopaymentapp.data.model.pos2.constants.PosTransType
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.di.PaymentValues
import com.test.urovopaymentapp.domain.model.EmvConstants.AMOUNT
import com.test.urovopaymentapp.domain.model.EmvConstants.CARD_TYPE_IC
import com.test.urovopaymentapp.domain.model.EmvConstants.CHECK_CARD_MODE
import com.test.urovopaymentapp.domain.model.EmvConstants.getEMVInstance
import com.test.urovopaymentapp.domain.model.TradingCardResponse
import com.test.urovopaymentapp.domain.model.request.login.Authentication
import com.test.urovopaymentapp.domain.model.request.login.AuthenticationData
import com.test.urovopaymentapp.domain.model.request.login.BackendUserRequest
import com.test.urovopaymentapp.domain.model.request.login.GrantingTicketRequest
import com.test.urovopaymentapp.domain.model.request_pago_ci.ChargingCurrency
import com.test.urovopaymentapp.domain.model.request_pago_ci.Contract
import com.test.urovopaymentapp.domain.model.request_pago_ci.OperationType
import com.test.urovopaymentapp.domain.model.request_pago_ci.RequestPagoCi
import com.test.urovopaymentapp.domain.model.request_pago_ci.Sender
import com.test.urovopaymentapp.domain.model.request_pago_ci.TaxableCash
import com.test.urovopaymentapp.utils.Result
import com.test.urovopaymentapp.utils.exception.ConnectServerException
import com.test.urovopaymentapp.utils.exception.UrovoChecCardResultException
import com.test.urovopaymentapp.utils.exception.UrovoMessageException
import com.test.urovopaymentapp.utils.exception.UrovoSelectApplicationException
import com.test.urovopaymentapp.utils.exception.UrovoTransactionException
import com.urovo.i9000s.api.emv.ContantPara
import com.urovo.i9000s.api.emv.ContantPara.CheckCardMode
import com.urovo.i9000s.api.emv.ContantPara.CheckCardResult
import com.urovo.i9000s.api.emv.ContantPara.IssuerScriptResult
import com.urovo.i9000s.api.emv.ContantPara.NfcErrMessageID
import com.urovo.i9000s.api.emv.ContantPara.NfcTipMessageID
import com.urovo.i9000s.api.emv.ContantPara.NfcTransResult
import com.urovo.i9000s.api.emv.ContantPara.PinEntrySource
import com.urovo.i9000s.api.emv.ContantPara.TransactionResult
import com.urovo.i9000s.api.emv.EmvListener
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.i9000s.api.emv.Funs
import com.urovo.sdk.beeper.BeeperImpl
import com.urovo.sdk.insertcard.InsertCardHandlerImpl
import com.urovo.sdk.led.LEDDriverImpl
import com.urovo.sdk.magcard.MagCardReaderImpl
import com.urovo.sdk.pinpad.PinPadProviderImpl
import com.urovo.sdk.pinpad.listener.PinInputListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat
import java.util.Hashtable
import java.util.Locale
import javax.inject.Inject


class MyEmvListener @Inject constructor(
    private val mKernelApi: EmvNfcKernelApi,
    private val context: Context,
    private val tradingRepository: TradingRepositoryImpl
) : EmvListener {
    private val TAG = "MyEmvListener"

    private val _result = MutableStateFlow<Result<PosInputDatas>>(Result.Loading())
    val result: StateFlow<Result<PosInputDatas>> get() = _result

    private val _posInputDatas: MutableLiveData<PosInputDatas> = MutableLiveData()
    val posInputDatas: LiveData<PosInputDatas> get() = _posInputDatas

    private var transType = 7
    private var payCardType = CARD_TYPE_IC //0:mag，1：ic/rfid

    private val pinpadlistener = AdminInputListener()

    val keyId: Int = 10

    //    var mSTIOS8583: StIso8583? = null
    lateinit var iMagCardReader: MagCardReaderImpl
    lateinit var iInsertCardReader: InsertCardHandlerImpl
    lateinit var pinpad: PinPadProviderImpl
    lateinit var iBeeper: BeeperImpl
    lateinit var iLed: LEDDriverImpl
    lateinit var soundPool: SoundPool
    var errorsoundid = 0
    var scansoundid = 0

    fun initKernel(posData: PosInputDatas) {
        _posInputDatas.postValue(posData)
        mKernelApi.setListener(this)
        mKernelApi.setContext(context)
        mKernelApi.LogOutEnable(1)
        soundPool = SoundPool(1, AudioManager.STREAM_NOTIFICATION, 100)

        errorsoundid = soundPool.load(context, R.raw.error, 1)
        scansoundid = soundPool.load(context, R.raw.success, 1)

        transType = PosTransType.getEMVTransType(_posInputDatas.value?.iTransNo ?: 7)

        payCardType = CARD_TYPE_IC //0:mag，1：ic/rfid
        iMagCardReader = MagCardReaderImpl.getInstance()
        iInsertCardReader = InsertCardHandlerImpl.getInstance()
        pinpad = PinPadProviderImpl.getInstance()
        iBeeper = BeeperImpl.getInstance()
        iLed = LEDDriverImpl.getInstance()

        startKernel(CheckCardMode.INSERT_OR_TAP)
    }

    fun startKernel(checkCardMode: CheckCardMode) {
        Thread {
            try {
                val data = getEMVInstance()
                data[CHECK_CARD_MODE] = checkCardMode
                data[AMOUNT] = posInputDatas.value?.amt ?: "0"
                mKernelApi.startKernel(data)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun loginToServer(tlvData: String) {
        runBlocking {
            posInputDatas.let {
                val request = grantingTicketRequest()
                tradingRepository.loginToProcessPayments(request).collect { response ->
                    when (response) {
                        is Result.Success -> {
                            processPayment(tlvData = tlvData, response.data)
                        }

                        is Result.Error -> {
                            _result.value =
                                Result.Error(ConnectServerException(context.getString(R.string.err_login_server)))
                        }

                        is Result.Loading -> {
                            _result.value =
                                Result.Loading(context.getString(R.string.login_server))
                        }
                    }
                }
            }
        }
    }

    private fun grantingTicketRequest(): GrantingTicketRequest {
        val autenticationData = AuthenticationData.Builder()
            .setIdAuthenticationData(MerchantConfig.idAuthenticationData)
            .setAuthenticationData(listOf(MerchantConfig.authenticationData))
            .build()
        val authentication = Authentication.Builder()
            .setUserID(MerchantConfig.authUserId)
            .setConsumerID(MerchantConfig.authConsumerId)
            .setAuthenticationData(listOf(autenticationData))
            .build()
        val backendUserRequest = BackendUserRequest.Builder()
            .setUserId(MerchantConfig.backendUserId)
            .setAccessCode(MerchantConfig.backendAccessCode)
            .setDialogId(MerchantConfig.backendDialogId)
            .build()

        val request = GrantingTicketRequest.Builder()
            .setAuthentication(authentication)
            .setBackendUserRequest(backendUserRequest)
            .build()
        return request
    }

    fun processPayment(tlvData: String, data: TradingCardResponse) {
        runBlocking {
            posInputDatas.value.let {
                val taxableCash =
                    posInputDatas.value?.amt?.let { amnt -> TaxableCash(amount = amnt) }
                        ?: TaxableCash(amount = "0.0")

                val contract = Contract.Builder()
                    .setChannelContract(PaymentValues.channelContract)
                    .setContractNumber("") //Número de contrato APIs Empresariales a 8 posiciones con ceros a la izquierda
                    .setChargingCurrency(ChargingCurrency(code = PaymentValues.chargingCurrency))
                    .build()

                val sender = posInputDatas.value?.let { it1 ->
                    Sender.Builder()
                        .setOperationType(OperationType(id = PaymentValues.operationTypeId))
                        .setContract(contract)
                        .setServiceNumber(PaymentValues.serviceNumber)
                        .setTerminalId(PaymentValues.terminalId)
                        .setChargeAccount(it1.pan)//Cuenta de cargo dada de alta como cuenta propia en el contrato de donde saldrán los fondos
                        .build()
                } ?: Sender.Builder().build()

                val request: RequestPagoCi = RequestPagoCi.Builder()
                    .setSender(sender)
                    .setTaxableCash(taxableCash)// Monto de la operación N(13.2) 13 enteros y dos decimales con punto decimal
                    .setNumberAgreement("")//Número de convenio CIE a 9posiciones con ceros a la izaquierda
                    .setReference("")//Refeencia CIE A(20)
                    .setConcept("")//Concepto CIE A(30), el campo no se require para los convenios que no llevan concepto
                    .build()
                val tsec = PreferencesConfigs.tsecHeader
                tradingRepository.processPaymentCI(tsec = tsec, request = request).collect {
                    when (it) {
                        is Result.Success -> {
                            mKernelApi.sendOnlineProcessResult(true, tlvData)
                        }

                        is Result.Error -> {
                            _result.value = Result.Error(
                                ConnectServerException(
                                    context.getString(
                                        R.string.err_process_payment
                                    )
                                )
                            )
                        }

                        is Result.Loading -> {
                            _result.value =
                                Result.Loading(context.getString(R.string.processing_payment))
                        }
                    }
                }

            }
        }
    }

    override fun onRequestSetAmount() {
        _result.value = Result.Loading(context.getString(R.string.loading_amount))
    }

    override fun onReturnCheckCardResult(
        checkCardResult: CheckCardResult,
        hashtable: Hashtable<String, String>
    ) {
        if (checkCardResult == CheckCardResult.INSERTED_CARD) {
            _result.value = Result.Loading(context.getString(R.string.read_card_ready))
        } else if (checkCardResult == CheckCardResult.MSR) {
            val stripStr = hashtable["StripInfo"]!!.uppercase(Locale.getDefault())
            val cardNo = hashtable["CardNo"]


            val hstr1 = Funs.TLV_Find("D1", stripStr)
            val hstr2 = Funs.TLV_Find("D2", stripStr)
            val hstr3 = Funs.TLV_Find("D3", stripStr)
            val track3: String
            val track1 = if (hstr1 == "") {
                ""
            } else {
                String(Funs.StrToHexByte(hstr1))
            }

            val track2 = if (hstr2 == "") {
                ""
            } else {
                String(Funs.StrToHexByte(hstr2))
            }
            if (hstr3 != "") {
                track3 = String(Funs.StrToHexByte(hstr3))
                _posInputDatas.postValue(_posInputDatas.value?.update(track3 = track3))
            }
            var index = track2.indexOf("=")
            if (index != -1) {
                val PAN = track2.substring(0, index) //Obtener el número de tarjeta
                index++
                val EXPIRED_DATE = track2.substring(index, index + 4)
                val SERVICE_CODE = track2.substring(index + 4, index + 4 + 3)

                _posInputDatas.postValue(
                    _posInputDatas.value?.update(
                        pan = PAN,
                        track2 = track2,
                        szExpDate = EXPIRED_DATE
                    )
                )

            }
            //TODO Procesar Pago
//            _posInputDatas.update(swipedMode = CardTypeConstant.MSR)
//            _result.value = Result.Error(UrovoMessageException())
//            _result.value = UrovoResult.Message(EmvReason.MESSAGE_MSR)
        } else if (checkCardResult == CheckCardResult.NEED_FALLBACK) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_need_fallback)))
        } else if (checkCardResult == CheckCardResult.BAD_SWIPE) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_bad_swipe)))
        } else if (checkCardResult == CheckCardResult.NOT_ICC) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_not_icc)))
        } else if (checkCardResult == CheckCardResult.TIMEOUT) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_timeout)))

        } else if (checkCardResult == CheckCardResult.CANCEL) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_cancel_operation)))
        } else if (checkCardResult == CheckCardResult.DEVICE_BUSY) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_device_busy)))
        } else if (checkCardResult == CheckCardResult.USE_ICC_CARD) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_not_icc)))
        } else if (checkCardResult == CheckCardResult.MULT_CARD) {
            _result.value =
                Result.Error(UrovoChecCardResultException(context.getString(R.string.error_multi_card)))
        }
    }

    override fun onRequestSelectApplication(arrayList: ArrayList<String>) {
        Log.i(TAG, "MainActivity  onRequestSelectApplication")
        _result.value = Result.Loading("onRequestSelectApplication")

        var i = 0
        while (i < arrayList.size) {
            Log.d(TAG, "app name " + i + " : " + arrayList[i])
            i++
        }
        if (i == 1) {
            mKernelApi.selectApplication(0)
        } else {
            _result.value = Result.Error(
                UrovoSelectApplicationException(message = arrayList.joinToString(", "))
            )
        }
    }


    //if contact online pin verify, will callback
    override fun onRequestPinEntry(pinEntrySource: PinEntrySource) {
        Log.i(TAG, "MainActivity  onRequestPinEntry request online pin")
//        if (pinEntrySource == PinEntrySource.KEYPAD) {
//            _posInputDatas.update(pan = GetCardNo())
//            if (pinEntrySource == PinEntrySource.KEYPAD) {
//                _posInputDatas.update(pan = GetCardNo())
//                emv_proc_onlinePin()
//            }
//        }
    }

    override fun onRequestOfflinePinEntry(pinEntrySource: PinEntrySource, PinTryCount: Int) {
        Log.i(TAG, "MainActivity  onRequestOfflinePinEntry")
    }

    override fun onRequestConfirmCardno() {
        Log.d(TAG, "CardNo:" + GetCardNo())
        _result.value = Result.Loading("Obteniendo datos de tarjeta")

        _posInputDatas.postValue(_posInputDatas.value?.update(swipedMode = CardTypeConstant.IC))
        iBeeper.startBeep(1, 200)
        try {
            iLed.turnOn(1)
            iLed.turnOn(2)
            iLed.turnOn(3)
            iLed.turnOn(4)
        } catch (e: RemoteException) {
            e.printStackTrace()
        }

        val pan = GetCardNo()
        _posInputDatas.postValue(
            _posInputDatas.value?.update(
                pan = pan,
                track2 = mKernelApi.getValByTag(0x57),
                szCardSeqNo = mKernelApi.getValByTag(0x5F34),
                szExpDate = mKernelApi.getValByTag(0x5F24)
            )
        )

        mKernelApi.sendConfirmCardnoResult(true)
    }

    override fun onRequestFinalConfirm() {
        mKernelApi.sendFinalConfirmResult(true)
    }

    override fun onRequestOnlineProcess(cardTlvData: String, dataKsn: String?) {
        //send s to sever
        _posInputDatas.postValue(
            _posInputDatas.value?.update(
                pan = GetCardNo(),
                track2 = mKernelApi.getValByTag(0x57),
                szCardSeqNo = mKernelApi.getValByTag(0x5F34),
                szExpDate = mKernelApi.getValByTag(0x5F24),
                file55 = cardTlvData
            )
        )

        val TVR = mKernelApi.getValByTag(0x95)
        val PSN = mKernelApi.getValByTag(0x5F34)

        Log.i(TAG, "onRequestOnlineProcess: tlvData:$cardTlvData")
        _result.value = Result.Loading("Procesando pago")
        loginToServer(tlvData = cardTlvData)
    }


    override fun onReturnBatchData(cardTlvData: String) {
        _posInputDatas.postValue(
            _posInputDatas.value?.update(
                pan = GetCardNo(),
                track2 = mKernelApi.getValByTag(0x57),
                szCardSeqNo = mKernelApi.getValByTag(0x5F34),
                szExpDate = mKernelApi.getValByTag(0x5F24),
                file55 = cardTlvData
            )
        )
    }


    override fun onReturnTransactionResult(transactionResult: TransactionResult) {
        Log.i(TAG, "onReturnTransactionResult: ")
        val TVR = mKernelApi.getValByTag(0x95)
        Log.i(TAG, "  onReturnTransactionResult TVR:$TVR")
        val TSI = mKernelApi.getValByTag(0x9B)
        Log.i(TAG, "  onReturnTransactionResult TSI:$TSI")
        val IAD = mKernelApi.getValByTag(0x9F10)
        Log.i(TAG, "  onReturnTransactionResult IAD:$IAD")
        val AC = mKernelApi.getValByTag(0x9F26)
        Log.i(TAG, "  onReturnTransactionResult AC:$AC")

        if (transactionResult == TransactionResult.ONLINE_APPROVAL) {
            soundPool.play(R.raw.success, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
//            _posInputDatas.update(
//                pzNumber = mSTIOS8583?.field11,
//                stIso8583 = mSTIOS8583
//            )
//
//            _result.postValue(UrovoResult.Success(posInputDatas.value!!))
        } else if (transactionResult == TransactionResult.ONLINE_DECLINED) {
            soundPool.play(R.raw.success, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
//            _posInputDatas.update(
//                pzNumber = mSTIOS8583?.field11,
//                stIso8583 = mSTIOS8583
//            )


//            _result.value = Result.Success(posInputDatas.value!!))

//            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
//            mKernelApi.abortKernel()
//            _result.postValue(UrovoResult.Error(Throwable("Transacción rechazada por el banco.")))
        } else if (transactionResult == TransactionResult.OFFLINE_DECLINED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_offline_declined)))
        } else if (transactionResult == TransactionResult.ICC_CARD_REMOVED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_icc_card_removed)))
        } else if (transactionResult == TransactionResult.TERMINATED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_operation_terminated)))
        } else if (transactionResult == TransactionResult.CANCELED_OR_TIMEOUT) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_canceled_timeout)))
        } else if (transactionResult == TransactionResult.CANCELED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_canceled)))
        } else if (transactionResult == TransactionResult.CARD_BLOCKED_APP_FAIL) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_card_blocked)))
        } else if (transactionResult == TransactionResult.APPLICATION_BLOCKED_APP_FAIL) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_app_blocked)))
        } else if (transactionResult == TransactionResult.INVALID_ICC_DATA) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_invalid_icc_data)))
        } else if (transactionResult == TransactionResult.NO_EMV_APPS) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.value =
                Result.Error(UrovoTransactionException(context.getString(R.string.err_no_emv_apps)))
        }
    }

    override fun onRequestDisplayText(displayText: ContantPara.DisplayText) {
        Log.i(TAG, "MainActivity  onRequestDisplayText")
        when (displayText) {
            ContantPara.DisplayText.USE_MAG_STRIPE -> {
                _result.value =
                    Result.Error(UrovoMessageException(context.getString(R.string.use_mag_stripe)))
            }

            ContantPara.DisplayText.APPROVED_PLEASE_SIGN -> {
                _result.value =
                    Result.Error(UrovoMessageException(context.getString(R.string.approved_please_sign)))

            }
        }

    }


    override fun onRequestOfflinePINVerify(
        pinEntrySource: PinEntrySource,
        pinEntryType: Int,
        bundle: Bundle
    ) {
        if (pinEntrySource == PinEntrySource.KEYPAD) { //use in os 8.0
            //pinEntryType 0-offline plain pin ,1-offline encrypt pin
            val pinTryTimes: Int = mKernelApi.getOfflinePinTryTimes()
            bundle.putInt("PinTryTimes", pinTryTimes)
            bundle.putBoolean("isFirstTime", true)
            proc_offlinePin(pinEntryType, pinTryTimes == 1, bundle)
        }
    }

    override fun onReturnIssuerScriptResult(issuerScriptResult: IssuerScriptResult, s: String) {
        if (issuerScriptResult == IssuerScriptResult.SUCCESS) {
            Log.d(TAG, "onReturnIssuerScriptResult:$s")
        } else if (issuerScriptResult == IssuerScriptResult.NO_OR_FAIL) {
            Log.d(TAG, "not issuer script result")
        }
    }

    override fun onNFCrequestTipsConfirm(msgID: NfcTipMessageID, msg: String) {}

    override fun onNFCrequestOnline() {}

    override fun onNFCrequestImportPin(type: Int, lasttimeFlag: Int, amt: String) {}

    override fun onNFCTransResult(result: NfcTransResult) {}

    override fun onNFCErrorInfor(erroCode: NfcErrMessageID, strErrInfo: String) {}

    override fun onReturnNfcCardData(hashtable: Hashtable<String, String>) {}

    private fun getAmt(field54: String): String {
        var field54 = field54
        try {
            field54 = field54.substring(field54.length - 12)
            val double1 = field54.toDouble() / 100
            field54 = DecimalFormat("0.00").format(double1)
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return field54
    }

    private fun GetCardNo(): String {
        var cardno = EmvNfcKernelApi.getInstance().getValByTag(0x5A)
        if (cardno == null || cardno == "") {
            cardno = EmvNfcKernelApi.getInstance().getValByTag(0x57)
            if (cardno == null || cardno == "") return ""
            cardno = cardno.substring(0, cardno.uppercase(Locale.getDefault()).indexOf("D"))
        }

        if ((cardno[cardno.length - 1] == 'f') || (cardno[cardno.length - 1] == 'F')
            || (cardno[cardno.length - 1] == 'd') || (cardno[cardno.length - 1] == 'D')
        ) cardno = cardno.substring(0, cardno.length - 1)
        return cardno
    }

    fun proc_offlinePin(pinEntryType: Int, isLastPinTry: Boolean, bundle: Bundle): Int {
        var iret = 0

        val paramVar = Bundle()
        paramVar.putInt("inputType", 3) //Offline PlainPin
        paramVar.putInt("CardSlot", 0)

        paramVar.putBoolean("sound", true)
        paramVar.putBoolean("onlinePin", false)
        paramVar.putBoolean("FullScreen", true)
        paramVar.putLong("timeOutMS", 60000)
        paramVar.putString("supportPinLen", "0,4,5,6,7,8,9,10,11,12")
        paramVar.putString("title", "Teclado de seguridad")
        paramVar.putBoolean("randomKeyboard", false)

        val pinTryTimes = bundle.getInt("PinTryTimes")
        val isFirst = bundle.getBoolean("isFirstTime", false)

        Log.d(TAG, "PinTryTimes:$pinTryTimes")
        if (isLastPinTry) {
            if (isFirst) paramVar.putString(
                "message",
                "Por favor, ingrese el PIN \nÚltimo intento de PIN"
            )
            else paramVar.putString(
                "message",
                "Por favor, ingrese el PIN \nPIN incorrecto \nÚltimo intento de PIN"
            )
        } else {
            if (isFirst) paramVar.putString("message", "Por favor, ingrese el PIN \n")
            else {
                paramVar.putString(
                    "message",
                    "Por favor, ingrese el PIN \nPIN incorrecto \nIntentos de PIN:$pinTryTimes"
                )
            }
        }

        val emvBundle = bundle
        if (pinEntryType == 1) {
            paramVar.putInt("inputType", 4) //Offline CipherPin

            val pub = emvBundle.getByteArray("pub")
            val publen = emvBundle.getIntArray("publen")
            val exp = emvBundle.getByteArray("exp")
            val explen = emvBundle.getIntArray("explen")

            Log.d("applog", "ModuleLen = " + publen!![0] + ": " + Funs.bytesToHexString(pub))
            Log.d("applog", "ExponentLen = " + explen!![0] + ": " + Funs.bytesToHexString(exp))

            val ModuleLen = publen[0]
            val ExponentLen = explen[0]
            val Module = ByteArray(ModuleLen)
            val Exponent = ByteArray(ExponentLen)

            System.arraycopy(pub, 0, Module, 0, ModuleLen)
            System.arraycopy(exp, 0, Exponent, 0, ExponentLen)

            paramVar.putInt("ModuleLen", ModuleLen) //Modulus length
            paramVar.putString("Module", Funs.bytesToHexString(Module)) //Module
            paramVar.putInt("ExponentLen", ExponentLen) //Exponent length
            paramVar.putString("Exponent", Funs.bytesToHexString(Exponent)) //Exponent
        }

        Log.d("applog", "proc_offlinePin getPinBlockEx start")

        val se = SEManager()
        iret = se.getPinBlockEx(paramVar, object : IInputActionListener.Stub() {
            @Throws(RemoteException::class)
            override fun onInputChanged(type: Int, result: Int, bundle: Bundle) {
                val resultBundle = bundle
                try {
                    Log.i(
                        "applog",
                        "proc_offlinePin：getPinBlockEx===onInputChanged：type=$type，result=$result"
                    )

                    if (type == 2) { // entering PIN
                    } else if (type == 0) //bypass
                    {
                        if (result == 0) {
                            Log.d("applog", "proc_offlinePin bypass")
                            mKernelApi.sendOfflinePINVerifyResult(1) //bypass
                        } else {
                            mKernelApi.sendOfflinePINVerifyResult(-198) //return code error
                        }
                    } else if (type == 3) //Offline plaintext
                    {
                        Log.d("applog", "proc_offlinePin Plaintext offline")
                        if (result == 0) {
                            mKernelApi.sendOfflinePINVerifyResult(0) //Offline plaintext verify successfully
                        } else { //Incorrect PIN, try again
                            val arg1Str = result.toString() + ""
                            if (arg1Str.length >= 4 && "71" == arg1Str.subSequence(0, 2)) {
                                if ("7101" == arg1Str) {
                                    mKernelApi.sendOfflinePINVerifyResult(-192) //PIN BLOCKED
                                } else {
                                    if ("7102" == arg1Str) {
                                        emvBundle.putBoolean("isFirstTime", false)
                                        emvBundle.putInt("PinTryTimes", 1)
                                        proc_offlinePin(
                                            pinEntryType,
                                            true,
                                            emvBundle
                                        ) //try again the last pin try
                                    } else {
                                        emvBundle.putBoolean("isFirstTime", false)
                                        emvBundle.putInt(
                                            "PinTryTimes",
                                            (arg1Str.substring(2, 4).toInt() - 1)
                                        )
                                        proc_offlinePin(pinEntryType, false, emvBundle) //try again
                                    }
                                }
                            } else if ("7074" == arg1Str) {
                                mKernelApi.sendOfflinePINVerifyResult(-192) //PIN BLOCKED
                            } else if ("7072" == arg1Str || "7073" == arg1Str) {
                                mKernelApi.sendOfflinePINVerifyResult(-202) //IC command failed
                            } else {
                                mKernelApi.sendOfflinePINVerifyResult(-198) //Return code error
                            }
                        }
                    } else if (type == 4) //Offline encryption PIN
                    {
                        Log.d("applog", "proc_offlinePin Offline encryption")
                        if (result == 0) {
                            mKernelApi.sendOfflinePINVerifyResult(0) //Offline encryption PIN verify successfully
                        } else {
                            val arg1Str = result.toString() + ""
                            if (arg1Str.length >= 4 && "71" == arg1Str.subSequence(0, 2)) {
                                if ("7101" == arg1Str) {
                                    mKernelApi.sendOfflinePINVerifyResult(-192) //PIN BLOCKED
                                } else {
                                    Log.d(
                                        "applog",
                                        "proc_offlinePin Offline encryption entry pin again"
                                    )
                                    if ("7102" == arg1Str) {
                                        emvBundle.putBoolean("isFirstTime", false)
                                        emvBundle.putInt("PinTryTimes", 1)
                                        proc_offlinePin(
                                            pinEntryType,
                                            true,
                                            emvBundle
                                        ) //try again the last pin try
                                    } else {
                                        emvBundle.putBoolean("isFirstTime", false)
                                        emvBundle.putInt(
                                            "PinTryTimes",
                                            (arg1Str.substring(2, 4).toInt() - 1)
                                        )
                                        proc_offlinePin(pinEntryType, false, emvBundle) //try again
                                    }
                                }
                            } else if ("7074" == arg1Str) {
                                mKernelApi.sendOfflinePINVerifyResult(-192) //PIN BLOCKED
                            } else if ("7072" == arg1Str || "7073" == arg1Str) {
                                mKernelApi.sendOfflinePINVerifyResult(-202) //IC command failed(card removed)
                            } else {
                                mKernelApi.sendOfflinePINVerifyResult(-198) //Return code error
                            }
                        }
                    } else if (type == 0x10) // click Cancel button
                    {
                        mKernelApi.sendOfflinePINVerifyResult(-199) //cancel
                    } else if (type == 0x11) // pinpad timed out
                    {
                        mKernelApi.sendOfflinePINVerifyResult(-199) //timeout
                    } else {
                        mKernelApi.sendOfflinePINVerifyResult(-198) //Return code error
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    Log.d("applog", "proc_offlinePin exception")
                }
            }
        })
        if (iret == -3 || iret == -4) mKernelApi.sendOfflinePINVerifyResult(-198)
        return iret
    }


    inner class AdminInputListener : PinInputListener {
        override fun onInput(len: Int, key: Int) {
        }

        override fun onConfirm(pinBlock: ByteArray, isNonePin: Boolean) {
            if (isNonePin) {
                mKernelApi.bypassPinEntry() //bypass
            } else {
                _posInputDatas.postValue(
                    _posInputDatas.value?.update(szPINData = String(pinBlock))
                        ?: _posInputDatas.value
                )
                mKernelApi.sendPinEntry()
            }
        }

        override fun onConfirm_dukpt(PinBlock: ByteArray, ksn: ByteArray) {
            _posInputDatas.postValue(_posInputDatas.value?.update(szPINData = String(PinBlock)))
        }

        override fun onCancel() {
            //Cancel
        }

        override fun onTimeOut() {
            //Timeout
        }

        override fun onError(i: Int) {
            //onError
        }
    }

}
