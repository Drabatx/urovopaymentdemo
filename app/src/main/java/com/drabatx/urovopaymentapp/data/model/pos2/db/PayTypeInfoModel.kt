package com.drabatx.urovopaymentapp.data.model.pos2.db

import android.content.ContentValues
import android.database.Cursor

/**Model of tmsPayTypeInfo table, represents:
 * @author KuCoffee
 */
class PayTypeInfoModel {
    var id: String? = null
    var payType: String? = null
    var timeout: String? = null
    var interval: String? = null
    var times: String? = null
    var reserve1: String? = null //reservationField1
    var reserve2: String? = null //reservationField2
    var reserve3: String? = null //reservationField3
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("id", id)
        values.put("payType", payType)
        values.put("timeout", timeout)
        values.put("interval", interval)
        values.put("times", times)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    fun fillByCursor(cur: Cursor) {
        id = cur.getString(cur.getColumnIndexOrThrow("id"))
        payType = cur.getString(cur.getColumnIndexOrThrow("payType"))
        timeout = cur.getString(cur.getColumnIndexOrThrow("timeout"))
        interval = cur.getString(cur.getColumnIndexOrThrow("interval"))
        times = cur.getString(cur.getColumnIndexOrThrow("times"))
        reserve1 = cur.getString(cur.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cur.getString(cur.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cur.getString(cur.getColumnIndexOrThrow("Reserve3"))
    }
}
