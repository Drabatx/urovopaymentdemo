package com.test.urovopaymentapp.data.model.db

import android.content.ContentValues
import android.database.Cursor

class UrovoUserModel {
    var id: String? = null
    var code: String? = null //Number (user name)
    var name: String? = null //name
    var type: String? = null //Type (0: Administrator, 1: Supervisor, 2: Operator)
    var password: String? = null //Password
    var memo1: String? = null //Remark 1
    var memo2: String? = null //Remark 2
    var reserve1: String? = null //Reserved field 1
    var reserve2: String? = null //Reserved field 2
    var reserve3: String? = null //Reserved field 3

    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("Id", id)
        values.put("Code", this.code)
        values.put("Name", name)
        values.put("Type", type)
        values.put("Password", password)
        values.put("Memo1", memo1)
        values.put("Memo2", memo2)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        return values
    }

    fun fillByCursor(cur: Cursor) {
        id = cur.getString(cur.getColumnIndexOrThrow("Id"))
        this.code = cur.getString(cur.getColumnIndexOrThrow("Code"))
        name = cur.getString(cur.getColumnIndexOrThrow("Name"))
        type = cur.getString(cur.getColumnIndexOrThrow("Type"))
        password = cur.getString(cur.getColumnIndexOrThrow("Password"))
        memo1 = cur.getString(cur.getColumnIndexOrThrow("Memo1"))
        memo2 = cur.getString(cur.getColumnIndexOrThrow("Memo2"))
        reserve1 = cur.getString(cur.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cur.getString(cur.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cur.getString(cur.getColumnIndexOrThrow("Reserve3"))
    }

    companion object {
        /**Type value-system administrator */
        const val Type_Admin: String = "0"

        /**Type value-supervisor */
        const val Type_Manager: String = "1"

        /**Type value-operator */
        const val Type_Operator: String = "2"
    }
}
