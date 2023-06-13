/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import com.nufochild.data.general.SessionPref
import com.nufochild.data.response.FoodData
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.Video
import com.nufochild.data.response.videoData

class MainRepository(
    private val sessionPref: SessionPref,
) {
    fun getVideos(): List<Video> {
        return videoData
    }

    fun getFood(): List<FoodsItem> {
        return FoodData.data
    }

    fun getToken(): String {
        return sessionPref.getToken()
    }
}