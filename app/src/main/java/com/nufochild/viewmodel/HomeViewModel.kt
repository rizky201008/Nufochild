/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.viewmodel

import androidx.lifecycle.ViewModel
import com.nufochild.data.Video
import com.nufochild.repository.VideoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: VideoRepository,
) : ViewModel() {
    private val _videoData = MutableStateFlow<List<Video>?>(null)
    val videoData: MutableStateFlow<List<Video>?> get() = _videoData
    fun getVideo() {
        val videos = repository.getVideos()
        _videoData.value = videos
    }

    init {
        getVideo()
    }
}