package com.drabatx.urovopaymentapp.data.model.pos2.db

import android.content.ContentValues
import android.database.Cursor

/**Model of the UrovoTrans table, represents:
 * @author KuCoffee
 */
class UrovoTransModel {
    var indexID: String? = null
    var emvTransNo: String? = null
    var refCurrencyCode: String? = null
    var merchantTypeCode: String? = null
    var termTransATT: String? = null
    var publicKeys: String? = null
    var reserve1: String? = null //Reserved field 1
    var reserve2: String? = null //Reserved field 2
    var reserve3: String? = null //Reserved field 3

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("IndexID", indexID)
        values.put("EmvTransNo", emvTransNo)
        values.put("RefCurrencyCode", refCurrencyCode)
        values.put("MerchantTypeCode", merchantTypeCode)
        values.put("TermTransATT", termTransATT)
        values.put("PublicKeys", publicKeys)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    fun fillByCursor(cur: Cursor) {
        indexID = cur.getString(cur.getColumnIndexOrThrow("IndexID"))
        emvTransNo = cur.getString(cur.getColumnIndexOrThrow("EmvTransNo"))
        refCurrencyCode = cur.getString(cur.getColumnIndexOrThrow("RefCurrencyCode"))
        merchantTypeCode = cur.getString(cur.getColumnIndexOrThrow("MerchantTypeCode"))
        termTransATT = cur.getString(cur.getColumnIndexOrThrow("TermTransATT"))
        publicKeys = cur.getString(cur.getColumnIndexOrThrow("PublicKeys"))
        reserve1 = cur.getString(cur.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cur.getString(cur.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cur.getString(cur.getColumnIndexOrThrow("Reserve3"))
    }
}
