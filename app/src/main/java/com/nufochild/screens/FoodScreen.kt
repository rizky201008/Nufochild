/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nufochild.R
import com.nufochild.data.FoodsItem
import com.nufochild.ui.components.CardList
import com.nufochild.ui.theme.NufochildTheme
import com.nufochild.ui.theme.Yellow200
import com.nufochild.ui.theme.Yellow500
import com.nufochild.ui.theme.Yellow900

@Composable
fun FoodListScreen() {
    val list = listOf(
        FoodsItem(100, 100, "Nasi", 100, 100, 1, 100),
        FoodsItem(200, 200, "Kacang", 200, 200, 2, 200),
        FoodsItem(300, 300, "Melon", 300, 300, 3, 300),
        FoodsItem(400, 400, "Semangka", 400, 400, 4, 400),
        FoodsItem(500, 500, "Manggis", 500, 500, 5, 500),
        FoodsItem(600, 600, "Nanas", 600, 600, 6, 600),
        FoodsItem(700, 700, "Apel", 700, 700, 7, 700),
        FoodsItem(800, 800, "Lemon", 800, 800, 8, 800),
        FoodsItem(900, 900, "Jeruk", 900, 900, 9, 900),
        FoodsItem(1000, 1000, "Jambu", 1000, 1000, 10, 1000),
    )
    val ctx = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Yellow500)
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
            items(list) {
                CardList(onClick = { /*TODO*/ }, onChecked = {}, text = it.nama!!)
            }
        }
    }
}

@Preview
@Composable
fun FoodListScreenPreview() {
    NufochildTheme {
        FoodListScreen()
    }
}