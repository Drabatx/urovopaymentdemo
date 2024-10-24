package com.drabatx.urovopaymentapp.presentation.view.viewmodels

import androidx.lifecycle.ViewModel
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.insertcard.InsertCardHandlerImpl
import com.urovo.sdk.pinpad.PinPadProviderImpl

class CardReaderViewModel : ViewModel() {
    private val payCardType = 1 //0:mag，1：ic/rfid
    private var iInsertCardReader: InsertCardHandlerImpl? = null
    private var mKernelApi: EmvNfcKernelApi? = null
    private var pinpad: PinPadProviderImpl? = null
    fun insertCard() {
        iInsertCardReader = InsertCardHandlerImpl.getInstance();
        mKernelApi = EmvNfcKernelApi.getInstance()
        pinpad = PinPadProviderImpl.getInstance()
    }
}