package com.nufochild.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nufochild.Destination
import com.nufochild.R
import com.nufochild.ui.theme.Yellow700
import com.nufochild.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.getViewModel

@Composable
fun SplashScreen(navController: NavController) {
    val viewModel = getViewModel<MainViewModel>()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(key1 = Unit) {
        val token = viewModel.getToken()
        coroutineScope.launch {
            delay(3000)
            if (token !== "") {
                navController.popBackStack()
                navController.navigate(Destination.Home.route)
            } else {
                navController.popBackStack()
                navController.navigate(Destination.Login.route)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Yellow700),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .padding(10.dp),
            painter = painterResource(id = R.drawable.logo_transparan),
            contentDescription = null
        )
    }
}