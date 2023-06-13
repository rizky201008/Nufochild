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
)

object FoodData {
    val data = listOf(
        FoodsItem(100, 100, "Nasi", 100, 100, 1, 100),
        FoodsItem(200, 200, "Kacang", 200, 200, 2, 200),
        FoodsItem(300, 300, "Melon", 300, 300, 3, 300),
        FoodsItem(400, 400, "Semangka", 400, 400, 4, 400),
        FoodsItem(500, 500, "Manggis", 500, 500, 5, 500),
        FoodsItem(600, 600, "Nanas", 600, 600, 6, 600),
        FoodsItem(700, 700, "Apel", 700, 700, 7, 700),
        FoodsItem(800, 800, "Lemon", 800, 800, 8, 800),
        FoodsItem(900, 900, "Jeruk", 900, 900, 9, 900),
        FoodsItem(1000, 1000, "Jambu", 1000, 1000, 10, 1000),
    )
}