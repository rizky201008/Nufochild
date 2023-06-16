/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.request

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profile")
data class RequestUpdateProfile(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("weight")
    val weight: Int? = null,

    @field:SerializedName("age")
    val age: Int? = null,

    @field:SerializedName("height")
    val height: Int? = null
)
