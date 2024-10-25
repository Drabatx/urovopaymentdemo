package com.drabatx.urovopaymentapp.presentation.view.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.drabatx.urovopaymentapp.domain.repository.MyEmvListener
import com.drabatx.urovopaymentapp.presentation.view.viewmodels.CardReaderViewModel
import com.urovo.i9000s.api.emv.EmvNfcKernelApi

class CardReaderViewModelFactory(
    private val mKernelApi: EmvNfcKernelApi,
    private val myEmvListener: MyEmvListener
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardReaderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CardReaderViewModel(
                mKernelApi,
                myEmvListener
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}