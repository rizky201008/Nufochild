/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.module

import com.nufochild.api.ApiServices
import com.nufochild.repository.FoodRepository
import com.nufochild.repository.VideoRepository
import com.nufochild.viewmodel.ViewModelFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://database-api-rezeodju2q-et.a.run.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServices::class.java)
    }
    single {
        VideoRepository()
    }
    single {
        FoodRepository()
    }
    single<ViewModelFactory> {
        ViewModelFactory(get(), get())
    }
}