/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import android.util.Log
import com.nufochild.api.ApiServices
import com.nufochild.data.general.ResponseVideoItem
import com.nufochild.data.general.SessionPref
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.request.RequestRegister
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.ResponseDetailUser
import com.nufochild.data.response.ResponseLogin
import com.nufochild.data.response.ResponseRegister

class MainRepository(
    private val sessionPref: SessionPref,
) {
    suspend fun getVideos(): List<ResponseVideoItem>? {
        val apiServices = ApiServices.getInstance()
        val response = apiServices.videos()
        return try {
            if (response.isSuccessful) {
                response.body()
            } else {
                listOf(ResponseVideoItem(null, null, null, false, null))
            }
        } catch (e: Exception) {
            listOf(ResponseVideoItem(null, null, null, false, null))
        }
    }

    suspend fun getFood(): List<FoodsItem> {
        Log.i("xxxncRepo", "getFood Hit")
        val apiServices = ApiServices.getInstance()
        val response = apiServices.meals()
        return try {
            if (response.isSuccessful) {
                response.body()!!
            } else {
                listOf(FoodsItem(null, null, null, null, null, null, null, null, false))
            }
        } catch (e: Exception) {
            listOf(
                FoodsItem(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    e.localizedMessage,
                    false
                )
            )
        }
    }

    fun getToken(): String {
        return sessionPref.getToken()
    }

    suspend fun getProfile(): ResponseDetailUser {
        val apiServices = ApiServices.getInstance()
        val token = getToken()
        val bearerToken = "Bearer $token"
        return try {
            val response = apiServices.profile(bearerToken)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                ResponseDetailUser(
                    null, null, null, null, null, null, response.body()!!.message, false
                )
            }
        } catch (e: Exception) {
            ResponseDetailUser(null, null, null, null, null, null, e.localizedMessage, false)
        }
    }

    suspend fun loginAccount(requestLogin: RequestLogin): ResponseLogin {
        val apiService = ApiServices.getInstance()
        return try {
            val responseLogin = apiService.login(requestLogin)
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