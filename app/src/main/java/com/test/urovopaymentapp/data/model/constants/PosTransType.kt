package com.test.urovopaymentapp.data.model.pos2.constants

import android.content.Context
import com.test.urovopaymentapp.R

object PosTransType {
    const val POS_NONE: Int = 0

    /** Consumption  */
    const val POS_SALE: Int = 1

    /** Pre-authorization  */
    const val POS_PREAUTH: Int = 2

    /** Pre-authorization completed  */
    const val POS_AUTH_CM: Int = 3

    /** Pre-authorization revocation  */
    const val POS_PREAUTH_VOID: Int = 4

    /** Pre-authorization completes revocation  */
    const val POS_AUTH_VOID: Int = 5

    /** Consumption Cancellation  */
    const val POS_SALE_VOID: Int = 6

    /** Return the next day  */
    const val POS_REFUND: Int = 7

    /**Add pre-authorization  */
    const val POS_PREAUTH_ADD: Int = 8

    /** Check balance  */
    const val POS_QUE: Int = 9

    /** Card stop payment query  */
    const val POS_STOP_CARD: Int = 10

    /** Straighten  */
    const val POS_REVERSE: Int = 11

    /** POS check-in  */
    const val POS_LOGON: Int = 12

    /** Points sign-in  */
    const val POTINT_LOGON: Int = 13

    /** Online check-out  */
    const val POS_LOGOFF: Int = 14

    /** Settlement  */
    const val POS_SETT: Int = 15

    /** Online batch upload start and transmission  */
    const val POS_BATCH_UP: Int = 16

    /** Parameter download and transfer  */
    const val POS_DOWNLOAD_PARAM: Int = 17

    /** POS uploads terminal parameters  */
    const val POS_UPLOAD_PARAM: Int = 18

    /** POS response test  */
    const val RESPONSE_TEST: Int = 19

    /** Offline settlement  */
    const val OFF_SALE: Int = 20

    /** Settlement adjustment  */
    const val OFF_ADJUST: Int = 21

    /** Settlement adjustment tip  */
    const val ADJUST_TIP: Int = 22

    /** Pre-authorization is completed in offline mode, which is a notification message  */
    const val POS_OFF_CONFIRM: Int = 23

    /** Batch upload offline settlement  */
    const val OFF_SALE_BAT: Int = 24

    /** Batch upload settlement adjustment  */
    const val OFF_ADJUST_BAT: Int = 25

    /** Batch upload settlement adjustment  */
    const val ADJUST_TIP_BAT: Int = 26

    /** Batch delivery and next day return  */
    const val POS_REFUND_BAT: Int = 27

    /** Batch upload completed offline  */
    const val OFF_CONFIRM_BAT: Int = 28

    /** Total transactions during the shift  */
    const val QUE_TOTAL: Int = 29

    /** Total number of transactions in the current batch  */
    const val QUE_BATCH_TOTAL: Int = 30

    /** Current batch details  */
    const val QUE_BATCH_MX: Int = 31

    /** When approving a certain transaction  */
    const val QUE_BATCH_ANY: Int = 32
    const val PRT_LAST: Int = 33 // 33
    const val PRT_ANY: Int = 34 // 34
    const val PRT_DETAIL: Int = 35 // 35
    const val PRT_TOTAL: Int = 36 // 36
    const val PRT_LAST_TOTAL: Int = 37 // 37
    // Operator management
    /** Operator management-operator sign-in  */
    const val TELLER_LOGON: Int = 38

    /** Operator management-Operator sign-out  */
    const val TELLER_LOGOFF: Int = 39

    /** Operator management-POS transaction query  */
    const val POS_TRANS_CHK: Int = 40

    /** installment  */
    const val INSTALLMENT_SALE: Int = 41

    /** Cancellation by installments  */
    const val INSTALLMENT_VOID: Int = 42

    /** Return in installments  */
    const val INSTALLMENT_REFUND: Int = 43

    /** Download project number  */
    const val DownloadProjectCode: Int = 44

    /** IC card public key download  */
    const val DOWNLOAD_EMV_CAPK: Int = 48

    /** EMV parameter download  */
    const val DOWNLOAD_EMV_PARAM: Int = 49

    /** Blacklist download  */
    const val DOWNLOAD_BLACK_LIST: Int = 50

    /** Blacklist download ends  */
    const val DOWNLOAD_BLACK_LIST_END: Int = 51

    /** Query IC card public key  */
    const val QUERY_EMV_CAPK: Int = 52

    /** Query IC card parameters  */
    const val QUERY_EMV_PARAM: Int = 53

    /** End IC card public key download  */
    const val END_EMV_CAPK: Int = 54

    /** End IC card parameter download  */
    const val END_EMV_PARAM: Int = 55

    const val POS_LAST_PRINT: Int = 51
    const val POS_ANY_PRINT: Int = 52
    const val POS_DETAIL_PRINT: Int = 53
    const val POS_TOTAL_PRINT: Int = 54
    const val POS_SETTLE_PRINT: Int = 55

    /** Upload script results  */
    const val UPLOAD_SCRIPT_RESULT: Int = 56

