/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nufochild.Destination
import com.nufochild.PortraitOrientation
import com.nufochild.R
import com.nufochild.showLoading
import com.nufochild.ui.components.CardNutritionProgress
import com.nufochild.ui.components.CardVideoList
import com.nufochild.ui.components.TopBarMain
import com.nufochild.ui.theme.Yellow700
import com.nufochild.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {
    val ctx = LocalContext.current
    PortraitOrientation(context = ctx)
    val viewModel = getViewModel<MainViewModel>()
    LaunchedEffect(Unit) {
        viewModel.getVideo()
    }
    val responseVideoData = viewModel.responseVideoData.collectAsState()
    val videos = responseVideoData.value
    var videoId by remember {
        mutableStateOf("")
    }
    val loading = viewModel.showLoading.collectAsState()
    val isLoading = loading.value

    val energy by remember {
        mutableStateOf(0.1f)
    }
    val energyvalue: Int = (energy * 100).toInt()
    val protein by remember {
        mutableStateOf(0.2f)
    }
    val proteinvalue: Int = (protein * 100).toInt()
    val fat by remember {
        mutableStateOf(0.3f)
    }
    val fatvalue: Int = (fat * 100).toInt()
    val fiber by remember {
        mutableStateOf(0.3f)
    }
    val fibervalue: Int = (fiber * 100).toInt()
    val carbohydrate by remember {
        mutableStateOf(0.9f)
    }
    val carbohydratevalue: Int = (carbohydrate * 100).toInt()

    Scaffold(topBar = {
        TopBarMain(navController)
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                Column(
                    modifier = Modifier
                        .background(color = Yellow700)
                        .fillMaxWidth()
                        .fillMaxHeight(0.8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        text = stringResource(id = R.string.nutrition_percentage),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                    ) {
                        CardNutritionProgress(
                            title = stringResource(id = R.string.protein),
                            progressValue = proteinvalue.toString(),
                            progress = protein
                        )
                        CardNutritionProgress(
                            title = stringResource(id = R.string.energy),
                            progressValue = energyvalue.toString(),
                            progress = energy
                        )
                        CardNutritionProgress(
                            title = stringResource(id = R.string.fat),
                            progressValue = fatvalue.toString(),
                            progress = fat
                        )
                        CardNutritionProgress(
                            title = stringResource(id = R.string.fiber),
                            progressValue = fibervalue.toString(),
                            progress = fiber
                        )
                        CardNutritionProgress(
                            title = stringResource(id = R.string.carbohydrates),
                            progressValue = carbohydratevalue.toString(),
                            progress = carbohydrate
                        )
                    }
                }
            }
            items(videos ?: emptyList()) {
                if (isLoading) {
                    showLoading()
                } else {
                    CardVideoList(
                        modifier = Modifier
                            .clickable {
                                videoId = it.url.toString()
                                navController.navigate(
                                    route = Destination.Video.setID(
                                        videoId
                                    )
                                )
                            },
                        image = it.image!!,
                        title = it.title!!
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    val ctx = LocalContext.current
    HomeScreen(navController = NavHostController(ctx))
}