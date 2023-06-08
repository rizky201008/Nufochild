/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nufochild.R
import com.nufochild.data.Food
import com.nufochild.repository.FoodRepository
import com.nufochild.ui.components.CardFoodList
import com.nufochild.ui.components.TopBarBackButton
import com.nufochild.ui.customview.FoodDialog
import com.nufochild.ui.theme.Yellow500
import com.nufochild.viewmodel.FoodViewModel
import com.nufochild.viewmodel.ViewModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodListScreen(
    navController: NavHostController,
    viewModel: FoodViewModel = viewModel(
        factory = ViewModelFactory(
            FoodRepository()
        )
    ),
) {
    val foods by viewModel.sortedFoods.collectAsState()
    Scaffold(
        topBar = {
            TopBarBackButton(onclick = { navController.navigateUp() })
        }
    ) { innerPadding ->
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
                items(foods) {
                    CardFoodList(
                        onClick = {
                            viewModel.setFood(
                                Food(
                                    name = it.nama!!,
                                    carbo = it.karbohidrat!!,
                                    fiber = it.serat!!,
                                    protein = it.protein!!,
                                    fat = it.lemak!!,
                                    energy = it.energi!!
                                )
                            )
                            viewModel.onPurchaseClick()
                        },
                        onChecked = {},
                        text = it.nama!!
                    )
                }
            }
        }
    }


    if (viewModel.isDialogShown) {
        val data by viewModel.foodData.collectAsState()
        FoodDialog(
            onDismiss = {
                viewModel.onDismissDialog()
            },
            title = stringResource(id = R.string.nutrition_detail_title),
            carbohydrates = data!!.carbo,
            energy = data!!.energy,
            fat = data!!.fat,
            fiber = data!!.fiber,
            protein = data!!.protein
        )
    }
}