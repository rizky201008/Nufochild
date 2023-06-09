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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nufochild.Destination
import com.nufochild.R
import com.nufochild.ui.components.CardDetailUserList
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.components.TopBarBackButton
import com.nufochild.ui.theme.Yellow500
import com.nufochild.ui.theme.Yellow700
import com.nufochild.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen(navController: NavHostController) {
    val viewModel = getViewModel<MainViewModel>()
    val dialog = viewModel.isDialogShown

    LaunchedEffect(Unit) {
        viewModel.getDetailUser()
    }

    if (dialog){
        AlertDialog(
            onDismissRequest = {  },
            confirmButton = {
                Button(onClick = { navController.navigateUp() }) {
                    Text(text = stringResource(id = R.string.back))
                }
                Button(onClick = { navController.navigate(Destination.UpdateProfile.route) }) {
                    Text(text = "Ok")
                }
            },
            title = { Text(text = "Oops") },
            text = { Text(text = stringResource(id = R.string.update_your_profile)) }
        )
    }
    val list = viewModel.detailUser.collectAsState()
    val lists = list.value
    Scaffold(topBar = {
        TopBarBackButton(
            onclick = {
                navController.navigateUp()
            },
            title = stringResource(id = R.string.update),
            actions = {}
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
            if (viewModel.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(10.dp),
                    color = Yellow700,
                    strokeWidth = 5.dp
                )
            } else {
                LazyColumn {
                    item {
                        CardDetailUserList(
                            text = lists?.age?.toString() ?: "0",
                            title = stringResource(id = R.string.label_age)
                        )
                    }
                    item {
                        CardDetailUserList(
                            text = lists?.gender ?: "0",
                            title = stringResource(id = R.string.label_gender)
                        )
                    }
                    item {
                        CardDetailUserList(
                            text = lists?.weight?.toString() ?: "0",
                            title = stringResource(id = R.string.label_weight)
                        )
                    }
                    item {
                        CardDetailUserList(
                            text = lists?.height?.toString() ?: "0",
                            title = stringResource(id = R.string.label_height)
                        )
                    }
                    item {
                        MyButton(
                            onClick = { navController.navigate(Destination.UpdateProfile.route) },
                            text = stringResource(id = R.string.update),
                            color = Yellow700
                        )
                    }
                }
            }
        }
    }
}