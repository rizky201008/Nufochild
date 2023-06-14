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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nufochild.Destination
import com.nufochild.R
import com.nufochild.ui.components.CardVideoList
import com.nufochild.ui.components.TopBarMain
import com.nufochild.ui.theme.White
import com.nufochild.ui.theme.Yellow500
import com.nufochild.ui.theme.Yellow700
import com.nufochild.viewmodel.MainViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: MainViewModel = koinViewModel()
) {
    viewModel.getVideo()
    val videoDataState by viewModel.videoData.collectAsState()
    val videos = remember { videoDataState ?: emptyList() }
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
                        Text(
                            text = stringResource(id = R.string.protein),
                            color = White,
                            fontSize = 15.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),
                                progress = protein,
                                color = Yellow500,
                                trackColor = White
                            )
                            Text(text = "$proteinvalue%", color = White, fontSize = 10.sp)
                        }
                        Text(
                            text = stringResource(id = R.string.energy),
                            color = White,
                            fontSize = 15.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),
                                progress = energy,
                                color = Yellow500,
                                trackColor = White
                            )
                            Text(text = "$energyvalue%", color = White, fontSize = 10.sp)
                        }
                        Text(
                            text = stringResource(id = R.string.fat),
                            color = White,
                            fontSize = 15.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),
                                progress = fat,
                                color = Yellow500,
                                trackColor = White
                            )
                            Text(text = "$fatvalue%", color = White, fontSize = 10.sp)
                        }
                        Text(
                            text = stringResource(id = R.string.fiber),
                            color = White,
                            fontSize = 15.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),
                                progress = fiber,
                                color = Yellow500,
                                trackColor = White
                            )
                            Text(text = "$fibervalue%", color = White, fontSize = 10.sp)
                        }
                        Text(
                            text = stringResource(id = R.string.carbohydrates),
                            color = White,
                            fontSize = 15.sp
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            LinearProgressIndicator(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(30.dp)
                                    .clip(shape = RoundedCornerShape(10.dp)),
                                progress = carbohydrate,
                                color = Yellow500,
                                trackColor = White
                            )
                            Text(text = "$carbohydratevalue%", color = White, fontSize = 10.sp)
                        }
                    }
                }
            }
            items(videos) {
                CardVideoList(
                    modifier = Modifier
                        .clickable {
                            navController.navigate(route = Destination.Video.setID(it.url))
                        },
                    image = it.image,
                    title = it.title
                )
            }
        }
    }
}