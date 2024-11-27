package com.test.urovopaymentapp.data.local.preferences

import android.content.SharedPreferences
import com.test.urovopaymentapp.MyApp
import com.test.urovopaymentapp.data.model.pos2.constants.SharedPrefKeys

/**
 * Payment application parameter settings
 * @author LAIMIGN
 */
object ConfigUtils {

    fun getInstance(): SharedPreferences {
        return MyApp.getInstance()
            .let {
                SharedPrefsTool.getInstances(
                    context = it.applicationContext,
                    SharedPrefsTool.SYSTEM_CONFIG_PREFS_NAME
                )
            }
    }

    var initCAPKFlag: Boolean
        /** Get the flag of the initialized CAPK (IC card public key) table
         * @return true has been initialized, false has not been initialized
         */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.InitCAPKFlag, false)
        }

    //configPrefsTool.getBoolean(SharedPrefKeys.InitCAPKFlag, false)
        /**Set the flag to initialize the CAPK (IC card public key) table
         * @param flag true has been initialized, false has not been initialized
         */
        set(flag) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.InitCAPKFlag, flag)
            }
        }


    var initPOSParamsFlag: Boolean
        /** Get the flag of the initialized POS parameter (EMV) table
         * @return true if initialized, false otherwise
         */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.InitPOSParamsFlag, false)
        }
        /** Set the flag for initializing the POS parameter (EMV) table
         * @param flag true if initialized, false otherwise
         */
        set(flag) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.InitPOSParamsFlag, flag)
            }
        }

    var initAIDFlag: Boolean
        /** Get the flag to initialize the AID table
         * @return true if initialized, false otherwise
         */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.InitAIDFlag, false)
        }
        /** Set the flag for initializing the AID table
         * @param flag true if initialized, false otherwise
         */
        set(flag) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.InitAIDFlag, flag)
            }
        }

    var initCardBinFlag: Boolean
        /** Get the flag to initialize the CardBin table
         * @return true if initialized, false otherwise
         */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.InitCardBinFlag, false)
        }
        /** Set the flag to initialize the CardBin table
         * @param flag true if initialized, false otherwise
         */
        set(flag) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.InitCardBinFlag, flag)
            }
        }

    var initUrovoUserFlag: Boolean
        /** Get the flag to initialize the UrovoUser table
         * @return true if initialized, false otherwise
         */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.InitUrovoUserFlag, false)
        }
        /** Set the flag to initialize the UrovoUser table
         * @param flag true if initialized, false otherwise
         */
        set(flag) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.InitUrovoUserFlag, flag)
            }
        }

    var currentMerchantID: String?
        /** Get the ID of the current merchant
         */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.CurrentMerchtID, "1")
        }
        /** Set the ID of the current merchant (can be used to switch merchants)
         * @param currentMerchantID
         */
        set(currentMerchantID) {
            if (!currentMerchantID.isNullOrEmpty()) {
                getInstance().let {
                    SharedPrefsTool.put(it, SharedPrefKeys.CurrentMerchtID, currentMerchantID)
                }
            }
        }

}
