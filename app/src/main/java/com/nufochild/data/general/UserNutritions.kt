/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.general

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "nutrition")
data class UserNutritions(

    @PrimaryKey
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("protein")
    val protein: Float? = 0f,
    @field:SerializedName("vprotein")
    val vprotein: Float? = 0f,

    @field:SerializedName("energy")
    val energy: Float? = 0f,
    @field:SerializedName("venergy")
    val venergy: Float? = 0f,

    @field:SerializedName("fat")
    val fat: Float? = 0f,
    @field:SerializedName("vfat")
    val vfat: Float? = 0f,

    @field:SerializedName("fiber")
    val fiber: Float? = 0f,
    @field:SerializedName("vfiber")
    val vfiber: Float? = 0f,

    @field:SerializedName("carbohydrate")
    val carbohydrate: Float? = 0f,
    @field:SerializedName("vcarbohydrate")
    val vcarbohydrate: Float? = 0f
)
