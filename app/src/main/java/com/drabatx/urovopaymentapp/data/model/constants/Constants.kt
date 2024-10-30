package com.drabatx.urovopaymentapp.data.model.pos2.constants

object Constants {
    /** Interface  */
    var interface_login: String = "LOGIN"
    var interface_modifyPassword: String = "MPWD" //Modify password
    var interface_pay: String = "PUR" // Payment
    var interface_queryConsume: String = "QRY" //Consumption query
    var interface_revokeDeal: String = "VTX" // Transaction revocation
    var interface_returnGoods: String = "RFD" //Transaction returns
    var interface_queryDealList: String = "QRYLIST" // Transaction list query
    var interface_queryDealDetail: String = "QRYDETAIL" // Transaction details

    /**
     * Payment Types
     */
    var WECHAT: String = "WECHAT" // WeChat
    var WECHATCSB: String = "WECHATSB" // WeChat c scan b
    var BILL99: String = "99BILL"
    var BILL99CSB: String = "99BILLCSB"
    var BAIDUCSB: String = "BAIDUCSB"

    /**
     * Operation type
     */
    var oper_consume: String = "PUR"
    var oper_revoke: String = "VTX"
    var oper_return_goods: String = "RFD"
    var oper_all: String = "ALL"

    /**
     * Other parameters
     */
    const val reference_length: Int = 12 //Reference number length
    const val merchant_length: Int = 15 //Merchant number length
    const val password_length: Int = 8 //Minimum password length
    const val terminal_length: Int = 8 //Terminal number length

    /**TPDU length */
    const val TPDU_Length: Int = 10

    /**Batch number length */
    const val BatchNum_Length: Int = 6

    /**Voucher number length */
    const val TraceNum_Length: Int = 6
}
