/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import android.content.Context
import android.util.Log
import com.nufochild.api.ApiServices
import com.nufochild.data.general.SessionPref
import com.nufochild.data.response.NutritionDataItem
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class CameraRepository(
    private val sessionPref: SessionPref,
    context: Context
) {
    suspend fun uploadImage(file: File): List<NutritionDataItem?> {
        val api = ApiServices.getInstance()
        try {
            val response = api.postImage(
                MultipartBody.Part.createFormData(
                    "image",
                    file.name,
                    file.asRequestBody()
                )
            )
            Log.i("xxxncRepo", response.toString())
            return if (response.isSuccessful) {
                response.body()?.nutritionData ?: emptyList()
            } else {
                Log.i("xxxncRepo", response.body()?.error.toString())
                listOf(NutritionDataItem(success = false))
            }
        } catch (e: Exception) {
            return listOf(NutritionDataItem(success = false))
        }
    }

}