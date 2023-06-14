/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.request

import com.google.gson.annotations.SerializedName

data class RequestRegister(

    @field:SerializedName("password")
    val password: String? = null,

    @field:SerializedName("password2")
    val password2: String? = password,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
