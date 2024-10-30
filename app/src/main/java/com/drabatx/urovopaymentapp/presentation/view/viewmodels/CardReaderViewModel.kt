package com.drabatx.urovopaymentapp.presentation.view.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.drabatx.urovopaymentapp.domain.repository.MyEmvListener
import com.urovo.i9000s.api.emv.ContantPara
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Hashtable
import javax.inject.Inject

@HiltViewModel
class CardReaderViewModel @Inject constructor(
    private val myEmvListener: MyEmvListener,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val TAG = "CardReaderViewModel"
    private val payCardType = 1 //0:mag，1：ic/rfid Solo para mostrar la imagen del tipo de tarjeta

    val livePosInputDatas = myEmvListener.posInputDatas

//    val isRequestOnlineProcess = myEmvListener.isRequestOnlineProcess
//    val result = myEmvListener.result
    val reasonsEMV = myEmvListener.reasonsEMV


    fun initEmvListener(posInputDatas: PosInputDatas) {
        myEmvListener.initKernel(posInputDatas)
    }

//    private fun startKernelCoroutine(checkCardMode: ContantPara.CheckCardMode, amount:String) {
//        Log.d(TAG, "SDK version:" + mKernelApi.emVjarVers)
//        Log.d(TAG, "EMV lib version:" + mKernelApi.getEMVLibVers(ContantPara.CardSlot.ICC))
//        Log.d(TAG, "PayPass lib version:" + mKernelApi.getNFCLibVers(0x02.toByte()))
//        Log.d(TAG, "PayWave lib version:" + mKernelApi.getNFCLibVers(0x03.toByte()))
//        Log.d(TAG, "Amex lib version:" + mKernelApi.getNFCLibVers(0x04.toByte()))
//
//        viewModelScope.launch(Dispatchers.Default) {
//            try {
//                val data = Hashtable<String, Any>().apply {
//                    put("checkCardMode", checkCardMode)
//                    put("currencyCode", "484") //484 para MX
//                    put("emvOption", ContantPara.EmvOption.START) // START_WITH_FORCE_ONLINE
//                    put("amount", amount)
//                    put("cashbackAmount", "")
//                    put("checkCardTimeout", "30") // Check Card timeout in seconds
//                    put("transactionType", "00") // 00-goods, 01-cash, 09-cashback, 20-refund
//                    put("isEnterAmtAfterReadRecord", false)
//                    put("supportDRL", true) // support Visa DRL?
//                    put("enableBeeper", true)
//                }
//                // Aquí llamas a tu API
//                mKernelApi.startKernel(data)
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//        }
//    }
}