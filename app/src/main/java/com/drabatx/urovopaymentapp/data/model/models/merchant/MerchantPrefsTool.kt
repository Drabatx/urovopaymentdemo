package com.drabatx.urovopaymentapp.data.model.models.merchant

import javax.inject.Inject

class MerchantPrefsTool @Inject constructor(private val sharedPrefsTool: SharedPrefsTool, private val configUtils: ConfigUtils) {
    /**
     * 获取SharedPreferences中的bool值
     * @param key
     * @param defValue
     * @return
     */
    fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPrefsTool.getBoolean(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            defValue
        )
    }

    /**
     * 设置SharedPreferences中的bool值
     * @param key
     * @param value
     * @return
     */
    fun putBoolean(key: String?, value: Boolean): Boolean {
        return sharedPrefsTool.putBoolean(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            value
        )
    }

    /**
     * 获取SharedPreferences中的int值
     * @param key
     * @param defValue
     * @return
     */
    fun getInt(key: String?, defValue: Int): Int {
        return sharedPrefsTool.getInt(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            defValue
        )
    }

    /**
     * 设置SharedPreferences中的int值
     * @param key
     * @param value
     * @return
     */
    fun putInt(key: String?, value: Int): Boolean {
        return sharedPrefsTool.putInt(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            value
        )
    }

    /**
     * 获取SharedPreferences中的long值
     * @param key
     * @param defValue
     * @return
     */
    fun getLong(key: String?, defValue: Long): Long {
        return sharedPrefsTool.getLong(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            defValue
        )
    }


    /**
     * 设置SharedPreferences中的long值
     * @param key
     * @param value
     * @return
     */
    fun putLong(key: String?, value: Long): Boolean {
        return sharedPrefsTool.putLong(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            value
        )
    }

    /**
     * 获取SharedPreferences中的float值
     * @param key
     * @param defValue
     * @return
     */
    fun getFloat(key: String?, defValue: Float): Float {
        return sharedPrefsTool.getFloat(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            defValue
        )
    }

    /**
     * 设置SharedPreferences中的float值
     * @param key
     * @param value
     * @return
     */
    fun putFloat(key: String?, value: Float): Boolean {
        return sharedPrefsTool.putFloat(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            value
        )
    }

    /**
     * 获取SharedPreferences中的String值
     *
     * @param key
     * @param defValue
     * @return
     */
    fun getString(key: String?, defValue: String?): String {
        return sharedPrefsTool.getString(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            defValue
        ) ?: ""
    }

    /**
     * 设置SharedPreferences中的String值
     *
     * @param key
     * @param value
     * @return
     */
    fun putString(key: String?, value: String?): Boolean {
        return sharedPrefsTool.putString(
            "MerchantInfo_" + configUtils.currentMerchantID,
            key,
            value
        )
    }

    /**
     * 判断功能是否开启
     *
     * @param key
     * @return
     */
    fun isFunctionOpen(key: String?): Boolean {
        val value = getString(key, "1")
        return if (value == "1") {
            true
        } else false
    }
}
