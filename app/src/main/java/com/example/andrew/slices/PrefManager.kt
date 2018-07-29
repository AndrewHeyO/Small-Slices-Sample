package com.example.andrew.slices

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

/**
 * Created by Andrew on 28.07.2018
 */

class PrefManager(context: Context) {

    private var sharedPref: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun saveString(key: String, str: String) {
        sharedPref.edit()
                .putString(key, str)
                .apply()
    }

    fun getString(key: String) = sharedPref.getString(key, "")

    fun saveInt(key: String, int: Int) {
        sharedPref.edit()
                .putInt(key, int)
                .apply()
    }

    fun getInt(key: String) = sharedPref.getInt(key, 0)
}