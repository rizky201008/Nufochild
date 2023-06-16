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
    var isUpdateSuccess by mutableStateOf(false)
        private set
    var isNameError by mutableStateOf(false)
        private set
    var isPasswordError by mutableStateOf(false)
        private set
    var isRegister by mutableStateOf(false)
        private set
    var isRegisterFail by mutableStateOf(false)
        private set
    var isLogin by mutableStateOf(false)
        private set
    var isLoginFail by mutableStateOf(false)
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

    var protein by mutableStateOf(0f)
        private set
    var vprotein by mutableStateOf(0)
        private set
    var fat by mutableStateOf(0f)
        private set
    var vfat by mutableStateOf(0)
        private set
    var energy by mutableStateOf(0f)
        private set
    var venergy by mutableStateOf(0)
        private set
    var fiber by mutableStateOf(0f)
        private set
    var vfiber by mutableStateOf(0)
        private set
    var carbohydrates by mutableStateOf(0f)
        private set
    var vcarbohydrates by mutableStateOf(0)
        private set

    fun getUpdated(): Boolean = repository.getProfileStatus()
    fun setUpdated(value: Boolean) = repository.setProfileStatus(value)

    fun getNutrition() {
        Log.i("xxxnc", "getNutrition")
        val data = repository.getNutrition()
        if (data.isEmpty()) {

        } else {
            val dd1 = data[0]?.protein!!
            val d1 = data[0]?.vprotein!!
            val progress1 = d1
            val valueMax1 = dd1
            val percentage1 = (progress1 / valueMax1) * 100
            val roundedPercentage1 = Math.round(percentage1)
            protein = roundedPercentage1 / 100f
            vprotein = roundedPercentage1

            val dd2 = data[0]?.fat!!
            val d2 = data[0]?.vfat!!
            val progress2 = d2
            val valueMax2 = dd2
            val percentage2 = (progress2 / valueMax2) * 100
            val roundedPercentage2 = Math.round(percentage2)
            fat = roundedPercentage2 / 100f
            vfat = roundedPercentage2

            val dd3 = data[0]?.energy!!
            val d3 = data[0]?.venergy!!
            val progress3 = d3
            val valueMax3 = dd3
            val percentage3 = (progress3 / valueMax3) * 100
            val roundedPercentage3 = Math.round(percentage3)
            energy = roundedPercentage3 / 100f
            venergy = roundedPercentage3

            val dd4 = data[0]?.carbohydrate!!
            val d4 = data[0]?.vcarbohydrate!!
            val progress4 = d4
            val valueMax4 = dd4
            val percentage4 = (progress4 / valueMax4) * 100
            val roundedPercentage4 = Math.round(percentage4)
            carbohydrates = roundedPercentage4 / 100f
            vcarbohydrates = roundedPercentage4

            val dd5 = data[0]?.fiber!!
            val d5 = data[0]?.vfiber!!
            val progress5 = d5
            val valueMax5 = dd5
            val percentage5 = (progress5 / valueMax5) * 100
            val roundedPercentage5 = Math.round(percentage5)
            fiber = roundedPercentage5 / 100f
            vfiber = roundedPercentage5

        }
    }

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
            Log.i("xxxnc", "getDetailVm $detail")
            if (detail!!.success) {
                _detailUser.value = detail
                setUpdated(true)
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
                        showDialog()
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
                        showDialog()
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
                isUpdateSuccess = true
                setUpdated(true)
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

    fun updateNutritionValue(
        protein: Float,
        energy: Float,
        fat: Float,
        fiber: Float,
        carbohydrate: Float
    ) {
        repository.updateNutrition(protein, energy, fat, fiber, carbohydrate)
    }
}