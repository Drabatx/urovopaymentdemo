package com.drabatx.urovopaymentapp.presentation.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.drabatx.urovopaymentapp.domain.repository.MyEmvListener
import com.urovo.i9000s.api.emv.ContantPara
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.beeper.BeeperImpl
import com.urovo.sdk.insertcard.InsertCardHandlerImpl
import com.urovo.sdk.led.LEDDriverImpl
import com.urovo.sdk.pinpad.PinPadProviderImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Hashtable

class CardReaderViewModel(
    private val mKernelApi: EmvNfcKernelApi,
    private val emvListener: MyEmvListener,
) : ViewModel() {
    private val TAG = "CardReaderViewModel"
    private val payCardType = 1 //0:mag，1：ic/rfid Solo para mostrar la imagen del tipo de tarjeta

    val isRequestOnlineProcess = emvListener.isRequestOnlineProcess
    val result = emvListener.result
    val reasonsEMV = emvListener.reasonsEMV

    private var amount = "1"


    fun startKernelCoroutine(checkCardMode: ContantPara.CheckCardMode) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = Hashtable<String, Any>().apply {
                    put("checkCardMode", checkCardMode)
                    put("currencyCode", "484") //484 para MX
                    put("emvOption", ContantPara.EmvOption.START) // START_WITH_FORCE_ONLINE
                    put("amount", amount)
                    put("cashbackAmount", "0")
                    put("checkCardTimeout", "30") // Check Card timeout in seconds
                    put("transactionType", "00") // 00-goods, 01-cash, 09-cashback, 20-refund
                    put("isEnterAmtAfterReadRecord", false)
                    put("supportDRL", true) // support Visa DRL?
                }
                // Aquí llamas a tu API
                mKernelApi.startKernel(data)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}