package com.drabatx.urovopaymentapp.data.model.models.merchant

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.File
import javax.inject.Inject

/**
 * SharedPreferencs的工具类，该类主要用于向SD卡写入配置参数<br></br>
 * 提示：<br></br>
 * 1.添加权限 android.permission.READ_EXTERNAL_STORAGE<br></br>
 * 2.添加权限 android.permission.WRITE_EXTERNAL_STORAGE<br></br>
 *
 * @author KuCoffee
 */

class SharedPrefsTool @Inject constructor(
    @ApplicationContext private val context: Context,
    private val externalAppDir: File
) {

    /**
     * 重定向Context里的SharedPreferences的路径， 让它指向/storage/sdcard0/包名/shared_prefs目录
     *
     * @param ctx
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun redirectPreferencesDir(ctx: Context, preferencesDir: File) {
        var obj = ReflectTool.getFromField(ctx, "mBase") // 可能是ContextImpl或者Application
        if (obj is Application) {
            obj = ReflectTool.getFromField(obj, "mBase")
        }
        // 现在obj只是ContextImpl
        ReflectTool.setToField(obj, "mPreferencesDir", preferencesDir)
    }

    /**
     * 创建SharedPreferences在SD卡中的目录， 因为原生的SharedPreferences不能在SD卡中创建目录，所以只能手动创建
     *
     * @param dirs
     * @throws Exception
     */
    @Throws(Exception::class)
    private fun createPreferencesDirs(dirs: File) {
        if (!dirs.exists() || !dirs.isDirectory) {
            dirs.mkdirs()
        }
    }

    /**
     * 获取SharedPreferencs
     *
     * @param fileName
     * @return
     */
    private fun getSharedPrefs(fileName: String): SharedPreferences? {
        var sharedPrefs: SharedPreferences? = null
        try {

            val preferencesDir: File = File(externalAppDir, "shared_prefs")
            createPreferencesDirs(preferencesDir)
            redirectPreferencesDir(context, preferencesDir)
            sharedPrefs =
                context.getSharedPreferences(fileName, Context.MODE_PRIVATE or Context.MODE_PRIVATE)
        } catch (e: Exception) {
            throw RuntimeException(e.message)
        }
        return sharedPrefs
    }

    /**
     * 获取SharedPreferences中的bool值
     *
     * @param fileName
     * @param key
     * @param defValue
     * @return
     */
    fun getBoolean(fileName: String, key: String?, defValue: Boolean): Boolean {
        return getSharedPrefs(fileName)!!.getBoolean(key, defValue)
    }

    /**
     * 设置SharedPreferences中的bool值
     *
     * @param fileName
     * @param key
     * @param value
     * @return
     */
    fun putBoolean(fileName: String, key: String?, value: Boolean): Boolean {
        return getSharedPrefs(fileName)!!.edit().putBoolean(key, value).commit()
    }

    /**
     * 获取SharedPreferences中的int值
     *
     * @param fileName
     * @param key
     * @param defValue
     * @return
     */
    fun getInt(fileName: String, key: String?, defValue: Int): Int {
        return getSharedPrefs(fileName)!!.getInt(key, defValue)
    }

    /**
     * 设置SharedPreferences中的int值
     *
     * @param fileName
     * @param key
     * @param value
     * @return
     */
    fun putInt(fileName: String, key: String?, value: Int): Boolean {
        return getSharedPrefs(fileName)!!.edit().putInt(key, value).commit()
    }

    /**
     * 获取SharedPreferences中的long值
     *
     * @param fileName
     * @param key
     * @param defValue
     * @return
     */
    fun getLong(fileName: String, key: String?, defValue: Long): Long {
        return getSharedPrefs(fileName)!!.getLong(key, defValue)
    }

    /**
     * 设置SharedPreferences中的long值
     *
     * @param fileName
     * @param key
     * @param value
     * @return
     */
    fun putLong(fileName: String, key: String?, value: Long): Boolean {
        return getSharedPrefs(fileName)!!.edit().putLong(key, value).commit()
    }

    /**
     * 获取SharedPreferences中的float值
     *
     * @param fileName
     * @param key
     * @param defValue
     * @return
     */
    fun getFloat(fileName: String, key: String?, defValue: Float): Float {
        return getSharedPrefs(fileName)!!.getFloat(key, defValue)
    }

    /**
     * 设置SharedPreferences中的float值
     *
     * @param fileName
     * @param key
     * @param value
     * @return
     */
    fun putFloat(fileName: String, key: String?, value: Float): Boolean {
        return getSharedPrefs(fileName)!!.edit().putFloat(key, value).commit()
    }

    /**
     * 获取SharedPreferences中的String值
     *
     * @param fileName
     * @param key
     * @param defValue
     * @return
     */
    fun getString(fileName: String, key: String?, defValue: String?): String? {
        return getSharedPrefs(fileName)!!.getString(key, defValue)
    }

    /**
     * 设置SharedPreferences中的String值
     *
     * @param fileName
     * @param key
     * @param value
     * @return
     */
    fun putString(fileName: String, key: String?, value: String?): Boolean {
        return getSharedPrefs(fileName)!!.edit().putString(key, value).commit()
    }

}
