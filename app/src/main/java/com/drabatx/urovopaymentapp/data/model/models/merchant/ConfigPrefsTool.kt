package com.drabatx.urovopaymentapp.data.model.models.merchant

import javax.inject.Inject

class ConfigPrefsTool @Inject constructor(private val sharedPrefsTool: SharedPrefsTool) {
    private val SharedPrefsFileName = "SystemConfiguration"

    /**Get the bool value in SharedPreferences
     * @param key
     * @param defValue
     * @return
     */
	fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return sharedPrefsTool.getBoolean(SharedPrefsFileName, key, defValue)
    }

    /**Set the bool value in SharedPreferences
     * @param key
     * @param value
     * @return
     */
	fun putBoolean(key: String?, value: Boolean): Boolean {
        return sharedPrefsTool.putBoolean(SharedPrefsFileName, key, value)
    }

    /**Get the int value in SharedPreferences
     * @param key
     * @param defValue
     * @return
     */
    fun getInt(key: String?, defValue: Int): Int {
        return sharedPrefsTool.getInt(SharedPrefsFileName, key, defValue)
    }

    /**Set the int value in SharedPreferences
     * @param key
     * @param value
     * @return
     */
    fun putInt(key: String?, value: Int): Boolean {
        return sharedPrefsTool.putInt(SharedPrefsFileName, key, value)
    }

    /**Get the long value in SharedPreferences
     * @param key
     * @param defValue
     * @return
     */
    fun getLong(key: String?, defValue: Long): Long {
        return sharedPrefsTool.getLong(SharedPrefsFileName, key, defValue)
    }

    /**Set the long value in SharedPreferences
     * @param key
     * @param value
     * @return
     */
    fun putLong(key: String?, value: Long): Boolean {
        return sharedPrefsTool.putLong(SharedPrefsFileName, key, value)
    }

    /**Get the float value in SharedPreferences
     * @param key
     * @param defValue
     * @return
     */
    fun getFloat(key: String?, defValue: Float): Float {
        return sharedPrefsTool.getFloat(SharedPrefsFileName, key, defValue)
    }

    /**Set the float value in SharedPreferences
     * @param key
     * @param value
     * @return
     */
    fun putFloat(key: String?, value: Float): Boolean {
        return sharedPrefsTool.putFloat(SharedPrefsFileName, key, value)
    }

    /**Get the String value in SharedPreferences
     * @param key
     * @param defValue
     * @return
     */
	fun getString(key: String?, defValue: String?): String {
        return sharedPrefsTool.getString(SharedPrefsFileName, key, defValue)?:""
    }

    /**Set the String value in SharedPreferences
     * @param key
     * @param value
     * @return
     */
	fun putString(key: String?, value: String?): Boolean {
        return sharedPrefsTool.putString(SharedPrefsFileName, key, value)
    }
}
