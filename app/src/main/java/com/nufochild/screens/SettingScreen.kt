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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nufochild.R
import com.nufochild.data.DetailUser
import com.nufochild.ui.components.CardDetailList
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.components.TopBarBackButton
import com.nufochild.ui.theme.Yellow500
import com.nufochild.ui.theme.Yellow700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController) {
    val lists = listOf(
        DetailUser("Joko", 30, 30, 3)
    )
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
        LazyColumn {
            item {
                TopBarBackButton(onclick = {
                    navController.navigateUp()
                })
            }
            items(lists) {
                CardDetailList(
                    onChecked = {},
                    text = it.name,
                    title = stringResource(id = R.string.label_name)
                )
                CardDetailList(
                    onChecked = {},
                    text = it.age.toString(),
                    title = stringResource(id = R.string.label_age)
                )
                CardDetailList(
                    onChecked = {},
                    text = it.height.toString(),
                    title = stringResource(id = R.string.label_height)
                )
                CardDetailList(
                    onChecked = {},
                    text = it.weight.toString(),
                    title = stringResource(id = R.string.label_weight)
                )
            }

            item {
                MyButton(
                    onClick = { /*TODO*/ },
                    text = stringResource(id = R.string.save),
                    color = Yellow700
                )
            }
        }
    }
}