/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nufochild.R
import com.nufochild.data.DetailUser
import com.nufochild.ui.components.CardDetailUserList
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.components.TopBarBackButton
import com.nufochild.ui.theme.Yellow500
import com.nufochild.ui.theme.Yellow700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController) {
    val lists = listOf(
        DetailUser("Joko", "Male", 30, "abcdefg", 3)
    )
    Scaffold(topBar = {
        TopBarBackButton(
            onclick = {
                navController.navigateUp()
            },
            title = lists[0].name ?: ""
        )
    }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Yellow500)
                .padding(innerPadding)
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
                    CardDetailUserList(
                        onChecked = {},
                        text = lists[0].age.toString(),
                        title = stringResource(id = R.string.label_age)
                    )
                }
                item {
                    CardDetailUserList(
                        onChecked = {},
                        text = lists[0].gender ?: "",
                        title = stringResource(id = R.string.label_gender)
                    )
                }
                item {
                    CardDetailUserList(
                        onChecked = {},
                        text = lists[0].weight.toString() ?: "",
                        title = stringResource(id = R.string.label_weight)
                    )
                }
                item {
                    CardDetailUserList(
                        onChecked = {},
                        text = lists[0].height.toString() ?: "",
                        title = stringResource(id = R.string.label_height)
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
}

@Composable
fun Test() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ABCDE")
    }
}