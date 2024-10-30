package com.test.urovopaymentapp.data.model.pos2.db

import android.content.ContentValues
import android.database.Cursor

/**Model of the Old Settle Bill table, which indicates the previous statement
 * @author KuCoffee
 */
class OldSettleModel {
    var indexID: String? = null
    var sDebitAMT: String? = null
    var sCreditAMT: String? = null
    var sDebitS: String? = null
    var sCreditS: String? = null
    var sFrnDebitAMT: String? = null
    var sFrnCreditAMT: String? = null
    var sFrnDebitS: String? = null
    var sFrnCreditS: String? = null
    var reserve1: String? = null //reservationField1
    var reserve2: String? = null //reservationField2
    var reserve3: String? = null //reservationField3

    /**Convert all the fields of the object to Content Values
     * @return
     */
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("IndexID", indexID)
        values.put("SDebitAMT", sDebitAMT)
        values.put("SCreditAMT", sCreditAMT)
        values.put("SDebitS", sDebitS)
        values.put("SCreditS", sCreditS)
        values.put("SFrnDebitAMT", sFrnDebitAMT)
        values.put("SFrnCreditAMT", sFrnCreditAMT)
        values.put("SFrnDebitS", sFrnDebitS)
        values.put("SFrnCreditS", sFrnCreditS)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    /**Populate the model with the value of a record in Cursor
     * @param cursor
     */
    fun fillByCursor(cursor: Cursor) {
        indexID = cursor.getString(cursor.getColumnIndexOrThrow("IndexID"))
        sDebitAMT = cursor.getString(cursor.getColumnIndexOrThrow("SDebitAMT"))
        sCreditAMT = cursor.getString(cursor.getColumnIndexOrThrow("SCreditAMT"))
        sDebitS = cursor.getString(cursor.getColumnIndexOrThrow("SDebitS"))
        sCreditS = cursor.getString(cursor.getColumnIndexOrThrow("SCreditS"))
        sFrnDebitAMT = cursor.getString(cursor.getColumnIndexOrThrow("SFrnDebitAMT"))
        sFrnCreditAMT = cursor.getString(cursor.getColumnIndexOrThrow("SFrnCreditAMT"))
        sFrnDebitS = cursor.getString(cursor.getColumnIndexOrThrow("SFrnDebitS"))
        sFrnCreditS = cursor.getString(cursor.getColumnIndexOrThrow("SFrnCreditS"))
        reserve1 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve3"))
    }
}
