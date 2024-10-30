package com.drabatx.urovopaymentapp.data.model.models.merchant

import com.drabatx.urovopaymentapp.data.model.pos2.constants.SharedPrefKeys
import javax.inject.Inject

/**
 * Merchant parameters currently in use
 *
 * @author KuCoffee
 */
class MerchantParams @Inject constructor(private val merchantPrefsTool: MerchantPrefsTool){
    // /**Get the merchant object currently in use
    // * @return
    // */
    // protected static MerchantInfo getCurrentMerchant() {
    // return PosApplication.getInstance().getCurrentMerchant();
    // }
    var indexMAC: String?
        /**
         * Get the index of MAC Key
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getIndex_MAC();
            merchantPrefsTool.getString(SharedPrefKeys.Index_MAC, "4")
        /**
         * Set the index of MAC Key
         *
         * @param indexMac
         */
        set(indexMac) {
            merchantPrefsTool.putString(SharedPrefKeys.Index_MAC, indexMac)
        }

    var indexMAC2: String?
        /**
         * Get the index of MAC Key2
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getIndex_MAC2();
            merchantPrefsTool.getString(SharedPrefKeys.Index_MAC2, "5")
        /**
         * Set the index of MAC Key2
         *
         * @param indexMac2
         */
        set(indexMac2) {
            merchantPrefsTool.putString(SharedPrefKeys.Index_MAC2, indexMac2)
        }

    var indexMAC3: String?
        /**
         * Get the index of MAC Key3
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getIndex_MAC3();
            merchantPrefsTool.getString(SharedPrefKeys.Index_MAC3, "6")
        /**
         * Set the index of MAC Key3
         *
         * @param indexMac3
         */
        set(indexMac3) {
            merchantPrefsTool.putString(SharedPrefKeys.Index_MAC3, indexMac3)
        }

    var indexMasterKey: String?
        /**
         * Get the index of MasterKey
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getIndex_MK();
            merchantPrefsTool.getString(SharedPrefKeys.Index_MK, "1")
        /**
         * Set the index of MasterKey
         *
         * @param indexMK
         */
        set(indexMK) {
            merchantPrefsTool.putString(SharedPrefKeys.Index_MK, indexMK)
        }

    var indexENCK: String?
        /**
         * Get the index of EncryptKey
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getIndex_ENCK();
            merchantPrefsTool.getString(SharedPrefKeys.Index_ENCK, "1")
        /**
         * Set the index of EncryptKey
         *
         * @param indexENCK
         */
        set(indexENCK) {
            merchantPrefsTool.putString(SharedPrefKeys.Index_ENCK, indexENCK)
        }

    var indexPinKey: String?
        /**
         * Get the index of the password key
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getIndex_PinKey();
            merchantPrefsTool.getString(SharedPrefKeys.Index_PINKey, "1")
        /**
         * Set the index of the password key
         *
         * @param indexPinKey
         */
        set(indexPinKey) {
            merchantPrefsTool.putString(SharedPrefKeys.Index_PINKey, indexPinKey)
        }

    var tPDU: String?
        /**
         * Get TPDU
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getTPDU();
            merchantPrefsTool.getString(SharedPrefKeys.CS_Tpdu, "6000000000")
        /**
         * Set TPDU
         *
         * @param TPDU
         */
        set(TPDU) {
            merchantPrefsTool.putString(SharedPrefKeys.CS_Tpdu, TPDU)
        }

    var traceNum: String?
        /**
         * Get serial number
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getTraceNum();
            merchantPrefsTool.getString(
                SharedPrefKeys.SS_TrackingNum,
                "000001"
            )
        /**
         * Set serial number
         *
         * @param traceNum
         */
        set(traceNum) {
            merchantPrefsTool.putString(SharedPrefKeys.SS_TrackingNum, traceNum)
        }

    var batchNum: String?
        /**
         * Get the batch number
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getBatchNum();
            merchantPrefsTool.getString(SharedPrefKeys.SS_BatchNum, "000001")
        /**
         * Set batch number
         *
         * @param batchNum
         */
        set(batchNum) {
// MerchantInfo merchantInfo = getCurrentMerchant();
// merchantInfo.setBatchNum(batchNum);
// MerchantInfoBll.getInstance().update(merchantInfo);
            merchantPrefsTool.putString(SharedPrefKeys.SS_BatchNum, batchNum)
        }

    var terminalNum: String?
        /**
         * Get terminal number
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getTerminalNum();
            merchantPrefsTool.getString(SharedPrefKeys.BS_TerminalNum, "")
        /**
         * Set terminal number
         *
         * @param terminalNum
         */
        set(terminalNum) {
            merchantPrefsTool.putString(SharedPrefKeys.BS_TerminalNum, terminalNum)
        }

    var merchantNum: String?
        /**
         * Get merchant number
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getMerchantNum();
            merchantPrefsTool.getString(SharedPrefKeys.BS_MerchantNum, "")
        /**
         * Set merchant number
         *
         * @param merchantNum
         */
        set(merchantNum) {
            merchantPrefsTool.putString(SharedPrefKeys.BS_MerchantNum, merchantNum)
        }

    var merchantName: String?
        /**
         * Get merchant name (Chinese)
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getChineseName();
            merchantPrefsTool
                .getString(SharedPrefKeys.BS_MerchantCNName, "")
        /**
         * Set merchant name (Chinese)
         *
         * @param merchantName
         */
        set(merchantName) {
            merchantPrefsTool.putString(
                SharedPrefKeys.BS_MerchantCNName,
                merchantName
            )
        }

