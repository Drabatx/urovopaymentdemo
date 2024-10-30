package com.test.urovopaymentapp.data.model

import com.test.urovopaymentapp.utils.PrintUrtil
import java.io.Serializable

class StIso8583 : Serializable, Cloneable {
    var szMsgCode: String = ""

    var field1: String = ""
    var field2: String = ""
    var field3: String = ""
    var field4: String = ""
    var field5: String = ""
    var field6: String = ""
    var field7: String = ""
    var field8: String = ""
    var field9: String = ""
    var field10: String = ""
    var field11: String = ""
    var field12: String = ""
    var field13: String = ""
    var field14: String = ""
    var field15: String = ""
    var field16: String = ""
    var field17: String = ""
    var field18: String = ""
    var field19: String = ""
    var field20: String = ""
    var field21: String = ""
    var field22: String = ""
    var field23: String = ""
    var field24: String = ""
    var field25: String = ""
    var field26: String = ""
    var field27: String = ""
    var field28: String = ""
    var field29: String = ""
    var field30: String = ""
    var field31: String = ""
    var field32: String = ""
    var field33: String = ""
    var field34: String = ""
    var field35: String = ""
    var field36: String = ""
    var field37: String = ""
    var field38: String = ""
    var field39: String = ""
    var field40: String = ""
    var field41: String = ""
    var field42: String = ""
    var field43: String = ""
    var field44: String = ""
    var field45: String = ""
    var field46: String = ""
    var field47: String = ""
    var field48: String = ""
    var field49: String = ""
    var field50: String = ""
    var field51: String = ""
    var field52: String = ""
    var field53: String = ""
    var field54: String = ""
    var field55: String = ""
    var field56: String = ""
    var field57: String = ""
    var field58: String = ""
    var field59: String = ""
    var field60: String = ""
    var field61: String = ""
    var field62: String = ""
    var field63: String = ""
    var field64: String = ""
    var field65: String = ""
    var field66: String = ""
    var field67: String = ""
    var field68: String = ""
    var field69: String = ""
    var field70: String = ""
    var field71: String = ""
    var field72: String = ""
    var field73: String = ""
    var field74: String = ""
    var field75: String = ""
    var field76: String = ""
    var field77: String = ""
    var field78: String = ""
    var field79: String = ""
    var field80: String = ""
    var field81: String = ""
    var field82: String = ""
    var field83: String = ""
    var field84: String = ""
    var field85: String = ""
    var field86: String = ""
    var field87: String = ""
    var field88: String = ""
    var field89: String = ""
    var field90: String = ""
    var field91: String = ""
    var field92: String = ""
    var field93: String = ""
    var field94: String = ""
    var field95: String = ""
    var field96: String = ""
    var field97: String = ""
    var field98: String = ""
    var field99: String = ""
    var field100: String = ""
    var field101: String = ""
    var field102: String = ""
    var field103: String = ""
    var field104: String = ""
    var field105: String = ""
    var field106: String = ""
    var field107: String = ""
    var field108: String = ""
    var field109: String = ""
    var field110: String = ""
    var field111: String = ""
    var field112: String = ""
    var field113: String = ""
    var field114: String = ""
    var field115: String = ""
    var field116: String = ""
    var field117: String = ""
    var field118: String = ""
    var field119: String = ""
    var field120: String = ""
    var field121: String = ""
    var field122: String = ""
    var field123: String = ""
    var field124: String = ""
    var field125: String = ""
    var field126: String = ""
    var field127: String = ""
    var field128: String = ""

    fun toLogModel(): StIso8583 {
        var stIso8583 = StIso8583()
        try {
            stIso8583 = clone() as StIso8583
            stIso8583.field2 = PrintUrtil.getPan(stIso8583.field2)
            stIso8583.field35 = ""
            stIso8583.field36 = ""
            stIso8583.field52 = ""
            stIso8583.field55 = ""
            // 正式发布版本时给铭感信息赋值为空
            val c: Class<*> = stIso8583.javaClass
            for (i in 1..127) {
                // 获取该对象的propertyName成员变量
                val field = c.getDeclaredField("field$i") // 注意你是通过参数来获取，不许加双引号“”
                // 取消访问检查
                field.isAccessible = true
                if ("" == field[stIso8583]) {
                    field.isAccessible = true
                    // 给对象的成员变量赋值为指定的值--->value
                    field[stIso8583] = null
                }
            }
        } catch (e: NoSuchFieldException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: SecurityException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: Exception) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        return stIso8583
    }

    companion object {
        const val serialversionuid: Long = 1L
    }
}
