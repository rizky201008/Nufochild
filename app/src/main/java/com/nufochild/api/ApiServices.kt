/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.api

import retrofit2.http.GET

interface ApiServices {

    @GET("profile")
    fun getProfile() {
        
    }
}