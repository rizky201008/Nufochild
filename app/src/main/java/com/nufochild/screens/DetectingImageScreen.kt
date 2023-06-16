/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nufochild.R
import com.nufochild.ui.components.TopBarBackButton
import com.nufochild.ui.theme.Black
import com.nufochild.viewmodel.CameraViewModel
import org.koin.androidx.compose.getViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetectingImageScreen(
    painter: Uri,
    contentDescription: String?,
    onBackClicked: () -> Unit
) {
    val viewModel = getViewModel<CameraViewModel>()
    val detectingString = stringResource(id = R.string.detecting)
    val dataFood = viewModel.foodData.collectAsState()
    val food = dataFood.value

    Scaffold(topBar = {
        TopBarBackButton(
            onclick = onBackClicked,
            actions = {}
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                        val file = convertUrlToFile(painter)
                        LaunchedEffect(Unit) {
                            viewModel.postImage(file)
                        }
                        AsyncImage(
                            model = painter.toString(),
                            contentDescription = contentDescription,
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .clip(shape = RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop,
                        )
                        if (viewModel.isLoading) {
                            CircularProgressIndicator()
                        }
                    }
                    if (viewModel.isLoading) {
                        Text(
                            text = detectingString,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        )
                    }
                    if (viewModel.isError == 1) {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.label_name),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(
                                    text = food?.name!!,
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(), color = Black
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.energy),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(
                                    text = food?.energy.toString(),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(), color = Black
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.fat),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(
                                    text = food?.fat.toString(),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(), color = Black
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.protein),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(
                                    text = food?.protein.toString(),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(), color = Black
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.fiber),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(
                                    text = food?.fiber.toString(),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(), color = Black
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = stringResource(id = R.string.carbohydrates),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                                Text(
                                    text = food?.carbo.toString(),
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 20.sp,
                                )
                            }
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                color = Black
                            )
                        }
                    } else if (viewModel.isError == 2) {
                        Text(
                            text = stringResource(id = R.string.image_cannot_detected),
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                        )
                    }
                }
            }
        }
    }
}

fun convertUrlToFile(imageUrl: Uri): File {
    val file = File(imageUrl.path)
    return file
}

