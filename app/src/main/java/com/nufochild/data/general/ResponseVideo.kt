/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.general

import com.google.gson.annotations.SerializedName

data class ResponseVideoItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    val success: Boolean = true,

    val message: String? = null
)
