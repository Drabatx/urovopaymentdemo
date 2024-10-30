package com.test.urovopaymentapp.data.model.db

import android.content.ContentValues
import android.database.Cursor

/**Model of the Reversal table, represents: reverse entity class
 * @author KuCoffee
 */
class ReversalModel {
    var indexID: String? = null
    var transType: String? = null
    var transStatus: String? = null
    var upToUMP: String? = null
    var traceNo: String? = null
    var s8583Send: String? = null
    var s8583Receive: String? = null
    var reserve1: String? = null //reservationField1
    var reserve2: String? = null //reservationField2
    var reserve3: String? = null //reservationField3

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("IndexID", indexID)
        values.put("TransType", transType)
        values.put("TransStatus", transStatus)
        values.put("UpToUMP", upToUMP)
        values.put("TraceNo", traceNo)
        values.put("S8583Send", s8583Send)
        values.put("S8583Receive", s8583Receive)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    fun fillByCursor(cursor: Cursor) {
        indexID = cursor.getString(cursor.getColumnIndexOrThrow("IndexID"))
        transType = cursor.getString(cursor.getColumnIndexOrThrow("TransType"))
        transStatus = cursor.getString(cursor.getColumnIndexOrThrow("TransStatus"))
        upToUMP = cursor.getString(cursor.getColumnIndexOrThrow("UpToUMP"))
        traceNo = cursor.getString(cursor.getColumnIndexOrThrow("TraceNo"))
        s8583Send = cursor.getString(cursor.getColumnIndexOrThrow("S8583Send"))
        s8583Receive = cursor.getString(cursor.getColumnIndexOrThrow("S8583Receive"))
        reserve1 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve3"))
    }
}
