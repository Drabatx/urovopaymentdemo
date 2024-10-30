package com.drabatx.urovopaymentapp.data.model.db

import android.content.ContentValues
import android.database.Cursor

class PayInfoModel {
    var id: String? = null
    var merchantId: String? = null
    var respTime: String? = null
    var payType: String? = null
    var amt: String? = null
    var traceNo: String? = null
    var reference: String? = null
    var dimensionCode: String? = null
    var terminalId: String? = null
    var isNeedRevoke: String? = null
    var cur: String? = null
    var reserve1: String? = null
    var reserve2: String? = null
    var reserve3: String? = null

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("id", id)
        values.put("merchantId", merchantId)
        values.put("respTime", respTime)
        values.put("payType", payType)
        values.put("amt", amt)
        values.put("traceNo", traceNo)
        values.put("reference", reference)
        values.put("dimensionCode", dimensionCode)
        values.put("terminalId", terminalId)
        values.put("isNeedRevoke", isNeedRevoke)
        values.put("cur", cur)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    fun fillByCursor(cur: Cursor) {
        id = cur.getString(cur.getColumnIndexOrThrow("id"))
        merchantId = cur.getString(cur.getColumnIndexOrThrow("merchantId"))
        respTime = cur.getString(cur.getColumnIndexOrThrow("respTime"))
        payType = cur.getString(cur.getColumnIndexOrThrow("payType"))
        amt = cur.getString(cur.getColumnIndexOrThrow("amt"))
        traceNo = cur.getString(cur.getColumnIndexOrThrow("traceNo"))
        reference = cur.getString(cur.getColumnIndexOrThrow("reference"))
        dimensionCode = cur.getString(cur.getColumnIndexOrThrow("dimensionCode"))
        terminalId = cur.getString(cur.getColumnIndexOrThrow("terminalId"))
        isNeedRevoke = cur.getString(cur.getColumnIndexOrThrow("isNeedRevoke"))
        this.cur = cur.getString(cur.getColumnIndexOrThrow("cur"))
        reserve1 = cur.getString(cur.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cur.getString(cur.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cur.getString(cur.getColumnIndexOrThrow("Reserve3"))
    }
}
