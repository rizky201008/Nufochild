/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.di

import com.nufochild.repository.CameraRepository
import com.nufochild.repository.MainRepository
import com.nufochild.viewmodel.CameraViewModel
import com.nufochild.viewmodel.MainViewModel
import com.nufochild.viewmodel.ViewModelFactory
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        MainRepository(get(), androidContext())
    }
    single {
        CameraRepository(get(), androidContext())
    }
    single {
        ViewModelFactory(get(),get())
    }
    viewModel {
        MainViewModel(get())
    }
    viewModel{
        CameraViewModel(get())
    }
}