/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import com.nufochild.data.FoodData
import com.nufochild.data.FoodsItem
import com.nufochild.data.Video
import com.nufochild.data.videoData

class MainRepository {
    fun getFood(): List<FoodsItem> {
        return FoodData.data
    }

    fun getVideos(): List<Video> {
        return videoData
    }
}