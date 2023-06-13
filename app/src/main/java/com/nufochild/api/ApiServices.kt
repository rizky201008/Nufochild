/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.api

import com.nufochild.data.request.RequestLogin
import com.nufochild.data.response.ResponseLogin
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServices {
    @POST("auth/login")
    suspend  fun login(
        @Body() body: RequestLogin
    ): ResponseLogin

    companion object {
        var apiService: ApiServices? = null
        fun getInstance() : ApiServices {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl("https://database-api-rezeodju2q-et.a.run.app/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }
            return apiService!!
        }
    }
}