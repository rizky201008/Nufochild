/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */
package com.nufochild.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nufochild.R
import com.nufochild.ui.theme.Black
import com.nufochild.ui.theme.Yellow200
import com.nufochild.ui.theme.Yellow500

@Composable
fun CardFoodList(
    text: String = "Makanan Ringan",
    onClick: () -> Unit,
    onChecked: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Yellow200, contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = text,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                fontSize = 25.sp,
                modifier = Modifier.fillMaxWidth(0.7f)
            )

            Image(painter = painterResource(id = R.drawable.ic_done),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onChecked() }
                    .background(color = Color.White, shape = CircleShape)
                    .padding(10.dp))
        }
    }
}

@Composable
fun CardVideoList(modifier: Modifier, image: String, title: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent, contentColor = Color.Transparent
            ),
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = Yellow500, shape = RoundedCornerShape(10.dp)
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop,
                )
            }
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal,
                color = Black,
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}

@Composable
fun CardDetailUserList(
    text: String = "Example Content",
    title: String = "Example Title",
    onChecked: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Yellow200, contentColor = Color.Black
        ),
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(70.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 12.sp,
                )
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    fontSize = 25.sp,
                )
            }

            Image(painter = painterResource(id = R.drawable.ic_android),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clickable { onChecked() }
                    .background(color = Color.White, shape = CircleShape)
                    .padding(10.dp)
            )
        }
    }
}

//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//fun CardsPreview() {
//    NufochildTheme {
//        CardVideoList(modifier = Modifier.clickable { })
//    }
//}