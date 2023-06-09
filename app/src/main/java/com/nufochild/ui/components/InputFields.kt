/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */
package com.nufochild.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nufochild.R
import com.nufochild.ui.theme.Yellow900

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun InputFields(
    label: String = "Example label",
    placeholder: String = "Example placeholder",
    value: String,
    onChange: (String) -> Unit,
    type: KeyboardType = KeyboardType.Text,
    isError: Boolean,
    errorText: String = ""
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val passwordHide = if (type == KeyboardType.Password) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    TextField(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .background(
                color = colorResource(id = R.color.white),
                shape = RoundedCornerShape(10.dp),
            )
            .border(
                width = 3.dp,
                shape = RoundedCornerShape(10.dp),
                color = Yellow900
            ),
        value = value,
        onValueChange = onChange,
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        textStyle = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold),
        label = { Text(text = label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = type),
        visualTransformation = passwordHide,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        isError = isError,
        supportingText = {
            if (isError) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(3.dp),
                    text = errorText,
                    color = MaterialTheme.colorScheme.error
                )
            }
        },
        trailingIcon = {
            if (isError)
                Icon(Icons.Filled.Error, "error", tint = MaterialTheme.colorScheme.error)
        },
    )
}

@Preview
@Composable
fun TextFieldPreview() {
    val value by remember { mutableStateOf("") }
    InputFields(
        value = value,
        onChange = { value },
        isError = true,
        errorText = "Tempekmu mambu tempik cok cok cokcok cok"
    )
}