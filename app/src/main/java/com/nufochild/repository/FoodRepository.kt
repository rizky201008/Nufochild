/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import com.nufochild.data.FoodData
import com.nufochild.data.FoodsItem

class FoodRepository {
    fun getFood(): List<FoodsItem> {
        return FoodData.data
    }
}