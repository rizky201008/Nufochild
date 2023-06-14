/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.data.response

import com.google.gson.annotations.SerializedName

data class Foods(

    @field:SerializedName("Foods")
    val foods: List<FoodsItem?>? = null,
)

data class FoodsItem(

    @field:SerializedName("Lemak")
    val lemak: Int? = null,

    @field:SerializedName("Serat")
    val serat: Int? = null,

    @field:SerializedName("Nama")
    val nama: String? = null,

    @field:SerializedName("Energi")
    val energi: Int? = null,

    @field:SerializedName("Karbohidrat")
    val karbohidrat: Int? = null,

    @field:SerializedName("ID")
    val iD: Int? = null,

    @field:SerializedName("Protein")
    val protein: Int? = null,

    val message: String? = null,

    val success: Boolean = true
)