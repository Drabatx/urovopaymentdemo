package com.test.urovopaymentapp.data.model.db

import android.content.ContentValues
import android.database.Cursor
import java.io.Serializable

class TranslogModel : Serializable {
    var indexID: String? = null
    var transType: String? = null // Transaction type
    var transStatus: String? =
        null // Transaction status (whether it has been revoked; only valid for transactions that can be revoked, 0 means not revoked)
    var upToUMP: String? = null // Whether it has been uploaded (05 uploaded, 06 not uploaded)
    var merchantNum: String? = null // Merchant number
    var terminalNum: String? = null // terminal number
    var bankCardNum: String? = null // bank card number
    var traceNo: String? = null // Voucher number (POS terminal serial number)
    var refNo: String? = null // Reference number (POS center serial number)
    var sqmNo: String? = null // Authorization code
    var amt: String? = null //Amount
    var date: String? = null // time and date
    var s8583Send: String? = null //Send message
    var s8583Receive: String? = null //Receive message
    var remark1: String? = null // Remark field 1 (time and date)
    var remark2: String? = null // Remark field 2 (time and date)
    var remark3: String? = null // Remark field 3 (time and date)
    var reserve1: String? = null // Reserved field 1
    var reserve2: String? = null // Reserved field 2
    var reserve3: String? = null // Reserved field 3
    var reserve4: String? = null // Reserved field 4
    var posInputDatas: String? = null // Save the string of posInputDatas for expansion

    /**
     * Convert all fields of the object into ContentValues
     *
     * @return
     */
    fun toContentValues(): ContentValues {
        val values = ContentValues()
        values.put("IndexID", indexID)
        values.put("TransType", transType)
        values.put("TransStatus", transStatus)
        values.put("UpToUMP", upToUMP)
        values.put("MerchantNum", merchantNum)
        values.put("TerminalNum", terminalNum)
        values.put("BankCardNum", bankCardNum)
        values.put("TraceNo", traceNo)
        values.put("RefNo", refNo)
        values.put("SqmNo", sqmNo)
        values.put("Amt", amt)
        values.put("Date", date)
        values.put("S8583Send", s8583Send)
        values.put("S8583Receive", s8583Receive)
        values.put("Remark1", remark1)
        values.put("Remark2", remark2)
        values.put("Remark3", remark3)
        values.put("Reserve1", reserve1)
        values.put("Reserve2", reserve2)
        values.put("Reserve3", reserve3)
        values.put("Reserve4", reserve4)
        values.put("posInputDatas", posInputDatas)
        return values
    }

    /**
     * Fill the Model with the value of a record in the Cursor
     *
     * @param cursor
     */
    fun fillByCursor(cursor: Cursor) {
        indexID = cursor.getString(cursor.getColumnIndexOrThrow("IndexID"))
        transType = cursor.getString(cursor.getColumnIndexOrThrow("TransType"))
        transStatus = cursor.getString(cursor.getColumnIndexOrThrow("TransStatus"))
        upToUMP = cursor.getString(cursor.getColumnIndexOrThrow("UpToUMP"))
        merchantNum = cursor.getString(cursor.getColumnIndexOrThrow("MerchantNum"))
        terminalNum = cursor.getString(cursor.getColumnIndexOrThrow("TerminalNum"))
        bankCardNum = cursor.getString(cursor.getColumnIndexOrThrow("BankCardNum"))
        traceNo = cursor.getString(cursor.getColumnIndexOrThrow("TraceNo"))
        refNo = cursor.getString(cursor.getColumnIndexOrThrow("RefNo"))
        sqmNo = cursor.getString(cursor.getColumnIndexOrThrow("SqmNo"))
        amt = cursor.getString(cursor.getColumnIndexOrThrow("Amt"))
        date = cursor.getString(cursor.getColumnIndexOrThrow("Date"))
        s8583Send = cursor.getString(cursor.getColumnIndexOrThrow("S8583Send"))
        s8583Receive = cursor.getString(cursor.getColumnIndexOrThrow("S8583Receive"))
        remark1 = cursor.getString(cursor.getColumnIndexOrThrow("Remark1"))
        remark2 = cursor.getString(cursor.getColumnIndexOrThrow("Remark2"))
        remark3 = cursor.getString(cursor.getColumnIndexOrThrow("Remark3"))
        reserve1 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve1"))
        reserve2 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve2"))
        reserve3 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve3"))
        reserve4 = cursor.getString(cursor.getColumnIndexOrThrow("Reserve4"))
        posInputDatas = cursor.getString(cursor.getColumnIndexOrThrow("posInputDatas"))
    }
}
