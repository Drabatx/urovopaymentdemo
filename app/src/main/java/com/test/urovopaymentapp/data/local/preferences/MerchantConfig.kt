package com.test.urovopaymentapp.data.local.preferences

import android.content.SharedPreferences
import com.test.urovopaymentapp.MyApp
import com.test.urovopaymentapp.domain.repository.AUTHENTICATION_CONSUMER_ID
import com.test.urovopaymentapp.domain.repository.AUTHENTICATION_DATA
import com.test.urovopaymentapp.domain.repository.AUTHENTICATION_TYPE
import com.test.urovopaymentapp.domain.repository.AUTHENTICATION_USER_ID
import com.test.urovopaymentapp.domain.repository.BACKEND_ACCESS_CODE
import com.test.urovopaymentapp.domain.repository.BACKEND_DIALOG_ID
import com.test.urovopaymentapp.domain.repository.BACKEND_USER_ID
import com.test.urovopaymentapp.domain.repository.ID_AUTHENTICATION_DATA

/**
 * Merchant parameters currently in use
 *
 * @author KuCoffee
 */
object MerchantConfig {

    fun getInstance(): SharedPreferences {
        return MyApp.getInstance()
            .let {
                SharedPrefsTool.getInstances(
                    context = it.applicationContext,
                    SharedPrefsTool.MERCHANT_PREFS_NAME
                )
            }
    }

    var authUserId: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, AUTHENTICATION_USER_ID, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, AUTHENTICATION_USER_ID, value)
            }
        }

    var authConsumerId: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, AUTHENTICATION_CONSUMER_ID, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, AUTHENTICATION_CONSUMER_ID, value)
            }
        }

    var authenticationType: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, AUTHENTICATION_TYPE, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, AUTHENTICATION_TYPE, value)
            }
        }

    var idAuthenticationData: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, ID_AUTHENTICATION_DATA, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, ID_AUTHENTICATION_DATA, value)
            }
        }

    var authenticationData: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, AUTHENTICATION_DATA, "")
        }
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, AUTHENTICATION_DATA, value)
            }
        }

    var backendUserId: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, BACKEND_USER_ID, "")
        }
    set(value) {
        getInstance().let {
            SharedPrefsTool.put(it, BACKEND_USER_ID, value)
        }
    }

    var backendAccessCode: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, BACKEND_ACCESS_CODE, "")
        }
        set(value){
            getInstance().let {
                SharedPrefsTool.put(it, BACKEND_ACCESS_CODE, value)
            }
        }

    var backendDialogId: String
        get() = getInstance().let {
            SharedPrefsTool.get(it, BACKEND_DIALOG_ID, "")
        }
        set(value){
            getInstance().let {
                SharedPrefsTool.put(it, BACKEND_DIALOG_ID, value)
            }
        }

}