    const val CHK_TRANS_TRACES: Int = 57
    const val CHK_TRANS_DETAIL: Int = 58

    /** q payment upload  */
    const val EC_QUICK_UPLOAD: Int = 59

    /** IC card online transactions are sent  */
    const val ICC_ONLINE_BP: Int = 60

    /** qpboc contactless consumption  */
    const val EC_QUICK_PAY: Int = 66

    /** Contact IC card electronic cash transaction  */
    const val EC_ORDINARY_PAY: Int = 67

    /** qpboc check balance  */
    const val EC_QUICK_BLANCE: Int = 68

    /** Contact the IC card to check the balance  */
    const val EC_ORDINARY_BLANCE: Int = 69

    /** qpboc return  */
    const val EC_QUICK_RETURN: Int = 68
    // public static final int EC_QUICK_ = 68; // qpboc check balance
    /**Change password for prepaid card */
    const val CHANGE_PASSWORD: Int = 681

    /** Subsequent processing after settlement  */
    const val AFTER_SETTLE: Int = 68

    /** TC value is sent  */
    const val SEND_TCVALUES: Int = 69

    /** Script upload  */
    const val SEND_SCRIPT: Int = 70

    /**
     * DCC to EDC  */
    const val POS_DCC_TO_EDC: Int = 71

    /** Manual pre-authorization completed Swipe card  */
    const val POS_AUTH_CM_HAND: Int = 1003

    /** Manual pre-authorization revocation  */
    const val POS_PREAUTH_VOID_HAND: Int = 1004

    /** Manual pre-authorization completes revocation  */
    const val POS_AUTH_VOID_HAND: Int = 1005

    /** Manual pre-authorization completes revocation  */
    const val POS_AUTH_QUERY: Int = 1006

    /** Pre-authorization query revocation  */
    const val POS_AUTH_VOID_QUERY: Int = 1007

    /** Pre-authorization query completed  */
    const val POS_AUTH_CM_QUERY: Int = 1008

    /** Order consumption  */
    const val POS_SALE_ORDER: Int = 1009

    /** Pre-authorization completion notification  */
    const val POS_AUTH_CM_T: Int = 1010

    const val YH_GET_CAR: Int = 2001 // Used for YiHi car pickup
    const val YH_ALSO_CAR: Int = 2002 // used for YiHi return car
    const val YH_QUERY: Int = 2003 // used for YiHi query
    const val YH_PRICE_QUERY: Int = 2004 // Used for YiHi price query
    const val YH_PAYAFFIRM_ZF: Int = 2005 // Used for eHi payment confirmation
    const val YH_QIANMIN: Int = 2006 // Used for YiHi signature submission
    const val YH_PAYAFFIRM_CZ: Int = 2007 // used for eHi payment confirmation
    const val ELECTRONICSIGNATURE: Int = 2008 // Electronic signature

    /** Transfer to designated account  */
    const val EC_QC: Int = 2009

    /** Coupon verification  */
    const val COUPONS: Int = 2010

    /** Contactless parameter download  */
    const val EC_PARAM: Int = 2011

    /** Transfer to non-designated account  */
    const val EC_QC_NO: Int = 2014

    /** Electronic cash recharge  */
    const val electronicTopUp: Int = 2012

    /** Electronic cash recharge cancellation  */
    const val electronicTopUpVoid: Int = 2013

    /** UnionPay wallet electronic ticket  */
    const val electronicTicket: Int = 2015

    /** UnionPay wallet points  */
    const val INTRGAL: Int = 2016

    /** UnionPay wallet cancellation  */
    const val WALLERVOID: Int = 2017

    /** UnionPay wallet return  */
    const val WALLERRETURN: Int = 2018

    /** UnionPay wallet coupon consumption  */
    const val WALLETCOUPONSALE: Int = 2019

    /** POS parameter download  */
    const val DOWNLOADPARAM: Int = 2020

    /** UnionPay Wallet-Coupon  */
    const val UMS_Wallet_Coupon: Int = 2025

    /** UnionPay Wallet-Electronic Ticket  */
    const val UMS_Wallet_Electronic: Int = 2026

    /** UnionPay Wallet-Points  */
    const val UMS_Wallet_Point: Int = 2027

    /** Last query  */
    const val POS_LAST_QUE: Int = 2028

    /** Alipay scan code payment  */
    const val QR_CODE_ZHIFUBAO: Int = 2021

    /** WeChat scan code payment  */
    const val QR_CODE_WEIXIN: Int = 2022

    /** Other scan code payment  */
    const val QR_CODE_OTHER: Int = 2023

    /** Alipay QR code payment  */
    const val QR_CODE_ZHIFUBAO2: Int = 20210

    /** WeChat QR code payment  */
    const val QR_CODE_WEIXIN2: Int = 20220

    /** Other QR code payment  */
    const val QR_CODE_OTHER2: Int = 2024

    /** Alipay scan code payment cancellation  */
    const val QRCODE_CANCEL_ZHIFUBAO: Int = 2029

    /** WeChat scan code payment cancellation  */
    const val QRCODE_CANCEL_WEIXIN: Int = 2030

