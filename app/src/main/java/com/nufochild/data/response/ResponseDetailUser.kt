/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.response

import com.google.gson.annotations.SerializedName

data class ResponseDetailUser(

    @field:SerializedName("gender")
    val gender: String? = "Undefined",

    @field:SerializedName("name")
    val name: String? = "",

    @field:SerializedName("weight")
    val weight: Int? = 0,

    @field:SerializedName("userId")
    val userId: String? = "abcdefg",

    @field:SerializedName("age")
    val age: Int? = 0,

    @field:SerializedName("height")
    val height: Int? = 0,

    val message: String? = null,

    val success: Boolean = true
)
