package com.test.urovopaymentapp

import android.app.Application
import android.content.Context
import com.test.urovopaymentapp.data.local.preferences.MerchantConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        MerchantConfig.initialize(applicationContext)
        MerchantConfig.authUserId = "00000001"
        MerchantConfig.authConsumerId = "10000080"
        MerchantConfig.authenticationType = "64"
        MerchantConfig.idAuthenticationData = "password"
        MerchantConfig.authenticationData = "BB1QBBx88iBz22pr"
        MerchantConfig.backendUserId = ""
        MerchantConfig.backendAccessCode = "00000001"
        MerchantConfig.backendDialogId = ""

    }

    companion object {
        fun getInstance(): Context {
            return MyApp().applicationContext
        }
    }

}