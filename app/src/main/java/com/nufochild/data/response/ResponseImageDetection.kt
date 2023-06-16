/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.response

import com.google.gson.annotations.SerializedName

data class ResponseImageDetection(

    @field:SerializedName("nutritionData")
    val nutritionData: List<NutritionDataItem?>? = null,

    @field:SerializedName("imageUrl")
    val imageUrl: String? = null,

    @field:SerializedName("prediction")
    val prediction: String? = null,

    @field:SerializedName("error")
    val error: String? = null,
)

data class NutritionDataItem(

    val message: String? = null,
    val success: Boolean? = true,

    @field:SerializedName("Lemak")
    val lemak: Float? = null,

    @field:SerializedName("Serat")
    val serat: Float? = null,

    @field:SerializedName("Nama")
    val nama: String? = null,

    @field:SerializedName("Energi")
    val energi: Float? = null,

    @field:SerializedName("Karbohidrat")
    val karbohidrat: Float? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("Protein")
    val protein: Float? = null
)
