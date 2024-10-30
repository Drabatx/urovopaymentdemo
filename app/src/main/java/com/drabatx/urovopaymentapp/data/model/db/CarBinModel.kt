package com.drabatx.urovopaymentapp.data.model.db

import android.content.ContentValues
import android.database.Cursor

/**The model of the CarBin table represents:
 * @author KuCoffee
 */
class CarBinModel {
    var indexID: String? = null
    var lEN: String? = null
    var fLAG: String? = null
    var cARNUMBER: String? = null
    var reserve1: String? = null
    var reserve2: String? = null
    var reserve3: String? = null

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("IndexID", indexID)
        values.put("LEN", lEN)
        values.put("FLAG", fLAG)
        values.put("CARNUMBER", cARNUMBER)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)

        return values
    }

    fun fillByCursor(cur: Cursor) {
        indexID = cur.getString(cur.getColumnIndexOrThrow("IndexID"))
        lEN = cur.getString(cur.getColumnIndexOrThrow("LEN"))
        fLAG = cur.getString(cur.getColumnIndexOrThrow("FLAG"))
        cARNUMBER = cur.getString(cur.getColumnIndexOrThrow("CARNUMBER"))
        reserve1 = cur.getString(cur.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cur.getString(cur.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cur.getString(cur.getColumnIndexOrThrow("Reserve3"))
    }
}