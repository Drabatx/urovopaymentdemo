package com.drabatx.urovopaymentapp.data.model.models.merchant

import android.text.TextUtils
import com.drabatx.urovopaymentapp.data.model.pos2.constants.SharedPrefKeys
import javax.inject.Inject

/**
 * Payment application parameter settings
 * @author LAIMIGN
 */
class ConfigUtils @Inject constructor(private val configPrefsTool: ConfigPrefsTool) {
    var initCAPKFlag: Boolean
        /** Get the flag of the initialized CAPK (IC card public key) table
         * @return true has been initialized, false has not been initialized
         */
        get() = configPrefsTool.getBoolean(SharedPrefKeys.InitCAPKFlag, false)
        /**Set the flag to initialize the CAPK (IC card public key) table
         * @param flag true has been initialized, false has not been initialized
         */
        set(flag) {
            configPrefsTool.putBoolean(SharedPrefKeys.InitCAPKFlag, flag)
        }


    var initPOSParamsFlag: Boolean
        /** Get the flag of the initialized POS parameter (EMV) table
         * @return
         */
        get() = configPrefsTool.getBoolean(SharedPrefKeys.InitPOSParamsFlag, false)
        /**Set the flag for initializing the POS parameter (EMV) table
         * @param flag
         */
        set(flag) {
            configPrefsTool.putBoolean(SharedPrefKeys.InitPOSParamsFlag, flag)
        }
    var initAIDFlag: Boolean
        /**Get the flag to initialize the AID table
         * @return
         */
        get() = configPrefsTool.getBoolean(SharedPrefKeys.InitAIDFlag, false)
        /**Set the flag for initializing the AID table
         * @param flag
         */
        set(flag) {
            configPrefsTool.putBoolean(SharedPrefKeys.InitAIDFlag, flag)
        }
    var initCardBinFlag: Boolean
        /**Get the flag to initialize the CardBin table
         * @return
         */
        get() = configPrefsTool.getBoolean(SharedPrefKeys.InitCardBinFlag, false)
        /**Set the flag to initialize the CardBin table
         * @param flag
         */
        set(flag) {
            configPrefsTool.putBoolean(SharedPrefKeys.InitCardBinFlag, flag)
        }
    var initUrovoUserFlag: Boolean
        /**Get the flag to initialize the UrovoUser table
         * @return
         */
        get() = configPrefsTool.getBoolean(SharedPrefKeys.InitUrovoUserFlag, false)
        /**Set the flag to initialize the UrovoUser table
         * @param flag
         */
        set(flag) {
            configPrefsTool.putBoolean(SharedPrefKeys.InitUrovoUserFlag, flag)
        }
    var currentMerchantID: String?
        /** Get the ID of the current merchant
         */
        get() = configPrefsTool.getString(SharedPrefKeys.CurrentMerchtID, "1")
        /**Set the ID of the current merchant (can be used to switch merchants)
         * @param currentMerchantID
         */
        set(currentMerchantID) {
            if (!TextUtils.isEmpty(currentMerchantID)) {
                configPrefsTool.putString(SharedPrefKeys.CurrentMerchtID, currentMerchantID)
            }
        }
}
