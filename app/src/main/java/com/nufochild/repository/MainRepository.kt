/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.repository

import android.content.Context
import android.util.Log
import com.nufochild.api.ApiServices
import com.nufochild.data.general.ResponseVideoItem
import com.nufochild.data.general.SessionPref
import com.nufochild.data.general.UserNutritions
import com.nufochild.data.local.NutritionDB
import com.nufochild.data.local.NutritionDao
import com.nufochild.data.local.UserDetailDB
import com.nufochild.data.local.UserDetailDao
import com.nufochild.data.request.RequestLogin
import com.nufochild.data.request.RequestRegister
import com.nufochild.data.request.RequestUpdateProfile
import com.nufochild.data.response.FoodsItem
import com.nufochild.data.response.Nutrition
import com.nufochild.data.response.ResponseDetailUser
import com.nufochild.data.response.ResponseLogin
import com.nufochild.data.response.ResponseRegister
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainRepository(
    private val sessionPref: SessionPref,
    context: Context
) {
    private val nutritionDao: NutritionDao
    private val userDetailDao: UserDetailDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NutritionDB.getDatabase(context)
        val db1 = UserDetailDB.getDatabase(context)
        nutritionDao = db.nutritionDao()
        userDetailDao = db1.detailUserDao()
    }

    fun getNutrition(): List<UserNutritions?> = nutritionDao.getAllNutrition()

    fun insertNutrition(nutrition: UserNutritions) {
        executorService.execute { nutritionDao.insert(nutrition) }
    }

    fun updateNutrition(
        protein: Float,
        energy: Float,
        fat: Float,
        fiber: Float,
        carbohydrate: Float
    ) {
        executorService.execute {
            nutritionDao.updateNutritionValue(
                vprotein = protein,
                venergy = energy,
                vfat = fat,
                vfiber = fiber,
                vcarbohydrate = carbohydrate
            )
        }
    }

    suspend fun insertUserDetail(profile: RequestUpdateProfile): Nutrition {
        val apiServices = ApiServices.getInstance()
        val token = getToken()

        return try {
            if (getProfileStatus()) {
                val response = apiServices.updateProfile("Bearer $token", profile)
                if (response.isSuccessful) {
                    response.body()?.nutrition!!
                } else {
                    Nutrition(error = true)
                }
            } else {
                val response = apiServices.newProfile("Bearer $token", profile)
                if (response.isSuccessful) {
                    response.body()?.nutrition!!
                } else {
                    Nutrition(error = true)
                }
            }

        } catch (e: Exception) {
            Log.i("xxxnc", "Error update profile $e")
            Nutrition(error = true)
        }
    }

    suspend fun getVideos(): List<ResponseVideoItem>? {
        val apiServices = ApiServices.getInstance()
        val response = apiServices.videos()
        return try {
            if (response.isSuccessful) {
                response.body()
            } else {
                listOf(ResponseVideoItem(success = false))
            }
        } catch (e: Exception) {
            listOf(ResponseVideoItem(success = false))
        }
    }

    suspend fun getFood(): List<FoodsItem> {
        val apiServices = ApiServices.getInstance()
        val response = apiServices.meals()
        return try {
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                listOf(FoodsItem(success = false))
            }
        } catch (e: Exception) {
            listOf(
                FoodsItem(
                    success = false
                )
            )
        }
    }

    fun getToken(): String = sessionPref.getToken()
    fun getProfileStatus(): Boolean = sessionPref.getProfileStatus()
    fun setProfileStatus(value: Boolean) = sessionPref.setProfileStatus(value)
    fun getNutritionStatus(): Boolean = sessionPref.getNutritionStatus()
    fun setNutritionStatus(value: Boolean) = sessionPref.setNutritionStatus(value)

    suspend fun getProfile(): ResponseDetailUser {
        val apiServices = ApiServices.getInstance()
        val token = getToken()
        val bearerToken = "Bearer $token"
        return try {
            val response = apiServices.profile(bearerToken)
            Log.i("xxxnc", "getDetail $response")
            if (response.isSuccessful) {
                response.body()!!
            } else {
                ResponseDetailUser(
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    response.message(),
                    false
                )
            }
        } catch (e: Exception) {
            ResponseDetailUser(
                null,
                null,
                null,
                null,
                null,
                null,
                e.localizedMessage,
                false
            )
        }
    }


    suspend fun loginAccount(requestLogin: RequestLogin): ResponseLogin {
        val apiService = ApiServices.getInstance()
        return try {
            val responseLogin = apiService.login(requestLogin)
            if (responseLogin.isSuccessful) {
                sessionPref.setToken(responseLogin.body()!!.accessToken.toString())
                responseLogin.body()!!
            } else {
                ResponseLogin(null, null, responseLogin.body()!!.message, false)
            }
        } catch (e: Exception) {
            ResponseLogin(null, null, e.localizedMessage, false)
        }
    }

    suspend fun registerAccount(value: RequestRegister): ResponseRegister {
        val apiService = ApiServices.getInstance()
        return try {
            val response = apiService.register(value)
            if (response.isSuccessful) {
                response.body()!!
            } else {
                ResponseRegister(response.body()!!.message, false)
            }
        } catch (e: Exception) {
            val response = ResponseRegister(e.localizedMessage, false)
            response
        }
    }
}