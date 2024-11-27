package com.test.urovopaymentapp.data.local.preferences

import android.content.Context
import android.content.SharedPreferences


object SharedPrefsTool {
    const val SHARED_PREFS_NAME = "shared_prefs"
    const val MERCHANT_PREFS_NAME = "merchant_prefs"
    const val SYSTEM_CONFIG_PREFS_NAME = "SystemConfiguration"


    /**
     * Inicializa SharedPrefsTool con el nombre del archivo de preferencias.
     * Este método debe ser llamado antes de usar el objeto.
     */
    fun getInstances(context: Context, prefsName: String): SharedPreferences {
        return context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)
    }

    /**
     * Guarda un valor en las SharedPreferences.
     */
    fun put(sharedPreferences: SharedPreferences, key: String, value: Any) {
        with(sharedPreferences.edit()) {
            when (value) {
                is String -> putString(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                else -> throw IllegalArgumentException("Unsupported type: ${value::class.java.simpleName}")
            }
            apply()
        }
    }

    /**
     * Obtiene un valor de las SharedPreferences con un valor predeterminado.
     */
    fun <T> get(sharedPreferences: SharedPreferences, key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> sharedPreferences.getString(key, defaultValue) as T
            is Float -> sharedPreferences.getFloat(key, defaultValue) as T
            is Long -> sharedPreferences.getLong(key, defaultValue) as T
            is Int -> sharedPreferences.getInt(key, defaultValue) as T
            is Boolean -> sharedPreferences.getBoolean(key, defaultValue) as T
            else -> throw IllegalArgumentException(
                "Unsupported type or null default value: ${defaultValue?.let { it::class.java.simpleName } ?: "null"}"
            )
        }
    }

    /**
     * Elimina una clave específica de las SharedPreferences.
     */
    fun remove(sharedPreferences: SharedPreferences, key: String) {
        with(sharedPreferences.edit()) {
            remove(key)
            apply()
        }
    }

    /**
     * Limpia todas las claves y valores almacenados en las SharedPreferences.
     */
    fun clear(sharedPreferences: SharedPreferences) {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }

    /**
     * Verifica si una clave existe en las SharedPreferences.
     */
    fun contains(sharedPreferences: SharedPreferences, key: String): Boolean {
        return sharedPreferences.contains(key)
    }
}