    /** Other scan code payment cancellations  */
    const val QRCODE_CANCEL_OTHER: Int = 2031

    /** Alipay scan code to pay for returns  */
    const val QRCODE_RETURN_ZHIFUBAO: Int = 2032

    /** Scan the WeChat QR code to pay for returns  */
    const val QRCODE_RETURN_WEIXIN: Int = 2033

    /** Other scan code payment returns  */
    const val QRCODE_RETURN_OTHER: Int = 2034

    /** Alipay scan code payment inquiry  */
    const val QRCODE_QUE_ZHIFUBAO: Int = 2035

    /** WeChat scan code payment query  */
    const val QRCODE_QUE_WEIXIN: Int = 2036

    /** Alipay scanned payment query  */
    const val QRCODE_QUE_ZHIFUBAO2: Int = 20350

    /** WeChat scanned payment query  */
    const val QRCODE_QUE_WEIXIN2: Int = 20360

    /** Other scan code payment inquiries  */
    const val QRCODE_QUE_OTHER: Int = 2037

    /** Baidu Wallet Payment  */
    const val QR_CODE_BAIDU: Int = 2038

    /** Baidu Wallet Cancellation  */
    const val QRCODE_CANCEL_BAIDU: Int = 2039

    /** Baidu Wallet Return  */
    const val QRCODE_RETURN_BAIDU: Int = 2040

    /** Cash recharge  */
    const val CASH_PREPAY: Int = 2041

    /** change Password  */
    const val POS_CHANGE_PWD: Int = 2042

    /** Card and coupon code query (used for the last query of the card and coupon)  */
    const val POS_COUPON_QUE: Int = 2045

    /** Bank card recharge consumption  */
    const val POS_SALE_CHARGE: Int = 2046

    /** Fund collection  */
    const val POS_CASH_SWEEP: Int = 2047

    /** Account verification  */
    const val POS_ACCOUNT_VALIDATION: Int = 2048

    /** Inter-bank transfer */
    const val POS_ACCOUNT_TRANSFER: Int = 2049

    /** Points query */
    const val INTEGRATE_QUERY: Int = 2050

    /** Redeem */
    const val INTEGRATE_EXCAANGE: Int = 2051

    /** Order Tracking */
    const val ORDER_QUERY: Int = 2052

    /** Order consumption */
    const val ORDER_SALE: Int = 2053

    /** Order consumption cancellation */
    const val ORDER_SALE_VOID: Int = 2054

    /** Delivery coupon query */
    const val DELIVERY_QUERY: Int = 2055

    /** Delivery voucher verification */
    const val DELIVERY_SALE: Int = 2056

    /** Consumption cancellation of delivery coupon */
    const val DELIVERY_SALE_VOID: Int = 2057

    /** Coupon consumption */
    const val COUPON_SALE: Int = 2058

    /** Coupon consumption cancellation */
    const val COUPON_SALE_VOID: Int = 2059

    /** DCC to EDC */
    const val POS_DCCTOEDC: Int = 2060

    /** WeChat payment cancellation  */
    const val SCAN_WX_CANCEL: Int = 2061

    /** Alipay payment cancellation  */
    const val SCAN_ALIPAY_CANCEL: Int = 2062

    /** Mini payment order request  */
    const val Union_MiniPay_OrderRequest: Int = 2063

    /** Mini payment order query  */
    const val Union_MiniPay_OrderQuery: Int = 2064

    /**
     * Read card
     */
    const val POS_READ_CARD: Int = 2065

    fun getPosTransTypeName(context: Context, posTransType: Int): String {
        val posTransTypeName = context.getString(R.string.unknown)
        when (posTransType) {
            POS_SALE -> return context.getString(R.string.sale)
            POS_QUE -> return context.getString(R.string.balance)
            POS_PREAUTH -> return context.getString(R.string.preaAuth)
            POS_AUTH_CM -> return context.getString(R.string.preaAuth_comp)
            POS_PREAUTH_VOID -> return context.getString(R.string.preaAuth_revoke)
            POS_AUTH_VOID -> return context.getString(R.string.preaAuth_comp_revoke)
            POS_SALE_VOID -> return context.getString(R.string.revoke)
            POS_REFUND -> return context.getString(R.string.refund)
            POS_LOGON -> return context.getString(R.string.sign_in)
            POS_LOGOFF -> return context.getString(R.string.sign_out)
            POS_SETT -> return context.getString(R.string.settle)
            QR_CODE_OTHER -> return context.getString(R.string.scan_pay)
            QRCODE_CANCEL_OTHER -> return context.getString(R.string.scan_revoke)
            QRCODE_RETURN_OTHER -> return context.getString(R.string.scan_refund)
        }
        return posTransTypeName
    }

    fun getEMVTransType(transType: Int): Int {
        var TransType = 7

        when (transType) {
            POS_SALE -> TransType = 7
            POS_SALE_VOID -> TransType = 11
            POS_QUE -> TransType = 9
            POS_PREAUTH -> TransType = 10
            else -> {}
        }
        return TransType
    }
}
