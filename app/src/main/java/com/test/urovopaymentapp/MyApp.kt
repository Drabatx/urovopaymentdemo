package com.test.urovopaymentapp

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
//        MerchantParams.setMerchantName("Urovo test")
//        MerchantParams.setMerchantNum("000000000000001")
//        MerchantParams.setTerminalNum("88888888")
//        MerchantParams.setTPDU("6000030000")
    }

    companion object {
        fun getInstance(): Context {
            return MyApp().applicationContext
        }
    }

}