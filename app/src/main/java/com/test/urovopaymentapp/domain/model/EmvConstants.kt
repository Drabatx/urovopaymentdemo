package com.test.urovopaymentapp.domain.model

import com.urovo.i9000s.api.emv.ContantPara
import java.util.Hashtable

object EmvConstants {
    const val CHECK_CARD_MODE = "checkCardMode"
    const val CURRENCY_CODE = "currencyCode"
    const val EMV_OPTION = "emvOption"
    const val AMOUNT = "amount"
    const val CASH_BACK_AMOUNT = "cashbackAmount"
    const val CHECK_CARD_TIMEOUT = "checkCardTimeout"
    const val TRANSACTION_TYPE = "transactionType"
    const val IS_ENTER_AMT_AFTER_READ_RECORD = "isEnterAmtAfterReadRecord"
    const val SUPPORT_DRL = "supportDRL"

    const val CARD_TYPE_MAG = 0
    const val CARD_TYPE_IC = 1
    private var transType = 7

    fun getEMVInstance(): Hashtable<String, Any> {
        val data = Hashtable<String, Any>()
        data[CURRENCY_CODE] = "682" //682
        data[EMV_OPTION] = ContantPara.EmvOption.START // START_WITH_FORCE_ONLINE
        data[CASH_BACK_AMOUNT] = "0"
        data[CHECK_CARD_TIMEOUT] = "30" // Check Card time out .Second
        data[TRANSACTION_TYPE] = "00" //00-goods 01-cash 09-cashback 20-refund
        data[IS_ENTER_AMT_AFTER_READ_RECORD] = false
        data[SUPPORT_DRL] = true // support Visa DRL?
        return data
    }
}