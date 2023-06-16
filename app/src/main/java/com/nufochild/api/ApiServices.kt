/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.api

import com.nufochild.data.general.ResponseVideoItem
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.request.RequestRegister
import com.nufochild.data.request.RequestUpdateProfile
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.ResponseDetailUser
import com.nufochild.data.response.ResponseImageDetection
import com.nufochild.data.response.ResponseLogin
import com.nufochild.data.response.ResponseRegister
import com.nufochild.data.response.ResponseUpdateProfile
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiServices {
    @POST("auth/login")
    suspend fun login(
        @Body() body: RequestLogin
    ): Response<ResponseLogin>

    @POST("auth/register")
    suspend fun register(
        @Body() body: RequestRegister
    ): Response<ResponseRegister>

    @GET("profil")
    @Headers("Accept: application/json")
    suspend fun profile(
        @Header("Authorization") token: String
    ): Response<ResponseDetailUser>

    @GET("videos")
    @Headers("Accept: application/json")
    suspend fun videos(): Response<List<ResponseVideoItem>?>

    @GET("meals")
    @Headers("Accept: application/json")
    suspend fun meals(): Response<List<FoodsItem>>

    @PUT("profil")
    suspend fun updateProfile(
        @Header("Authorization") token: String,
        @Body() body: RequestUpdateProfile
    ): Response<ResponseUpdateProfile>

    @POST("profil")
    suspend fun newProfile(
        @Header("Authorization") token: String,
        @Body() body: RequestUpdateProfile
    ): Response<ResponseUpdateProfile>

    @Multipart
    @POST("upload")
    suspend fun postImage(@Part file: MultipartBody.Part): Response<ResponseImageDetection>


    companion object {
        //                val BASEURL = "https://webhook.site/3b574d45-0c80-4016-9f8a-3161d13b900c/"
        val BASEURL = "https://database-api-rezeodju2q-et.a.run.app/"
        var apiService: ApiServices? = null
        fun getInstance(): ApiServices {
            if (apiService == null) {
                apiService = Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build().create(ApiServices::class.java)
            }
            return apiService!!
        }
    }
}