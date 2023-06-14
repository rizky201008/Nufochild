package com.nufochild.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import com.nufochild.Destination
import com.nufochild.R
import com.nufochild.data.request.RequestLogin
import com.nufochild.ui.components.InputFields
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.theme.Yellow200
import com.nufochild.ui.theme.Yellow700
import com.nufochild.ui.theme.Yellow900
import com.nufochild.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(navController: NavHostController) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val viewModel = getViewModel<MainViewModel>()
    val showLoading = viewModel.showLoading.collectAsState()
    val emailError = viewModel.emailError.collectAsState()
    val passwordError = viewModel.passwordError.collectAsState()
    val isLoading = showLoading.value
    val isEmailError = emailError.value
    val isPasswordError = passwordError.value
    val loginSuccess = viewModel.loginSuccess.collectAsState()
    val isLoginSuccess = loginSuccess.value
    val emailErrMsg = viewModel.emailErrMsg.collectAsState()
    val passErrMsg = viewModel.passErrMsg.collectAsState()
    val emailErrTxt = emailErrMsg.value
    val passErrTxt = passErrMsg.value

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Yellow200),
    ) {
        Image(
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .blur(3.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .zIndex(1f)
                .padding(10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InputFields(
                value = email,
                onChange = { email = it },
                label = stringResource(id = R.string.label_email),
                placeholder = stringResource(id = R.string.hint_email),
                type = KeyboardType.Email,
                isError = isEmailError,
                errorText = emailErrTxt
            )
            InputFields(
                value = password,
                onChange = { password = it },
                label = stringResource(id = R.string.label_password),
                placeholder = stringResource(id = R.string.hint_password),
                type = KeyboardType.Password,
                isError = isPasswordError,
                errorText = passErrTxt
            )

            Spacer(modifier = Modifier.height(100.dp))

            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(10.dp),
                    color = Yellow700,
                    strokeWidth = 5.dp
                )
            } else {
                MyButton(
                    onClick = {
                        viewModel.login(RequestLogin(password, email))
                        if (isLoginSuccess) {
                            keyboardController?.hide()
                            navController.popBackStack()
                            navController.navigate(Destination.Home.route)
                        }
                    },
                    text = stringResource(id = R.string.login),
                    color = Yellow700,
                    textColor = Color.White
                )
            }

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
                Text(text = stringResource(id = R.string.not_registered))
                Text(
                    text = stringResource(id = R.string.register),
                    color = Yellow900,
                    modifier = Modifier.clickable {
                        navController.navigate(Destination.Register.route)
                    }
                )
            }
        }
    }
}