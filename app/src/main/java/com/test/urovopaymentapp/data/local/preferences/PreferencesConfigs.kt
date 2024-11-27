package com.test.urovopaymentapp.data.local.preferences

import android.content.SharedPreferences
import com.test.urovopaymentapp.MyApp
import com.test.urovopaymentapp.domain.repository.LOGIN_CONSUMER_REQUEST_ID
import com.test.urovopaymentapp.domain.repository.LOGIN_TIMESTAMP
import com.test.urovopaymentapp.domain.repository.LOGIN_TSEC

object PreferencesConfigs {
    fun getInstance(): SharedPreferences {
        return MyApp.getInstance()
            .let {
                SharedPrefsTool.getInstances(
                    context = it.applicationContext,
                    SharedPrefsTool.MERCHANT_PREFS_NAME
                )
            }
    }
    var tsecHeader:String
        get() = getInstance().let {
            SharedPrefsTool.get(it, LOGIN_TSEC, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, LOGIN_TSEC, value)
            }
        }
    var customerIdHeader: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, LOGIN_CONSUMER_REQUEST_ID, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, LOGIN_CONSUMER_REQUEST_ID, value)
            }
        }

    var timestampHeader: Long
        get() = getInstance().let {
            SharedPrefsTool.get(it, LOGIN_TIMESTAMP, 0)
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, LOGIN_TIMESTAMP, value)
            }
        }

}