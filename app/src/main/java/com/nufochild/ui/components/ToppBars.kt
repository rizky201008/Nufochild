/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nufochild.Destination
import com.nufochild.R
import com.nufochild.toCamera
import com.nufochild.ui.theme.Yellow700

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarMain(navController: NavHostController) {
    val ctx = LocalContext.current
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Destination.Setting.route)
            }) {
                Icon(Icons.Filled.Person, contentDescription = null)
            }
        },
        actions = {
            Image(
                painter = painterResource(id = R.drawable.ic_bagel),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate(Destination.Foods.route)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        toCamera(ctx)
                    }
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(id = R.color.yellow_700)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarBackButton(
    onclick: () -> Unit,
    title: String = "",
    actions: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(onClick = {
                onclick()
            }) {
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            actions
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Yellow700,
            scrolledContainerColor = Color.White,
            navigationIconContentColor = Color.White,
        ),
    )
}