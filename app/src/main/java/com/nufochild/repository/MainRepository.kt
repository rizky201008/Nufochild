/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import android.util.Log
import com.nufochild.api.ApiServices
import com.nufochild.data.general.SessionPref
import com.nufochild.data.general.Video
import com.nufochild.data.general.videoData
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.request.RequestRegister
import com.nufochild.data.response.FoodData
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.ResponseLogin
import com.nufochild.data.response.ResponseRegister

class MainRepository(
    private val sessionPref: SessionPref,
) {
    fun getVideos(): List<Video> {
        return videoData
    }

    fun getFood(): List<FoodsItem> {
        return FoodData.data
    }

    fun getToken(): String {
        return sessionPref.getToken()
    }

    suspend fun loginAccount(requestLogin: RequestLogin): ResponseLogin {
        val apiService = ApiServices.getInstance()
        return try {
            val responseLogin = apiService.login(requestLogin)
            Log.i("xxxnufochild", responseLogin.toString())
            if (responseLogin.isSuccessful) {
                sessionPref.setToken(responseLogin.body()!!.accessToken.toString())
                responseLogin.body()!!
            } else {
                ResponseLogin(null, null, responseLogin.body()!!.message, false)
            }
        } catch (e: Exception) {
            ResponseLogin(null, null, e.localizedMessage, false)
        }
    }

    suspend fun registerAccount(value: RequestRegister): ResponseRegister {
        val apiService = ApiServices.getInstance()
        return try {
            val response = apiService.register(value)
            Log.i("xxxnufochild", response.toString())
            if (response.isSuccessful) {
                response.body()!!
            } else {
                ResponseRegister(response.body()!!.message, false)
            }
        } catch (e: Exception) {
            val response = ResponseRegister(e.localizedMessage, false)
            response
        }
    }
}