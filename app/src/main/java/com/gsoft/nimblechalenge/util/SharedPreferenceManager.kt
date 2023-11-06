package com.gsoft.nimblechalenge.util

import android.content.Context
import android.content.SharedPreferences


class SharePreferencesManager(private var context: Context) {

    fun cleanData() {
        val settings: SharedPreferences =
            context.getSharedPreferences(Constants.KEY_SHARED_PREFERENCE, 0)
        val editor = settings.edit()
        editor.clear()
        editor.apply()
    }

    fun remove(key: String?) {
        val settings: SharedPreferences =
            context.getSharedPreferences(Constants.KEY_SHARED_PREFERENCE, 0)
        val editor = settings.edit()
        editor.remove(key)
        editor.apply()
    }

    fun setString(key: String?, value: String?) {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        val editor = settings.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String?, defaultValue: String?): String? {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        return settings.getString(key, defaultValue)
    }

    fun getBoolean(key: String?, defaultValue: Boolean): Boolean {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        return settings.getBoolean(key, defaultValue)
    }

    fun setBoolean(key: String?, value: Boolean) {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        val editor = settings.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }

    fun getLong(key: String?, defaultValue: Long): Long {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        return settings.getLong(key, defaultValue)
    }

    fun setLong(key: String?, value: Long) {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        val editor = settings.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun getInteger(key: String?, defaultValue: Int): Int {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        return settings.getInt(key, defaultValue)
    }

    fun setInteger(key: String?, value: Int) {
        val settings: SharedPreferences = context.getSharedPreferences(
            Constants.KEY_SHARED_PREFERENCE, 0
        )
        val editor = settings.edit()
        editor.putInt(key, value)
        editor.apply()
    }

}