/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.general

import android.content.Context

class SessionPref(context: Context) {
    companion object {
        var PREFS_NAME = "token"
        var API_KEY_NAME = "api_key"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    fun setToken(value: String) {
        val editor = preferences.edit()
        editor.putString(API_KEY_NAME, value)
        editor.apply()
    }

    fun getToken(): String {
        val api_key = preferences.getString(API_KEY_NAME, "")
        return api_key.toString()
    }
}