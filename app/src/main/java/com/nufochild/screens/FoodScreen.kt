/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nufochild.R
import com.nufochild.data.general.Food
import com.nufochild.ui.components.CardFoodList
import com.nufochild.ui.components.TopBarBackButton
import com.nufochild.ui.theme.Yellow500
import com.nufochild.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodListScreen(
    navController: NavHostController,
) {
    val viewModel = getViewModel<MainViewModel>()
    LaunchedEffect(Unit) {
        viewModel.getFoods()
    }
    var update by remember { mutableStateOf(false) }
    val foodss = viewModel.foods.collectAsState()
    val foods = foodss.value
    var food: List<Food> by remember {
        mutableStateOf(emptyList())
    }

    Scaffold(topBar = {
        TopBarBackButton(onclick = { navController.navigateUp() }, actions = {})
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = Yellow500),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(3.dp),
                contentScale = ContentScale.Crop
            )
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(foods ?: emptyList()) {
                    CardFoodList(
                        onChecked = {
                            viewModel.showDialog()
                            update = true
                            food = listOf(
                                Food(
                                    "",
                                    it.karbohidrat!!,
                                    it.serat!!,
                                    it.lemak!!,
                                    it.energi!!,
                                    it.protein!!
                                )
                            )
                        },
                        text = it.nama ?: "",
                        protein = it.protein ?: 0f,
                        energy = it.energi ?: 0f,
                        fat = it.lemak ?: 0f,
                        fiber = it.serat ?: 0f,
                        carbohydrate = it.karbohidrat ?: 0f
                    )
                    if (update) {
                        Log.i("xxxnc", food.toString())
                        update = false
                        Log.i("xxxnc", update.toString())
                        val data = food.firstOrNull()
                        LaunchedEffect(key1 = Unit) {
                            viewModel.updateNutritionValue(
                                data?.protein ?: 0f,
                                data?.energy ?: 0f,
                                data?.fat ?: 0f,
                                data?.fiber ?: 0f,
                                data?.carbo ?: 0f
                            )
                            food = emptyList()
                        }
                    }
                }
            }
        }
    }



    if (viewModel.isDialogShown) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDialog() },
            confirmButton = {
                Button(onClick = { viewModel.dismissDialog() }) {
                    Text(text = "Ok")
                }
            },
            title = { Text(text = stringResource(id = R.string.titleSuccessDialog)) },
            text = { Text(text = stringResource(id = R.string.foodChecked)) }
        )
    }
}