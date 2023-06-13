/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nufochild.data.general.Food
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.Video
import com.nufochild.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository,
) : ViewModel() {
    private val _videoData = MutableStateFlow<List<Video>?>(null)
    val videoData: MutableStateFlow<List<Video>?> get() = _videoData
    fun getVideo() {
        val videos = repository.getVideos()
        _videoData.value = videos
    }

    var isDialogShown by mutableStateOf(false)
        private set

    fun onPurchaseClick() {
        isDialogShown = true
    }

    fun onDismissDialog() {
        isDialogShown = false
    }

    private val _sortedFoods = MutableStateFlow(
        repository.getFood()
            .sortedBy { it.nama }
    )

    val sortedFoods: MutableStateFlow<List<FoodsItem>> get() = _sortedFoods

    private val _foodData = MutableStateFlow<Food?>(null)
    val foodData: StateFlow<Food?> get() = _foodData

    private val _showLoading = MutableStateFlow(false)
    val showLoading: StateFlow<Boolean> get() = _showLoading

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    private val _token = MutableStateFlow<String?>("")
    val token: StateFlow<String?> get() = _token

    fun setFood(food: Food) {
        _foodData.value = food
    }

    fun resetFood() {
        _foodData.value = null
    }

    fun getToken() {
        val token = repository.getToken()
        _token.value = token
    }

    fun login(value: RequestLogin) {
        _showLoading.value = true
        viewModelScope.launch {
            val response = repository.loginAccount(value)
            _showLoading.value = false
            _loginSuccess.value = response
        }
    }
}