/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.components.TopBarBackButton
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetectingImageScreen(
    painter: Uri,
    contentDescription: String?,
    onBackClicked: () -> Unit
) {
    val detectingString = stringResource(id = R.string.detecting)
    var name by remember { mutableStateOf(detectingString) }
    var showLoading by remember { mutableStateOf(true) }
    var result by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            delay(3000)
            showLoading = false
            name = "Ote ote"
            result = true
        }
    }
    Scaffold(topBar = {
        TopBarBackButton(
            onclick = onBackClicked
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
                        AsyncImage(
                            model = painter.toString(),
                            contentDescription = contentDescription,
                            modifier = Modifier
                                .fillMaxSize(1f)
                                .clip(shape = RoundedCornerShape(20.dp)),
                            contentScale = ContentScale.Crop,
                        )
                        if (showLoading) {
                            CircularProgressIndicator()
                        }
                    }
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                    )
                    if (result) {
                        MyButton(onClick = { /*TODO*/ }, text = "Kirim")
                    }
                }
            }
        }
    }
}

