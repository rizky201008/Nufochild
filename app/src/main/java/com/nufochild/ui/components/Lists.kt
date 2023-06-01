/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */
package com.nufochild.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nufochild.R
import com.nufochild.data.FoodsItem
import com.nufochild.ui.theme.Yellow200

@Composable
fun CardList(
    text: String = "Makanan Ringan",
    checkIcon: Painter = painterResource(id = R.drawable.ic_done),
    onClick: () -> Unit,
    onChecked: () -> Unit,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Yellow200, contentColor = Color.Black
        ), modifier = Modifier
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

            Image(
                painter = checkIcon,
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

//@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardsPreview() {
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
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        items(list) {
            CardList(onClick = { /*TODO*/ }, onChecked = {}, text = "Nasi")
        }
    }
}

fun showToast(msg: String, context: Context) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}