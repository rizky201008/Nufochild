package com.nufochild.ui.components


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nufochild.R

@Composable
fun MyButton(
    text: String = "Text",
    onClick: () -> Unit,
    color: Color = MaterialTheme.colorScheme.primary,
    textColor: Color = Color.White,
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = textColor)
    ) {
        Text(
            text = text,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            softWrap = true,
            maxLines = 1
        )
    }
}

@Composable
fun MyBorderedButton(
    text: String = "Text",
    onClick: () -> Unit,
    color: Color = colorResource(id = R.color.yellow_700),
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Text(
            text = text,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}