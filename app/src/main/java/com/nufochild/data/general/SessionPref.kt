/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.general

import android.content.Context

class SessionPref(context: Context) {
    companion object {
        var PREFS_NAME = "user"
        var API_KEY_NAME = "api_key"
        var UPDATED_PROFILE_NAME = "profile_updated"
        var CHECKED_PROFILE_NAME = "profile_checked"
        var NUTRITION_NAME = "nutrition"
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

    fun getProfileStatus(): Boolean {
        return preferences.getBoolean(UPDATED_PROFILE_NAME, false)
    }

    fun setProfileStatus(newVal: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(UPDATED_PROFILE_NAME, newVal)
        editor.apply()
    }

    fun getNutritionStatus(): Boolean {
        return preferences.getBoolean(NUTRITION_NAME, false)
    }

    fun setNutritionStatus(newVal: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(NUTRITION_NAME, newVal)
        editor.apply()
    }

    fun getProfileCheck(): Boolean {
        return preferences.getBoolean(CHECKED_PROFILE_NAME, false)
    }

    fun setProfileCheck(newVal: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(CHECKED_PROFILE_NAME, newVal)
        editor.apply()
    }

}