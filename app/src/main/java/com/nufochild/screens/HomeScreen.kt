/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nufochild.R
import com.nufochild.ui.components.TopBarMain
import com.nufochild.ui.customview.CustomCircularProgressIndicator
import com.nufochild.ui.theme.Yellow200
import com.nufochild.ui.theme.Yellow700
import com.nufochild.ui.theme.Yellow900

@Composable
fun HomeScreen(navController: NavHostController) {
    Box(
        modifier = Modifier
            .background(color = Yellow700)
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        LazyColumn(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                TopBarMain(navController)
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = R.string.nutrition_percentage),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(20.dp))

                CustomCircularProgressIndicator(
                    modifier = Modifier
                        .size(250.dp),
                    initialValue = 100f,
                    primaryColor = Yellow900,
                    secondaryColor = Yellow200,
                    circleRadius = 230f,
                    onPositionChange = {

                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}