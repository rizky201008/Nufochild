/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.response

import com.google.gson.annotations.SerializedName

data class ResponseUpdateProfile(

    @field:SerializedName("nutrition")
    val nutrition: Nutrition? = null,

    @field:SerializedName("message")
    val message: String? = null
)

data class Nutrition(

    @field:SerializedName("carbo")
    val carbo: Float? = null,

    @field:SerializedName("fiber")
    val fiber: Float? = null,

    @field:SerializedName("protein")
    val protein: Float? = null,

    @field:SerializedName("fat")
    val fat: Float? = null,

    @field:SerializedName("energy")
    val energy: Float? = null,

    val error: Boolean = false
)
