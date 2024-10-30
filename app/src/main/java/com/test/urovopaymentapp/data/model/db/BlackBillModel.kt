package com.test.urovopaymentapp.data.model.db

import android.content.ContentValues
import android.database.Cursor

/**The model of the Black Bill table stands for blacklist information
 * @author KuCoffee
 */
class BlackBillModel {
    var indexID: String? = null
    var expBIN: String? = null
    var reserve1: String? = null //reservationField1
    var reserve2: String? = null //reservationField2
    var reserve3: String? = null //reservationField3

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("IndexID", indexID)
        values.put("ExpBIN", expBIN)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    fun fillByCursor(cursor: Cursor) {
        indexID = cursor.getString(cursor.getColumnIndexOrThrow("IndexID"))
        expBIN = cursor.getString(cursor.getColumnIndexOrThrow("ExpBIN"))
        reserve1 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve3"))
    }
}
