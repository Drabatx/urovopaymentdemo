package com.drabatx.urovopaymentapp.presentation.view.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.drabatx.urovopaymentapp.data.model.pos2.Constants.PosTransType
import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InputAmountViewModel: ViewModel() {

    private val _inputData = MutableStateFlow(PosInputDatas())
    val inputData = _inputData.asStateFlow()
    fun setInputData() {
        this.inputData.value.setiTransNo(PosTransType.POS_SALE)
    }
    fun setAmount(amount: Double) {
        this.inputData.value.amt = amount.toString()
    }


    init {
        setInputData()
    }

}