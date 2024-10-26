package com.drabatx.urovopaymentapp.presentation.view.factory

import com.drabatx.urovopaymentapp.data.model.pos2.models.PosInputDatas
import com.drabatx.urovopaymentapp.domain.repository.MyEmvListener
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.beeper.BeeperImpl
import com.urovo.sdk.led.LEDDriverImpl
import javax.inject.Inject

class MyEmvListenerFactory @Inject constructor(
    private val mKernelApi: EmvNfcKernelApi,
    private val iBeeper: BeeperImpl,
    private val iLed: LEDDriverImpl
) {
    fun create(posInputDatas: PosInputDatas): MyEmvListener {
        return MyEmvListener(posInputDatas, mKernelApi, iBeeper, iLed)
    }
}