package com.test.urovopaymentapp.utils

import android.content.Context
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun getJson(fileName: String?, context: Context): String {
    //将json数据变成字符串
    val stringBuilder = StringBuilder()
    try {
        //获取assets资源管理器
        val assetManager = context.assets
        //通过管理器打开文件并读取
        val bf = BufferedReader(
            InputStreamReader(
                assetManager.open(fileName!!)
            )
        )
        var line: String?
        while ((bf.readLine().also { line = it }) != null) {
            stringBuilder.append(line)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return stringBuilder.toString()
}

fun getPan(string: String): String {
    if (string.length > 10) {
        val stringBuffer = StringBuffer()
        stringBuffer.append(string.substring(0, 6))
        val String2 = string.substring(6, string.length - 4)
        for (i in 0 until String2.length) {
            stringBuffer.append("*")
        }
        stringBuffer.append(string.substring(string.length - 4, string.length))
        return stringBuffer.toString()
    }
    return ""
}
fun getCurrentDateTime(): String {
    val currentDate = Date()
    val dateFormat = SimpleDateFormat("dd/MM/yyyy 'a las' HH:mm:ss", Locale.getDefault())
    return dateFormat.format(currentDate)
}

fun IntToAmt(amt: Long): String {
    var StrTrace = "" + amt
    val str1: String
    val str2: String
    if (StrTrace.length == 2) StrTrace = "0.$StrTrace"
    else if (StrTrace.length == 1) StrTrace = "0.0$StrTrace"
    else {
        str1 = StrTrace.substring(StrTrace.length - 2, StrTrace.length)
        str2 = StrTrace.substring(0, StrTrace.length - 2)
        StrTrace = "$str2.$str1"
    }
    return StrTrace
}

fun getDateTime(transdbModel: String): String {
    try {
        val stringBuffer = StringBuffer()
        stringBuffer.append(transdbModel.substring(0, 4))
        stringBuffer.append("/")
        stringBuffer.append(transdbModel.substring(4, 6))
        stringBuffer.append("/")
        stringBuffer.append(transdbModel.substring(6, 8))
        stringBuffer.append(" ")
        stringBuffer.append(transdbModel.substring(8, 10))
        stringBuffer.append(":")
        stringBuffer.append(transdbModel.substring(10, 12))
        stringBuffer.append(":")
        stringBuffer.append(transdbModel.substring(12, 14))
        return stringBuffer.toString()
    } catch (e: Exception) {
        e.printStackTrace()
        return ""
    }
}