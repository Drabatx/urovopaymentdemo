package com.drabatx.urovopaymentapp.domain.repository

import android.os.Bundle
import android.os.RemoteException
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.drabatx.urovopaymentapp.domain.model.EmvReason
import com.drabatx.urovopaymentapp.domain.model.EmvReasonsModel
import com.drabatx.urovopaymentapp.utils.UrovoResult
import com.lib.card.constant.CardTypeConstant
import com.urovo.i9000s.api.emv.ContantPara
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
import com.urovo.sdk.led.LEDDriverImpl
import java.util.Hashtable
import java.util.Locale
import javax.inject.Inject


class MyEmvListener @Inject constructor(
    private var posInputDatas: PosInputDatas,
    private val mKernelApi: EmvNfcKernelApi,
    private val iBeeper: BeeperImpl,
    private val iLed: LEDDriverImpl
) : EmvListener {
    private val TAG = "MyEmvListener"

    private val _isRequestOnlineProcess = MutableLiveData(false)
    val isRequestOnlineProcess: LiveData<Boolean> get() = _isRequestOnlineProcess

    private val _result = MutableLiveData<UrovoResult<PosInputDatas>>(UrovoResult.Initial)
    val result: LiveData<UrovoResult<PosInputDatas>> get() = _result

    private val _reasonsEMV = MutableLiveData<EmvReasonsModel>()
    val reasonsEMV: LiveData<EmvReasonsModel> get() = _reasonsEMV

    override fun onRequestSetAmount() {
        Log.i(TAG, "MainActivity  onRequestSetAmount")
    }

    override fun onReturnCheckCardResult(
        checkCardResult: CheckCardResult,
        hashtable: Hashtable<String, String>
    ) {
        Log.i(TAG, "MainActivity  onReturnCheckCardResult checkCardResult =$checkCardResult")
        Log.d(TAG, hashtable.toString())
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
                posInputDatas = posInputDatas.update(track3 = track3)
            }
            var index = track2.indexOf("=")
            if (index != -1) {
                val PAN = track2.substring(0, index) //Obtener el número de tarjeta
                index++
                val EXPIRED_DATE = track2.substring(index, index + 4)
                val SERVICE_CODE = track2.substring(index + 4, index + 4 + 3)
                posInputDatas = posInputDatas.update(
                    pan = PAN,
                    track2 = track2,
                    szExpDate = EXPIRED_DATE
                )

            }
            posInputDatas = posInputDatas.update(swipedMode = CardTypeConstant.MSR)
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_MSR)
                .setMessage(cardNo ?: "").build()
        } else if (checkCardResult == CheckCardResult.NEED_FALLBACK) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_ERROR)
                .setMessage("NEED_FALLBACK ! Please Tap or Swipe Card!").build()
        } else if (checkCardResult == CheckCardResult.BAD_SWIPE) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_ERROR)
                .setMessage("BAD_SWIPE ! Please Tap or Swipe Card!").build()
        } else if (checkCardResult == CheckCardResult.NOT_ICC) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_ERROR)
                .setMessage("NOT_ICC, Remove and Insert Card Again!").build()
        } else if (checkCardResult == CheckCardResult.TIMEOUT) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_TIMEOUT)
                .setMessage("Check Card Time Out!").build()
        } else if (checkCardResult == CheckCardResult.CANCEL) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.CANCEL_OPERATION)
                .setMessage("Cancel Operation!").build()
        } else if (checkCardResult == CheckCardResult.DEVICE_BUSY) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_ERROR)
                .setMessage("Check Card Device Busy !").build()
        } else if (checkCardResult == CheckCardResult.USE_ICC_CARD) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_ERROR)
                .setMessage("Chip Card! Please Use Contact Interface,Please Insert Card!")
                .build()
        } else if (checkCardResult == CheckCardResult.MULT_CARD) {
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_ERROR)
                .setMessage("Multi Card! Please Insert One Card!")
                .build()
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
            _reasonsEMV.value = EmvReasonsModel.Builder()
                .setReason(EmvReason.MESSAGE_APP_SELECT)
                .setMessage(arrayList.joinToString(", "))
                .build()
        }
    }


    //if contact online pin verify, will callback
    override fun onRequestPinEntry(pinEntrySource: PinEntrySource) {
        Log.i(TAG, "MainActivity  onRequestPinEntry request online pin")
        if (pinEntrySource == PinEntrySource.KEYPAD) {
            posInputDatas = posInputDatas.update(pan = GetCardNo())
            //TODO Handle emv_proc_onlinePin()
        }
    }

    override fun onRequestOfflinePinEntry(pinEntrySource: PinEntrySource, PinTryCount: Int) {
        Log.i(TAG, "MainActivity  onRequestOfflinePinEntry")
    }

    override fun onRequestConfirmCardno() {
        Log.d(TAG, "CardNo:" + GetCardNo())
        posInputDatas.swipedMode = CardTypeConstant.IC
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
        posInputDatas = posInputDatas.update(
            pan = pan,
            track2 = mKernelApi.getValByTag(0x57),
            szCardSeqNo = mKernelApi.getValByTag(0x5F34),
            szExpDate = mKernelApi.getValByTag(0x5F24)
        )
        mKernelApi.sendConfirmCardnoResult(true)
    }

    override fun onRequestFinalConfirm() {
        Log.i(TAG, "onRequestFinalConfirm: ")
        mKernelApi.sendFinalConfirmResult(true)
    }

    override fun onRequestOnlineProcess(cardTlvData: String, dataKsn: String) {
        posInputDatas =
            posInputDatas.update(
                pan = GetCardNo(),
                track2 = mKernelApi.getValByTag(0x57),
                szCardSeqNo = mKernelApi.getValByTag(0x5F34),
                szExpDate = mKernelApi.getValByTag(0x5F24),
                file55 = cardTlvData
            )
        val TVR = mKernelApi.getValByTag(0x95)
        Log.i(TAG, "  onRequestOnlineProcess TVR:$TVR")
        val PSN = mKernelApi.getValByTag(0x5F34)
        Log.i(TAG, "MainActivity  onRequestOnlineProcess PSN:$PSN")

        _isRequestOnlineProcess.value = true
        _reasonsEMV.value = EmvReasonsModel.Builder().setReason(EmvReason.MESSAGE_REQUEST_ONLINE)
            .setMessage("onConfirm PIN")
            .build()
    }


    override fun onReturnBatchData(cardTlvData: String) {
        posInputDatas = posInputDatas.update(
            pan = GetCardNo(),
            track2 = mKernelApi.getValByTag(0x57),
            szCardSeqNo = mKernelApi.getValByTag(0x5F34),
            szExpDate = mKernelApi.getValByTag(0x5F24),
            file55 = cardTlvData
        )
        _isRequestOnlineProcess.value = true
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

        if (transactionResult == TransactionResult.ONLINE_APPROVAL || transactionResult == TransactionResult.OFFLINE_APPROVAL) {
            mKernelApi.abortKernel()
            //TODO pago aceptado
            _result.value = UrovoResult.Success(posInputDatas)
        } else if (transactionResult == TransactionResult.ONLINE_DECLINED) {
            mKernelApi.abortKernel()
            _result.value = UrovoResult.Error(Throwable("online decline!"))
        } else if (transactionResult == TransactionResult.OFFLINE_DECLINED) {
            _result.value = UrovoResult.Error(Throwable("offline decline!"))
        } else if (transactionResult == TransactionResult.ICC_CARD_REMOVED) {
            _result.value = UrovoResult.Error(Throwable("icc card removed!"))
        } else if (transactionResult == TransactionResult.TERMINATED) {
            _result.value = UrovoResult.Error(Throwable("terminated!"))
        } else if (transactionResult == TransactionResult.CANCELED_OR_TIMEOUT) {
            _result.value = UrovoResult.Error(Throwable("canceled or timeout!"))
        } else if (transactionResult == TransactionResult.CANCELED) {
            _result.value = UrovoResult.Error(Throwable("canceled!"))
        } else if (transactionResult == TransactionResult.CARD_BLOCKED_APP_FAIL) {
            _result.value = UrovoResult.Error(Throwable("card blocked!"))
        } else if (transactionResult == TransactionResult.APPLICATION_BLOCKED_APP_FAIL) {
            _result.value = UrovoResult.Error(Throwable("application blocked!"))
        } else if (transactionResult == TransactionResult.INVALID_ICC_DATA) {
            _result.value = UrovoResult.Error(Throwable("invalid icc data!"))
        } else if (transactionResult == TransactionResult.NO_EMV_APPS) {
            _result.value = UrovoResult.Error(Throwable("no emv apps!"))
        }
    }

    override fun onRequestDisplayText(displayText: ContantPara.DisplayText) {
        Log.i(TAG, "MainActivity  onRequestDisplayText")
        _reasonsEMV.value = EmvReasonsModel.Builder()
            .setReason(EmvReason.MESSAGE_CARD_MESSAGE)
            .setMessage(displayText.name)
            .build()
    }


    override fun onRequestOfflinePINVerify(
        pinEntrySource: PinEntrySource,
        pinEntryType: Int,
        bundle: Bundle
    ) {
        if (pinEntrySource == PinEntrySource.KEYPAD) { //use in os 8.0
            val pinTryTimes: Int = mKernelApi.getOfflinePinTryTimes()
            bundle.putInt("PinTryTimes", pinTryTimes)
            bundle.putBoolean("isFirstTime", true)
            if (pinTryTimes == 1) {
                //TODO emv_proc_onlinePin()
            } else {
                //TODO emv_proc_onlinePin()
            }
        } else {
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
        _reasonsEMV.value = EmvReasonsModel.Builder()
            .setReason(EmvReason.MESSAGE_CARD_MESSAGE)
            .setMessage(msg)
            .build()
    }


    //go online send data to host ,then import online result
    override fun onNFCrequestOnline() {
        //should send to host
        Log.d(TAG, "onNFCrequestOnline ")
        _isRequestOnlineProcess.value = true
        _reasonsEMV.value = EmvReasonsModel.Builder().setMessage("onConfirm PIN")
            .setReason(EmvReason.MESSAGE_REQUEST_ONLINE).build()
    }


    override fun onNFCrequestImportPin(type: Int, lasttimeFlag: Int, amt: String) {
        Log.i(TAG, "onNFCrequestImportPin: ")
        _reasonsEMV.value = EmvReasonsModel.Builder()
            .setReason(EmvReason.REQUEST_ONLINEPIN).setMessage("onConfirm PIN").build()
        //TODO emv_proc_onlinePin()
    }


    override fun onNFCTransResult(result: NfcTransResult) {
        Log.i(TAG, "onNFCTransResult: ${result.name}")
        val value = mKernelApi.getValByTag(0x5F20)
        Log.i(TAG, "onNFCTransResult: 5F20=$value")
    }

    override fun onNFCErrorInfor(erroCode: NfcErrMessageID, strErrInfo: String) {
        Log.d(TAG, "onNFCErrorInfor: erroCode:$erroCode - onErrorInfor:$strErrInfo")
        _reasonsEMV.value = EmvReasonsModel.Builder()
            .setReason(EmvReason.MESSAGE_ERROR).setMessage(strErrInfo).build()
    }


    //save data about go online or offline
    override fun onReturnNfcCardData(hashtable: Hashtable<String, String>) {
        val ksn = hashtable["KSN"]
        val TrackData = hashtable["TRACKDATA"]
        val EmvData = hashtable["EMVDATA"]
        val QPBOCType = hashtable["QPBOCTYPE"]
        posInputDatas = posInputDatas.update(swipedMode = CardTypeConstant.RFID)
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

        posInputDatas = posInputDatas.update(
            pan = GetCardNo(),
            track2 = mKernelApi.getValByTag(0x57),
            szCardSeqNo = mKernelApi.getValByTag(0x5F34),
            szExpDate = mKernelApi.getValByTag(0x5F24),
            file55 = EmvData
        )
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
        Log.i(TAG, "GetCardNo: $cardno")
        return cardno
    }
}
