package com.drabatx.urovopaymentapp.domain.repository

import android.util.Log
import com.urovo.i9000s.api.emv.EmvNfcKernelApi
import com.urovo.sdk.pinpad.listener.PinInputListener

internal class AdminInputListener : PinInputListener {
    private lateinit var mKernelApi: EmvNfcKernelApi
    private val TAG = "AdminInputListener"
    fun setKernelApi(mKernelApi: EmvNfcKernelApi) {

    }

    override fun onInput(len: Int, key: Int) {
    }

    override fun onConfirm(pinBlock: ByteArray, isNonePin: Boolean) {
        if (isNonePin) {
            mKernelApi.bypassPinEntry() //bypass
            //                mKernelApi.ProcOnlinePinAgain();
        } else {
            Log.d(TAG, "pinblock:" + String(pinBlock))
            mKernelApi.sendPinEntry()
        }
    }

    override fun onConfirm_dukpt(PinBlock: ByteArray, ksn: ByteArray) {
        if (PinBlock == null) {
            mKernelApi.bypassPinEntry() //bypass
            //mKernelApi.ProcOnlinePinAgain();
        } else {
            Log.d(TAG, "pinblock:" + String(PinBlock))
            Log.d(TAG, "ksn:" + String(ksn))
            mKernelApi.sendPinEntry()
        }
    }

    override fun onCancel() {
        Log.d(TAG, "PINPAD cancel")
        mKernelApi.cancelPinEntry()
    }

    override fun onTimeOut() {
        mKernelApi.cancelPinEntry()
    }

    override fun onError(i: Int) {
        mKernelApi.cancelPinEntry()
    }
}
