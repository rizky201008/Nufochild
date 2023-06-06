/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel(){
    var isDialogShown by mutableStateOf(false)
        private set

    fun onPurchaseClick(){
        isDialogShown = true
    }

    fun onDismissDialog(){
        isDialogShown = false
    }
}