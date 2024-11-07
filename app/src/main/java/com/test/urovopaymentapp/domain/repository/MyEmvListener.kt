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
import com.test.urovopaymentapp.data.model.StIso8583
import com.test.urovopaymentapp.data.model.pos2.constants.PosTransType
import com.test.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.test.urovopaymentapp.domain.model.EmvReason
import com.test.urovopaymentapp.utils.UrovoResult
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
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat
import java.util.Hashtable
import java.util.Locale
import javax.inject.Inject


class MyEmvListener @Inject constructor(
    private val mKernelApi: EmvNfcKernelApi,
    private val context: Context,
    private val tradingRepository: TradingRepositoryImpl
) : EmvListener, PosEveCallBack {
    private val TAG = "MyEmvListener"

    private val _isRequestOnlineProcess = MutableLiveData(false)
    val isRequestOnlineProcess: LiveData<Boolean> get() = _isRequestOnlineProcess

    private val _result = MutableLiveData<UrovoResult<PosInputDatas>>(UrovoResult.Initial)
    val result: LiveData<UrovoResult<PosInputDatas>> get() = _result

//    private val _reasonsEMV = MutableLiveData<EmvReasonsModel>()
//    val reasonsEMV: LiveData<EmvReasonsModel> get() = _reasonsEMV

    private val _posInputDatas: MutableLiveData<PosInputDatas> = MutableLiveData()
    val posInputDatas: LiveData<PosInputDatas> get() = _posInputDatas

    private var transType = 7
    private var payCardType = 1 //0:mag，1：ic/rfid

    private val pinpadlistener = AdminInputListener()

    val keyId: Int = 10
    var mSTIOS8583: StIso8583? = null
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
        transType = PosTransType.getEMVTransType(posInputDatas.value?.iTransNo ?: 7)

        payCardType = 1 //0:mag，1：ic/rfid
        iMagCardReader = MagCardReaderImpl.getInstance()
        iInsertCardReader = InsertCardHandlerImpl.getInstance()
        pinpad = PinPadProviderImpl.getInstance()
        iBeeper = BeeperImpl.getInstance()
        iLed = LEDDriverImpl.getInstance()

        StartKernel(ContantPara.CheckCardMode.INSERT_OR_TAP)
    }

    fun StartKernel(checkCardMode: CheckCardMode) {
        Thread {
            try {
                Log.i(TAG, "++++++++++++++ run: startKernel")
                val data = Hashtable<String, Any>()
                data["checkCardMode"] = checkCardMode //
                data["currencyCode"] = "682" //682
                data["emvOption"] = ContantPara.EmvOption.START // START_WITH_FORCE_ONLINE
                data["amount"] = posInputDatas.value?.amt ?: "0"
                data["cashbackAmount"] = "0"
                data["checkCardTimeout"] = "30" // Check Card time out .Second
                data["transactionType"] = "00" //00-goods 01-cash 09-cashback 20-refund
                data["isEnterAmtAfterReadRecord"] = false
                data["supportDRL"] = true // support Visa DRL?
                mKernelApi.startKernel(data)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }.start()
    }

    fun processPayment(tlvData: String) {
        runBlocking {
            posInputDatas.value?.let {
                tradingRepository.startSale(it).collect { result ->
                    when (result) {
                        is UrovoResult.Success -> {
                            val responseData =
                                "8A02303091081AD4D51400820000710F860D842400000817C217D34162474C910A1397ECEFC7A605110012"
                            mSTIOS8583 = StIso8583()
                            mKernelApi.sendOnlineProcessResult(true, responseData)
                        }

                        is UrovoResult.Error -> {
                            mKernelApi.sendOnlineProcessResult(false, tlvData)
                        }

                        is UrovoResult.Loading -> {
                            _result.postValue(UrovoResult.Loading)
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }

    fun setPosData(posData: PosInputDatas) {
        _posInputDatas.postValue(posData)
    }

    override fun onEventComplete(stIOS8583: StIso8583?) {
        Log.i(TAG, "onEventComplete: ")
    }

    override fun onRequestSetAmount() {
        Log.i(TAG, "onRequestSetAmount: ${posInputDatas.value?.amt}")
    }

    override fun onReturnCheckCardResult(
        checkCardResult: CheckCardResult,
        hashtable: Hashtable<String, String>
    ) {
        Log.i(TAG, "onReturnCheckCardResult checkCardResult =$checkCardResult")
        if (checkCardResult == CheckCardResult.MSR) {
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
            _posInputDatas.postValue(_posInputDatas.value?.update(swipedMode = CardTypeConstant.MSR))

            _result.postValue(UrovoResult.Error(Throwable(EmvReason.MESSAGE_MSR.name)))

        } else if (checkCardResult == CheckCardResult.NEED_FALLBACK) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_need_fallback)))
            )
        } else if (checkCardResult == CheckCardResult.BAD_SWIPE) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_bad_swipe)))
            )
        } else if (checkCardResult == CheckCardResult.NOT_ICC) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_not_icc)))
            )
        } else if (checkCardResult == CheckCardResult.TIMEOUT) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_timeout)))
            )
        } else if (checkCardResult == CheckCardResult.CANCEL) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_cancel_operation)))
            )
        } else if (checkCardResult == CheckCardResult.DEVICE_BUSY) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_device_busy)))
            )
        } else if (checkCardResult == CheckCardResult.USE_ICC_CARD) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_use_icc_card)))
            )
        } else if (checkCardResult == CheckCardResult.MULT_CARD) {
            _result.postValue(
                UrovoResult.Error(Throwable(context.getString(R.string.error_multi_card)))
            )
        }
    }

    override fun onRequestSelectApplication(arrayList: ArrayList<String>) {
        Log.i(TAG, "MainActivity  onRequestSelectApplication")
        var i = 0
        while (i < arrayList.size) {
            Log.d(TAG, "app name " + i + " : " + arrayList[i])
            i++
        }
        if (i == 1) {
            mKernelApi.selectApplication(0)
        } else {
            _result.postValue(
                UrovoResult.Error(Throwable(arrayList.joinToString(", ")))
            )
        }
    }


    //if contact online pin verify, will callback
    override fun onRequestPinEntry(pinEntrySource: PinEntrySource) {
        Log.i(TAG, "MainActivity  onRequestPinEntry request online pin")
        if (pinEntrySource == PinEntrySource.KEYPAD) {
            _posInputDatas.postValue(_posInputDatas.value?.update(pan = GetCardNo()))
            if (pinEntrySource == PinEntrySource.KEYPAD) {
                _posInputDatas.postValue(_posInputDatas.value?.update(pan = GetCardNo()))
//                _reasonsEMV.postValue(EmvReasonsModel("Request Pin", EmvReason.MESSAGE_REQUEST_PIN))
                emv_proc_onlinePin()
            }
        }
    }

    override fun onRequestOfflinePinEntry(pinEntrySource: PinEntrySource, PinTryCount: Int) {
        Log.i(TAG, "MainActivity  onRequestOfflinePinEntry")
    }

    override fun onRequestConfirmCardno() {
        Log.d(TAG, "CardNo:" + GetCardNo())
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
        //Se auto confirma la tarjeta
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

        _isRequestOnlineProcess.postValue(true)

        Log.i(TAG, "onRequestOnlineProcess: tlvData:$cardTlvData")
        processPayment(tlvData = cardTlvData)
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
        _isRequestOnlineProcess.postValue(true)
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
            _posInputDatas.postValue(
                _posInputDatas.value?.update(
                    pzNumber = mSTIOS8583?.field11,
                    stIso8583 = mSTIOS8583
                )
            )
            _result.postValue(UrovoResult.Success(posInputDatas.value!!))
        } else if (transactionResult == TransactionResult.ONLINE_DECLINED) {
            soundPool.play(R.raw.success, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _posInputDatas.postValue(
                _posInputDatas.value?.update(
                    pzNumber = mSTIOS8583?.field11,
                    stIso8583 = mSTIOS8583
                )
            )
            _result.postValue(UrovoResult.Success(posInputDatas.value!!))

//            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
//            mKernelApi.abortKernel()
//            _result.postValue(UrovoResult.Error(Throwable("Transacción rechazada por el banco.")))
        } else if (transactionResult == TransactionResult.OFFLINE_DECLINED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Transacción rechazada por la tarjeta.")))
        } else if (transactionResult == TransactionResult.ICC_CARD_REMOVED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Tarjeta retirada antes de completar la operación.")))
        } else if (transactionResult == TransactionResult.TERMINATED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Operación terminada.")))
        } else if (transactionResult == TransactionResult.CANCELED_OR_TIMEOUT) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Operación cancelada o tiempo agotado.")))
        } else if (transactionResult == TransactionResult.CANCELED) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Operación cancelada.")))
        } else if (transactionResult == TransactionResult.CARD_BLOCKED_APP_FAIL) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Tarjeta bloqueada.")))
        } else if (transactionResult == TransactionResult.APPLICATION_BLOCKED_APP_FAIL) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Aplicación bloqueada.")))
        } else if (transactionResult == TransactionResult.INVALID_ICC_DATA) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Datos de tarjeta inválidos. Intenta nuevamente.")))
        } else if (transactionResult == TransactionResult.NO_EMV_APPS) {
            soundPool.play(R.raw.error, 0.8f, 0.8f, 1, 0, 1f)
            mKernelApi.abortKernel()
            _result.postValue(UrovoResult.Error(Throwable("Tarjeta incompatible o sin aplicaciones EMV.")))
        }
    }

    override fun onRequestDisplayText(displayText: ContantPara.DisplayText) {
        Log.i(TAG, "MainActivity  onRequestDisplayText")
        when (displayText) {
            ContantPara.DisplayText.USE_MAG_STRIPE -> {
                _result.postValue(
                    UrovoResult.Message(context.getString(R.string.use_mag_stripe))
                )
            }

            ContantPara.DisplayText.APPROVED_PLEASE_SIGN -> {
                _result.postValue(
                    UrovoResult.Message(context.getString(R.string.approved_please_sign))
                )
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

    //if you call mEmvApi.getIssuerScriptResult() ,this will callback
    override fun onReturnIssuerScriptResult(issuerScriptResult: IssuerScriptResult, s: String) {
        if (issuerScriptResult == IssuerScriptResult.SUCCESS) {
            Log.d(TAG, "onReturnIssuerScriptResult:$s")
        } else if (issuerScriptResult == IssuerScriptResult.NO_OR_FAIL) {
            Log.d(TAG, "not issuer script result")
        }
    }


    //contactless Tip message callback
    override fun onNFCrequestTipsConfirm(msgID: NfcTipMessageID, msg: String) {
        Log.i(TAG, "onNFCrequestTipsConfirm: $msg")
        _result.postValue(UrovoResult.Message(msg))

    }


    //go online send data to host ,then import online result
    override fun onNFCrequestOnline() {
        //should send to host
        Log.d(TAG, "onNFCrequestOnline ")
        _isRequestOnlineProcess.postValue(true)

        _result.postValue(UrovoResult.Message(context.getString(R.string.on_confirm_pin)))

    }


    override fun onNFCrequestImportPin(type: Int, lasttimeFlag: Int, amt: String) {
        Log.i(TAG, "onNFCrequestImportPin: ")
        _result.postValue(UrovoResult.Message(context.getString(R.string.on_confirm_pin)))
        emv_proc_onlinePin()
    }


    override fun onNFCTransResult(result: NfcTransResult) {
        Log.i(TAG, "onNFCTransResult: ${result.name}")
        val value = mKernelApi.getValByTag(0x5F20)
        Log.i(TAG, "onNFCTransResult: 5F20=$value")
    }

    override fun onNFCErrorInfor(erroCode: NfcErrMessageID, strErrInfo: String) {
        Log.d(TAG, "onNFCErrorInfor: erroCode:$erroCode - onErrorInfor:$strErrInfo")
        _result.postValue(UrovoResult.Error(Throwable(strErrInfo)))

    }


    //save data about go online or offline
    override fun onReturnNfcCardData(hashtable: Hashtable<String, String>) {
        val ksn = hashtable["KSN"]
        val TrackData = hashtable["TRACKDATA"]
        val EmvData = hashtable["EMVDATA"]
        val QPBOCType = hashtable["QPBOCTYPE"]
        _posInputDatas.postValue(_posInputDatas.value?.update(swipedMode = CardTypeConstant.RFID))
        var tagValue = mKernelApi.getValByTag(0x9F26)
        if (tagValue != null) {
            Log.d(TAG, "tagValue 0x9F26:$tagValue")
        }
        val Track = mKernelApi.getValByTag(0x57)
        Log.i(TAG, " Track:$Track")
        Log.i(TAG, " CardNo:" + GetCardNo())

        tagValue = mKernelApi.getValByTag(0x9F24) //use for contact or contactless
        if (tagValue != null) {
            Log.d(TAG, "tagValue 0x9F24:$tagValue")
        }
        val TVR = mKernelApi.getValByTag(0x95)
        Log.d(TAG, "tagValue 0x95:$TVR")

        tagValue = mKernelApi.getValByTag(0x9F41)
        if (tagValue != null) Log.d(TAG, "tagValue 0x9F41:$tagValue")
        tagValue = mKernelApi.getValByTag(0x9F1E)
        if (tagValue != null) Log.d(TAG, "tagValue 0x9F1E:$tagValue")
        tagValue = mKernelApi.getValByTag(0x5F34)
        if (tagValue != null) Log.d(TAG, "tagValue 0x5F34:$tagValue")


        val MstripMode = mKernelApi.mstripFlag
        if (MstripMode == 1) {
            val track2 = mKernelApi.getValByTag(0x9F6B)
            Log.d(TAG, "mStrip track2:$track2")
        }

        Log.d(TAG, "onReturnNfcCardData")
        Log.d(TAG, "KSN:$ksn")
        Log.d(TAG, "TrackData:$TrackData")
        Log.d(TAG, "EmvData:$EmvData")

        _posInputDatas.postValue(
            _posInputDatas.value?.update(
                pan = GetCardNo(),
                track2 = mKernelApi.getValByTag(0x57),
                szCardSeqNo = mKernelApi.getValByTag(0x5F34),
                szExpDate = mKernelApi.getValByTag(0x5F24),
                file55 = EmvData
            )
        )
    }

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

    fun emv_proc_onlinePin() {
        Log.i("applog", "emv_proc_onlinePin")
        val param = Bundle()
//        if (isDUKPT) param.putInt("PINKeyNo", 3)
//        else
        param.putInt("PINKeyNo", keyId)
        val cardno: String = GetCardNo()
        Log.i("applog", "emv_proc_onlinePin cardno $cardno")
        param.putString("cardNo", cardno)
        param.putBoolean("sound", true)
        param.putBoolean("onlinePin", true)
        param.putBoolean("FullScreen", true)
        param.putLong("timeOutMS", (60 * 1000).toLong())
        param.putString("supportPinLen", "0,4,5,6,7,8,9,10,11,12") // "4,4");   //
        param.putString("title", "Security Keyboard")
        param.putString("message", "Please Enter PIN" + "\n") // use your real amount


        Log.i("applog", "getPinBlockEx ")
        _result.postValue(UrovoResult.Message(context.getString(R.string.pin_request)))

    }

    inner class AdminInputListener : PinInputListener {
        override fun onInput(len: Int, key: Int) {
        }

        override fun onConfirm(pinBlock: ByteArray, isNonePin: Boolean) {
            if (isNonePin) {
                mKernelApi.bypassPinEntry() //bypass
            } else {
                _posInputDatas.postValue(
                    _posInputDatas.value?.update(
                        szPINData = String(pinBlock)
                    )
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
