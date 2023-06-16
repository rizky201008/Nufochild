/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nufochild.data.general.Food
import com.nufochild.repository.CameraRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class CameraViewModel(private val repository: CameraRepository) : ViewModel() {
    var isLoading by mutableStateOf(false)
        private set

    var isError by mutableStateOf(0)
        private set

    private val _foodData = MutableStateFlow<Food?>(null)
    val foodData: StateFlow<Food?> get() = _foodData

    fun postImage(file: File) {
        Log.i("xxxncVM", "postImage Hit")
        isLoading = true
        viewModelScope.launch {
            val response = repository.uploadImage(file)
            Log.i("xxxncVM", response.toString())
            if (response[0]?.success!!) {
                isError = 1
                _foodData.value =
                    Food(
                        response[0]?.nama,
                        response[0]?.karbohidrat,
                        response[0]?.serat,
                        response[0]?.lemak,
                        response[0]?.energi,
                        response[0]?.protein
                    )
                isLoading = false
            } else {
                isLoading = false
                isError = 2
            }
        }
    }
}