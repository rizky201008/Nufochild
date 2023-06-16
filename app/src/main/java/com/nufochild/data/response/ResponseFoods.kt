/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.response

import com.google.gson.annotations.SerializedName

data class FoodsItem(

    @field:SerializedName("Lemak_g")
    val lemak: Float? = null,

    @field:SerializedName("Serat_g")
    val serat: Float? = null,

    @field:SerializedName("Nama")
    val nama: String? = null,

    @field:SerializedName("Energi_Kal")
    val energi: Float? = null,

    @field:SerializedName("Karbodhidrat_g")
    val karbohidrat: Float? = null,

    @field:SerializedName("Protein_g")
    val protein: Float? = null,

    val message: String? = null,

    val success: Boolean = true
)