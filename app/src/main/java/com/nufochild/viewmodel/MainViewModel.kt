/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.viewmodel

import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nufochild.data.general.Food
import com.nufochild.data.general.ResponseVideoItem
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.request.RequestRegister
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.ResponseDetailUser
import com.nufochild.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: MainRepository,
) : ViewModel() {
    private val _response_videoData = MutableStateFlow<List<ResponseVideoItem>?>(null)
    val responseVideoData: StateFlow<List<ResponseVideoItem>?> get() = _response_videoData

    var isDialogShown by mutableStateOf(false)
        private set

    fun onPurchaseClick() {
        isDialogShown = true
    }

    fun onDismissDialog() {
        isDialogShown = false
    }

    private val _foods = MutableStateFlow<List<FoodsItem>>(emptyList())

    val foods: StateFlow<List<FoodsItem>> get() = _foods

    private val _foodData = MutableStateFlow<Food?>(null)
    val foodData: StateFlow<Food?> get() = _foodData

    private val _showLoading = MutableStateFlow(true)
    val showLoading: StateFlow<Boolean> get() = _showLoading

    private val _emailError = MutableStateFlow(false)
    val emailError: StateFlow<Boolean> get() = _emailError

    private val _emailErrMsg = MutableStateFlow("")
    val emailErrMsg: StateFlow<String> get() = _emailErrMsg

    private val _passwordError = MutableStateFlow(false)
    val passwordError: StateFlow<Boolean> get() = _passwordError

    private val _passErrMsg = MutableStateFlow("")
    val passErrMsg: StateFlow<String> get() = _passErrMsg

    private val _nameError = MutableStateFlow(false)
    val nameError: StateFlow<Boolean> get() = _nameError

    private val _nameErrMsg = MutableStateFlow("")
    val nameErrMsg: StateFlow<String> get() = _nameErrMsg

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> get() = _loginSuccess

    private val _registerSuccess = MutableStateFlow(false)
    val registerSuccess: StateFlow<Boolean> get() = _registerSuccess

    private val _token = MutableStateFlow<String?>("")
    val token: StateFlow<String?> get() = _token

    private val _detailUser = MutableStateFlow<ResponseDetailUser?>(null)
    val detailUser: StateFlow<ResponseDetailUser?> get() = _detailUser

    fun getFoods() {
        _showLoading.value = true
        viewModelScope.launch {
            val data = repository.getFood()
            _foods.value = data
            _showLoading.value = false
        }
    }
    fun getDetailUser() {
        _showLoading.value = true
        viewModelScope.launch {
            val detailUser = repository.getProfile()
            if (detailUser.success) {
                _detailUser.value = detailUser
                _showLoading.value = false
            } else {

            }
        }
    }

    fun setFood(food: Food) {
        _foodData.value = food
    }

    fun getToken() {
        val token = repository.getToken()
        _token.value = token
    }

    fun register(value: RequestRegister) {
        _showLoading.value = true
        if (value.name!!.isNotEmpty() && value.email!!.isNotEmpty() && value.password2!!.isNotEmpty() && value.password!!.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(value.email).matches()) {
                _showLoading.value = false
                _emailError.value = true
                _emailErrMsg.value = "Email is invalid"
            } else if (value.password!!.length < 8) {
                _showLoading.value = false
                _passErrMsg.value = "Password length minimum 8 character"
                _passwordError.value = true
            } else if (value.name!!.length < 3) {
                _nameError.value = true
                _showLoading.value = false
                _nameErrMsg.value = "Name length minimum 3 character"
            } else if (value!!.password != value!!.password2) {
                _showLoading.value = false
                _passwordError.value = true
                _passErrMsg.value = "Confirm password doesn't match"
            } else {
                viewModelScope.launch {
                    val response = repository.registerAccount(value)
                    if (response.success) {
                        _showLoading.value = false
                        _passwordError.value = false
                        _emailError.value = false
                        _registerSuccess.value = true
                    } else {
                        _showLoading.value = false
                        _passwordError.value = false
                        _emailError.value = false
                        _registerSuccess.value = false
                    }
                }
            }
        } else {
            _showLoading.value = false
            _passwordError.value = true
            _emailError.value = true
            _nameError.value = true
            _passErrMsg.value = "This field is required!"
            _nameErrMsg.value = "This field is required!"
            _emailErrMsg.value = "This field is required!"
        }
    }

    fun login(value: RequestLogin) {
        _showLoading.value = true
        if (value.email!!.isNotEmpty() && value.password!!.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(value.email).matches()) {
                _loginSuccess.value = false
                _showLoading.value = false
                _emailError.value = true
                _emailErrMsg.value = "Email is invalid"
            } else {
                viewModelScope.launch {
                    val response = repository.loginAccount(value)
                    if (response.success) {
                        _showLoading.value = false
                        _passwordError.value = false
                        _emailError.value = false
                        _loginSuccess.value = true
                    } else {
                        _showLoading.value = false
                        _passwordError.value = false
                        _emailError.value = false
                        _loginSuccess.value = false
                    }
                }
            }
        } else {
            _loginSuccess.value = false
            _showLoading.value = false
            _passwordError.value = true
            _emailError.value = true
            _passErrMsg.value = "This field is required!"
            _emailErrMsg.value = "This field is required!"
        }
    }
    fun getVideo() {
        _showLoading.value = true
        viewModelScope.launch {
            val videos = repository.getVideos()
            _response_videoData.value = videos
            _showLoading.value = false
        }
    }
}