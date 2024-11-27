package com.test.urovopaymentapp.data.local.preferences

import android.content.SharedPreferences
import com.test.urovopaymentapp.MyApp
import com.test.urovopaymentapp.data.model.pos2.constants.SharedPrefKeys

/**
 * Merchant parameters currently in use
 *
 * @author KuCoffee
 */
object MerchantParams {

    fun getInstance(): SharedPreferences {
        return MyApp.getInstance()
            .let {
                SharedPrefsTool.getInstances(
                    context = it.applicationContext,
                    SharedPrefsTool.MERCHANT_PREFS_NAME
                )
            }
    }

    // /**Get the merchant object currently in use
    // * @return
    // */

    var indexMAC: String?
        /**
         * Get the index of MAC Key
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.Index_MAC, "4") }
        /**
         * Set the index of MAC Key
         *
         * @param indexMac
         */
        set(indexMac) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.Index_MAC, indexMac!!) }
        }

    var indexMAC2: String?
        /**
         * Get the index of MAC Key2
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.Index_MAC2, "5") }
        /**
         * Set the index of MAC Key2
         *
         * @param indexMac2
         */
        set(indexMac2) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.Index_MAC2, indexMac2!!) }
        }

    var indexMAC3: String?
        /**
         * Get the index of MAC Key3
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.Index_MAC3, "6") }
        /**
         * Set the index of MAC Key3
         *
         * @param indexMac3
         */
        set(indexMac3) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.Index_MAC3, indexMac3!!) }
        }

    var indexMasterKey: String?
        /**
         * Get the index of MasterKey
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.Index_MK, "1") }
        /**
         * Set the index of MasterKey
         *
         * @param indexMK
         */
        set(indexMK) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.Index_MK, indexMK!!) }
        }

    var indexENCK: String?
        /**
         * Get the index of EncryptKey
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.Index_ENCK, "1") }
        /**
         * Set the index of EncryptKey
         *
         * @param indexENCK
         */
        set(indexENCK) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.Index_ENCK, indexENCK!!) }
        }

    var indexPinKey: String?
        /**
         * Get the index of the password key
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.Index_PINKey, "1") }
        /**
         * Set the index of the password key
         *
         * @param indexPinKey
         */
        set(indexPinKey) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.Index_PINKey, indexPinKey!!) }
        }

    var tPDU: String?
        /**
         * Get TPDU
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.CS_Tpdu, "6000000000") }
        /**
         * Set TPDU
         *
         * @param TPDU
         */
        set(TPDU) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.CS_Tpdu, TPDU!!) }
        }

    var traceNum: String
        /**
         * Get serial number
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.SS_TrackingNum, "000001") } ?: ""
        /**
         * Set serial number
         *
         * @param traceNum
         */
        set(traceNum) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.SS_TrackingNum, traceNum) }
        }

    var batchNum: String
        /**
         * Get the batch number
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.SS_BatchNum, "000001") } ?: ""
        /**
         * Set batch number
         *
         * @param batchNum
         */
        set(batchNum) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.SS_BatchNum, batchNum) }
        }

    var terminalNum: String?
        /**
         * Get terminal number
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.BS_TerminalNum, "") }
        /**
         * Set terminal number
         *
         * @param terminalNum
         */
        set(terminalNum) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.BS_TerminalNum, terminalNum!!) }
        }

    var merchantNum: String?
        /**
         * Get merchant number
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.BS_MerchantNum, "") }
        /**
         * Set merchant number
         *
         * @param merchantNum
         */
        set(merchantNum) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.BS_MerchantNum, merchantNum!!) }
        }

    var merchantName: String?
        /**
         * Get merchant name (Chinese)
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.BS_MerchantCNName, "") }
        /**
         * Set merchant name (Chinese)
         *
         * @param merchantName
         */
        set(merchantName) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.BS_MerchantCNName, merchantName!!) }

        }

    var merchantEngName: String?
        /**
         * Get the English name of the merchant
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.BS_MerchantEngName, "") }
        /**
         * Set the English name of the merchant
         *
         * @param engName
         */
        set(engName) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.BS_MerchantEngName, engName!!) }

        }

    var commWaitTime: String?
        /**
         * Get communication waiting time
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.CS_WaitTime, "60") }
        /**
         * Set communication waiting time
         *
         * @param time
         */
        set(time) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.CS_WaitTime, time!!) }
        }

    var reversalTimes: String?
        /**
         * Get the number of reversals
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.SS_ReversalTimes, "3") }
        /**
         * Set the number of reversals
         *
         * @param reversalTimes
         */
        set(reversalTimes) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.SS_ReversalTimes, reversalTimes!!) }
        }

    var serverIP: String?
        /**
         * Get server IP address
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.CS_EthernetServerIp, "") }
        /**
         * Set server IP address
         *
         * @param ip
         */
        set(ip) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.CS_EthernetServerIp, ip!!) }
        }

    var serverPort: String?
        /**
         * Get server port
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.CS_EthernetServerPort, "") }
        /**
         * Set server port
         *
         * @param port
         */
        set(port) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.CS_EthernetServerPort, port!!) }
        }

    var maxTransactNum: String?
        /**
         * Get the maximum number of transactions (maximum number of settlements)
         *
         * @return
         */
        get() =
            getInstance()
                .let { SharedPrefsTool.get(it, SharedPrefKeys.SS_MaxTransactions, "") }
        /**
         * Set the maximum number of transactions (maximum number of settlements)
         *
         * @param transactNum
         */
        set(transactNum) {
            getInstance()
                .let { SharedPrefsTool.put(it, SharedPrefKeys.SS_MaxTransactions, transactNum!!) }
        }


    // Transaction management settings
    val dsSale: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support consumer transactions  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_Sale, true) } ?: true

    val dSSaleRevocate: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support consumption cancellation transactions  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_SaleRevocate, true) } ?: true

    val dsSaleReturn: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support return transactions  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_SaleReturn, true) } ?: true

    val dsBalanceQuery: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support balance inquiry transactions.  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_BalanceQuery, true) } ?: true

    val dSPreAuth: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support pre-authorized transactions.  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_PreAuth, true) } ?: true

    val dsPreAuthRevocate: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization revocation.  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_PreAuthRevocate, true) } ?: true


    val dSPreAuthDoneRequest: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion requests.  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_PreAuthDoneRequest, true) } ?: true


    val dsPreAuthDoneNotify: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion notifications.  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_PreAuthDoneNotify, true) } ?: true

    val dsPreAuthDoneRevocate: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion revocation.  */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_PreAuthDoneRevocate, true) } ?: true

    val dsSaleRevocatePwd: Boolean
        /**Transaction management settings - transaction password input control - used to set whether to support consumption cancellation and whether to input password */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_SaleRevocatePwd, false) } ?: false

    val dSPreRevocatePwd: Boolean
        /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization revocation and whether to input password */
        get() = getInstance()
            .let { SharedPrefsTool.get(it, SharedPrefKeys.DS_PreRevocatePwd, false) } ?: false

    val dSPreAuthDoneRevocatePwd: Boolean
        /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization, complete revocation and whether to input password */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.DS_PreAuthDoneRevocatePwd, false)
        } ?: false


    var userName: String
        /**Get the administrator operator number when logging in */
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.BS_UserName, "01")
        } ?: ""
        /**Save the administrator operator number when logging in */
        set(username) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.BS_UserName, username)
            }
        }

    var userPhone: String
        /**Get user login user mobile phone number*/
        get() = getInstance().let {
            SharedPrefsTool.get(it, SharedPrefKeys.BS_UserPhone, "")
        } ?: ""
        /**Set up user and log in user’s mobile phone number*/
        set(value) {
            getInstance().let {
                SharedPrefsTool.put(it, SharedPrefKeys.BS_UserPhone, value)
            }
        }
}
