package com.nufochild.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nufochild.R
import com.vanpra.composematerialdialogs.MaterialDialog
import com.vanpra.composematerialdialogs.datetime.date.datepicker
import com.vanpra.composematerialdialogs.rememberMaterialDialogState
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputFields(
    label: String = "Example label",
    placeholder: String = "Example placeholder",
    value: String,
    onChange: (String) -> Unit,
    type: KeyboardType = KeyboardType.Text,
) {
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
                color = colorResource(id = R.color.yellow_900)
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
        visualTransformation = passwordHide
    )
}

@Composable
fun DateInput() {
    var pickedDate by remember {
        mutableStateOf(LocalDate.now())
    }
    val dateDialogState = rememberMaterialDialogState()
    val ctx = LocalContext.current

    val formattedDate by remember {
        derivedStateOf {
            DateTimeFormatter.ofPattern("dd-MM-yyyy").format(pickedDate)
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Text(
            text = formattedDate,
            style = TextStyle(color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(20.dp)
                )
                .border(
                    width = 3.dp,
                    color = colorResource(id = R.color.yellow_900),
                    shape = RoundedCornerShape(20.dp)
                )
                .shadow(elevation = 3.dp, shape = RoundedCornerShape(20.dp))
                .padding(10.dp)
        )

        Button(
            onClick = { dateDialogState.show() }, colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(
                    id = R.color.yellow_900
                ),
                contentColor = colorResource(id = R.color.white)
            )
        ) {
            Icon(
                modifier = Modifier
                    .size(30.dp)
                    .padding(5.dp),
                painter = painterResource(id = R.drawable.ic_calendar_month),
                contentDescription = null
            )
            Text(text = stringResource(id = R.string.dob))
        }

        MaterialDialog(dialogState = dateDialogState, buttons = {
            positiveButton(text = "Ok") {
                Toast.makeText(ctx, "Oke", Toast.LENGTH_SHORT).show()
            }
            negativeButton(text = "Cancel")
        }) {
            datepicker(
                initialDate = LocalDate.now(),
                title = "Pick a date",
            ) {
                pickedDate = it
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InputPrev() {
    DateInput()
//    ExampleDate()
}