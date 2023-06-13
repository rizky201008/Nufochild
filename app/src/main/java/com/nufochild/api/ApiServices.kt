/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.api

import com.nufochild.data.request.RequestLogin
import com.nufochild.data.response.ResponseDetailUser
import com.nufochild.data.response.ResponseLogin
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiServices {
    @GET("profile")
    fun getProfile(
        @Header("Authorization") token: String
    ): ResponseDetailUser

    @POST("auth/login")
    fun login(
        @Body() body: RequestLogin
    ): ResponseLogin
}