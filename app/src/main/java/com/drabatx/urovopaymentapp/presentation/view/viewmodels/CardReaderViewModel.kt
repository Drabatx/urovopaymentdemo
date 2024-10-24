package com.drabatx.urovopaymentapp.presentation.view.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.urovo.i9000s.api.emv.ContantPara
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.insertcard.InsertCardHandlerImpl
import com.urovo.sdk.pinpad.PinPadProviderImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Hashtable

class CardReaderViewModel : ViewModel() {
    private val payCardType = 1 //0:mag，1：ic/rfid
    private var insertCardHandlerImpl: InsertCardHandlerImpl? = null
    private var mKernelApi: EmvNfcKernelApi? = null
    private var pinpad: PinPadProviderImpl? = null

    private var amount = "1"
    fun insertCard() {
        insertCardHandlerImpl = InsertCardHandlerImpl.getInstance();
        mKernelApi = EmvNfcKernelApi.getInstance()
        pinpad = PinPadProviderImpl.getInstance()
        startKernelCoroutine(ContantPara.CheckCardMode.INSERT_OR_TAP)
    }


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
                mKernelApi?.startKernel(data)

                // Registro en log
                Log.d("applog", "amount: $amount")

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}