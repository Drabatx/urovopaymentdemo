package com.drabatx.urovopaymentapp.presentation.view.viewmodels

import androidx.lifecycle.ViewModel
import com.drabatx.urovopaymentapp.data.model.pos2.Constants.PosTransType
import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InputAmountViewModel : ViewModel() {

    private val _inputData = MutableStateFlow(PosInputDatas.Builder().build())
    val inputData = _inputData.asStateFlow()
    fun setInputData() {
        _inputData.value = PosInputDatas.Builder().setITransNo(PosTransType.POS_SALE).build()
    }

    fun setAmount(amount: Double) {
        this.inputData.value.amt = amount.toString()
    }


    init {
        setInputData()
    }

}