    var merchantEngName: String?
        /**
         * Get the English name of the merchant
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getEnglishName();
            merchantPrefsTool.getString(
                SharedPrefKeys.BS_MerchantEngName,
                ""
            )
        /**
         * Set the English name of the merchant
         *
         * @param engName
         */
        set(engName) {
            merchantPrefsTool.putString(SharedPrefKeys.BS_MerchantEngName, engName)
        }

    var commWaitTime: String?
        /**
         * Get communication waiting time
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
// return merchantInfo.getCommTimeout();
            merchantPrefsTool.getString(SharedPrefKeys.CS_WaitTime, "60")
        /**
         * Set communication waiting time
         *
         * @param time
         */
        set(time) {
            merchantPrefsTool.putString(SharedPrefKeys.CS_WaitTime, time)
        }

    var reversalTimes: String?
        /**
         * Get the number of reversals
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getReversalTimes();
            merchantPrefsTool.getString(SharedPrefKeys.SS_ReversalTimes, "3")
        /**
         * Set the number of reversals
         *
         * @param reversalTimes
         */
        set(reversalTimes) {
            merchantPrefsTool.putString(
                SharedPrefKeys.SS_ReversalTimes,
                reversalTimes
            )
        }

    var serverIP: String?
        /**
         * Get server IP address
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getIP();
            merchantPrefsTool.getString(
                SharedPrefKeys.CS_EthernetServerIp,
                ""
            )
        /**
         * Set server IP address
         *
         * @param IP
         */
        set(IP) {
            merchantPrefsTool.putString(SharedPrefKeys.CS_EthernetServerIp, IP)
        }

    var serverPort: String?
        /**
         * Get server port
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getPort();
            merchantPrefsTool.getString(
                SharedPrefKeys.CS_EthernetServerPort, ""
            )
        /**
         * Set server port
         *
         * @param port
         */
        set(port) {
            merchantPrefsTool.putString(SharedPrefKeys.CS_EthernetServerPort, port)
        }

    val maxTransactNum: String
        /**
         * Get the maximum number of transactions (maximum number of settlements)
         *
         * @return
         */
        get() =// MerchantInfo merchantInfo = getCurrentMerchant();
            // return merchantInfo.getMaxTransactNum();
            merchantPrefsTool.getString(
                SharedPrefKeys.SS_MaxTransactions,
                ""
            )

    /**
     * Set the maximum number of transactions (maximum number of settlements)
     *
     * @param transactNum
     */
    fun setMaxTransactNum(transactNum: String?): Boolean {
        return merchantPrefsTool.putString(
            SharedPrefKeys.SS_MaxTransactions,
            transactNum
        )
    }

    // Transaction management settings
    val dsSale: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support consumer transactions  */
        get() = merchantPrefsTool.getBoolean(SharedPrefKeys.DS_Sale, true)

    val dSSaleRevocate: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support consumption cancellation transactions  */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_SaleRevocate,
            true
        )

    val dsSaleReturn: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support return transactions  */
        get() = merchantPrefsTool.getBoolean(SharedPrefKeys.DS_SaleReturn, true)

    val dsBalanceQuery: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support balance inquiry transactions.  */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_BalanceQuery,
            true
        )

    val dSPreAuth: Boolean
        /** Transaction management settings - traditional transactions - used to set whether to support pre-authorized transactions.  */
        get() = merchantPrefsTool.getBoolean(SharedPrefKeys.DS_PreAuth, true)

    val dsPreAuthRevocate: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization revocation.  */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_PreAuthRevocate,
            true
        )

    val dSPreAuthDoneRequest: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion requests.  */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_PreAuthDoneRequest, true
        )

    val dsPreAuthDoneNotify: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion notifications.  */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_PreAuthDoneNotify, true
        )

    val dsPreAuthDoneRevocate: Boolean
        /** Transaction management settings—traditional transactions—are used to set whether to support pre-authorization completion revocation.  */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_PreAuthDoneRevocate, true
        )

    val dsSaleRevocatePwd: Boolean
        /**Transaction management settings - transaction password input control - used to set whether to support consumption cancellation and whether to input password */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_SaleRevocatePwd, false
        )

    val dSPreRevocatePwd: Boolean
        /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization revocation and whether to input password */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_PreRevocatePwd, false
        )

    val dSPreAuthDoneRevocatePwd: Boolean
        /**Transaction management settings - transaction password input control - used to set whether to support pre-authorization, complete revocation and whether to input password */
        get() = merchantPrefsTool.getBoolean(
            SharedPrefKeys.DS_PreAuthDoneRevocatePwd, false
        )


    val userName: String
        /**Get the administrator operator number when logging in */
        get() = merchantPrefsTool.getString(SharedPrefKeys.BS_UserName, "01")

    /**Save the administrator operator number when logging in */
    fun setUserName(userName: String?): Boolean {
        return merchantPrefsTool.putString(SharedPrefKeys.BS_UserName, userName)
    }

    /**Set up user and log in user’s mobile phone number */
    fun setUserPhone(userPhone: String?): Boolean {
        return merchantPrefsTool.putString(SharedPrefKeys.BS_UserPhone, userPhone)
    }

    val userPhone: String
        /**Get user login user mobile phone number */
        get() = merchantPrefsTool.getString(SharedPrefKeys.BS_UserPhone, "")
}
