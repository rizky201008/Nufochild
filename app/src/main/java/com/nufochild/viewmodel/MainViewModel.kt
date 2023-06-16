/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nufochild.data.general.ResponseVideoItem
import com.nufochild.data.general.UserNutritions
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.request.RequestRegister
import com.nufochild.data.request.RequestUpdateProfile
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

    var isLoading by mutableStateOf(false)
        private set

    fun showDialog() {
        isDialogShown = true
    }

    fun dismissDialog() {
        isDialogShown = false
    }

    private val _foods = MutableStateFlow<List<FoodsItem>?>(emptyList())

    val foods: StateFlow<List<FoodsItem>?> get() = _foods

    var isEmailError by mutableStateOf(false)
        private set
    var isUpdateError by mutableStateOf(false)
        private set
    var isNameError by mutableStateOf(false)
        private set
    var isPasswordError by mutableStateOf(false)
        private set
    var isRegister by mutableStateOf(false)
        private set
    var isLogin by mutableStateOf(false)
        private set
    var isProfileStatuses by mutableStateOf(false)
        private set

    val _token = MutableStateFlow("")
    val token: StateFlow<String> get() = _token

    val _emailErrMsg = MutableStateFlow("")
    val emailErrMsg: StateFlow<String> get() = _emailErrMsg

    private val _passErrMsg = MutableStateFlow("")
    val passErrMsg: StateFlow<String> get() = _passErrMsg

    private val _nameErrMsg = MutableStateFlow("")
    val nameErrMsg: StateFlow<String> get() = _nameErrMsg

    private val _detailUser = MutableStateFlow<ResponseDetailUser?>(null)
    val detailUser: StateFlow<ResponseDetailUser?> get() = _detailUser

    fun getNutrition(): List<UserNutritions?> = repository.getNutrition()

    fun insertNutrition(nutritions: UserNutritions) {
        repository.insertNutrition(nutritions)
    }

    fun getFoods() {
        isLoading = true
        viewModelScope.launch {
            val data = repository.getFood()
            _foods.value = data
            isLoading = false
        }
    }

    fun getDetailUser() {
        isLoading = true
        viewModelScope.launch {
            val detail = repository.getProfile()
            Log.i("xxxnc","getDetailVm $detail")
            if (detail!!.success) {
                _detailUser.value = detail
                isLoading = false
            } else {
                isLoading = false
                showDialog()
                isProfileStatuses = false
                _detailUser.value = detail
            }
        }
    }

    fun getToken(): String {
        return repository.getToken()
        Log.i("xxxnc", "getToken VM $token")
    }

    fun setProfileStatus(value: Boolean) = repository.setProfileStatus(value)

//    fun getNutritionStatus() {
//        Log.i("xxxnc", "VM getNutritionStatus")
//        nutritionStatus = repository.getProfileStatus()
//    }

    fun register(value: RequestRegister) {
        isLoading = true
        if (value.name!!.isNotEmpty() && value.email!!.isNotEmpty() && value.password2!!.isNotEmpty() && value.password!!.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(value.email).matches()) {
                isLoading = false
                isRegister = false
                isEmailError = true
                _emailErrMsg.value = "Email is invalid"
            } else if (value.password.length < 8) {
                isLoading = false
                isRegister = false
                _passErrMsg.value = "Password length minimum 8 character"
                isPasswordError = true
            } else if (value.name.length < 3) {
                isNameError = true
                isLoading = false
                isRegister = false
                _nameErrMsg.value = "Name length minimum 3 character"
            } else if (value.password != value.password2) {
                isLoading = false
                isPasswordError = true
                isRegister = false
                _passErrMsg.value = "Confirm password doesn't match"
            } else {
                viewModelScope.launch {
                    val response = repository.registerAccount(value)
                    isLoading = false
                    if (response.success) {
                        isPasswordError = false
                        isEmailError = false
                        isRegister = true

                    } else {
                        isPasswordError = false
                        isEmailError = false
                        isRegister = true
                    }
                }
            }
        } else {
            isLoading = false
            isPasswordError = true
            isEmailError = true
            isNameError = true
            isRegister = false
            _passErrMsg.value = "This field is required!"
            _nameErrMsg.value = "This field is required!"
            _emailErrMsg.value = "This field is required!"
        }
    }

    fun login(value: RequestLogin) {
        isLoading = true
        if (value.email!!.isNotEmpty() && value.password!!.isNotEmpty()) {
            if (!Patterns.EMAIL_ADDRESS.matcher(value.email).matches()) {
                isLogin = false
                isLoading = false
                isEmailError = true
                _emailErrMsg.value = "Email is invalid"
            } else {
                viewModelScope.launch {
                    val response = repository.loginAccount(value)
                    if (response.success) {
                        isLoading = false
                        isPasswordError = false
                        isEmailError = false
                        isLogin = true
                    } else {
                        isLoading = false
                        isPasswordError = false
                        isEmailError = false
                        isLogin = false
                    }
                }
            }
        } else {
            isLogin = false
            isLoading = false
            isPasswordError = true
            isEmailError = true
            _passErrMsg.value = "This field is required!"
            _emailErrMsg.value = "This field is required!"
        }
    }

    fun getVideo() {
        Log.i("xxxnc", "getVideo VM")
        isLoading = true
        viewModelScope.launch {
            val videos = repository.getVideos()
            _response_videoData.value = videos
            isLoading = false
        }
    }

    fun updateDetail(value: RequestUpdateProfile) {
        Log.i("xxxnc", "VM updateDetail")
        viewModelScope.launch {
            val data = repository.insertUserDetail(value)
            isLoading = false
            if (!data.error) {
                insertNutrition(
                    UserNutritions(
                        null,
                        data.protein,
                        0f,
                        data.energy,
                        0f,
                        data.fat,
                        0f,
                        data.fiber,
                        0f,
                        data.carbo,
                        0f
                    )
                )
            } else {
                showDialog()
            }
        }
    }
}