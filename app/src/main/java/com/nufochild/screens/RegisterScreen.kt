package com.nufochild.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.nufochild.R
import com.nufochild.ui.components.InputFields
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.theme.NufochildTheme

@Composable
fun RegisterScreen() {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(colorResource(id = R.color.yellow_200)),
    ) {
        Image(
            painter = painterResource(id = R.drawable.dounts),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .blur(3.dp)
        )
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputFields(
                value = name,
                onChange = { name = it },
                label = stringResource(id = R.string.label_name),
                placeholder = stringResource(id = R.string.hint_name),
                type = KeyboardType.Text
            )
            InputFields(
                value = email,
                onChange = { email = it },
                label = stringResource(id = R.string.label_email),
                placeholder = stringResource(id = R.string.hint_email),
                type = KeyboardType.Email
            )
            InputFields(
                value = password,
                onChange = { password = it },
                label = stringResource(id = R.string.label_password),
                placeholder = stringResource(id = R.string.hint_password),
                type = KeyboardType.Password
            )
            InputFields(
                value = confirmPassword,
                onChange = { confirmPassword = it },
                label = stringResource(id = R.string.label_cpassword),
                placeholder = stringResource(id = R.string.hint_password),
                type = KeyboardType.Password
            )

            Spacer(modifier = Modifier.height(100.dp))

            MyButton(
                onClick = { /*TODO*/ },
                text = stringResource(id = R.string.register),
                color = colorResource(
                    id = R.color.yellow_700
                ),
                textColor = Color.White
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = stringResource(id = R.string.already_registered))
                Text(text = stringResource(id = R.string.login),
                    color = colorResource(id = R.color.yellow_900),
                    modifier = Modifier.clickable { }
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun RegisterPrev() {
    NufochildTheme {
        RegisterScreen()
    }
}