/*
 * Copyright (rizky201008) 2023.
 * All code in this application is made by the author.
 * You may copy part of this code and you are allowed to include the author's name anywhere.
 */

package com.nufochild.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.nufochild.R
import com.nufochild.data.request.RequestUpdateProfile
import com.nufochild.ui.components.InputFields
import com.nufochild.ui.components.MyButton
import com.nufochild.ui.theme.White
import com.nufochild.ui.theme.Yellow200
import com.nufochild.ui.theme.Yellow700
import com.nufochild.viewmodel.MainViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateProfileScreen(navHostController: NavHostController) {
    val viewModel = getViewModel<MainViewModel>()
    val context = LocalContext.current
    val updateSuccess = viewModel.isUpdateSuccess
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("1") }
    var genderSelected by remember { mutableStateOf("") }
    var genderList = listOf("Male", "Female")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Yellow200),
    ) {
        InputFields(
            value = age,
            onChange = { age = it },
            label = stringResource(id = R.string.label_age),
            placeholder = "0",
            type = KeyboardType.Number,
            isError = false,
            errorText = ""
        )
        InputFields(
            value = weight,
            onChange = { weight = it },
            label = stringResource(id = R.string.label_weight),
            placeholder = "0",
            type = KeyboardType.Number,
            isError = false,
            errorText = ""
        )
        InputFields(
            value = height,
            onChange = { height = it },
            label = stringResource(id = R.string.label_height),
            placeholder = "0",
            type = KeyboardType.Number,
            isError = false,
            errorText = ""
        )
        var expanded by remember { mutableStateOf(false) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            TextField(
                value = genderSelected,
                onValueChange = {},
                readOnly = true,
                label = { Text(text = stringResource(id = R.string.label_gender)) },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .background(color = White, shape = RoundedCornerShape(10.dp))
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                genderList.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(text = item) },
                        onClick = {
                            genderSelected = item
                            expanded = false
                            gender = if (item == "Male") "1" else "2"
                        }
                    )
                }
            }
        }

        MyButton(
            onClick = {
                viewModel.updateDetail(
                    RequestUpdateProfile(
                        null,
                        gender,
                        null,
                        weight.toInt(),
                        age.toInt(),
                        height.toInt()
                    )
                )
            },
            color = Yellow700,
            textColor = White,
            text = stringResource(id = R.string.save)
        )
    }

    if (viewModel.isDialogShown) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDialog() },
            confirmButton = {
                Button(
                    onClick = { viewModel.dismissDialog() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ok")
                }
            },
            title = { Text(text = "Oops") },
            text = { Text(text = stringResource(id = R.string.update_failed)) }
        )
    }
    if (updateSuccess) {
        AlertDialog(
            onDismissRequest = { viewModel.dismissDialog() },
            confirmButton = {
                Button(
                    onClick = { navHostController.navigateUp() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Ok")
                }
            },
            title = { Text(text = "Yeah!") },
            text = { Text(text = stringResource(id = R.string.update_success)) }
        )
    }
}