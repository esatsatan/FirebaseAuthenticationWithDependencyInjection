package com.example.personeltakip.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.personeltakip.components.EmailField
import com.example.personeltakip.components.NameField
import com.example.personeltakip.components.PasswordField
import com.example.personeltakip.components.SmallSpacer

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUp() {

    var email by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = ""
                )
            )
        }
    )

    var name by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = ""
                )
            )
        }
    )

    var password by rememberSaveable(
        stateSaver = TextFieldValue.Saver,
        init = {
            mutableStateOf(
                value = TextFieldValue(
                    text = ""
                )
            )
        }
    )

    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        NameField(
            name = name,
            onNameValueChange = { newValue ->
                name = newValue
            }
        )
        SmallSpacer()

        EmailField(
            name = email,
            onNameValueChange = { newValue ->
                name = newValue
            }
        )
        SmallSpacer()

        PasswordField(
            password = password,
            onPasswordValueChange = { newValue ->
                password = newValue
            }
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green
            ),
            onClick = {
                keyboard?.hide()
                // buraya kayıt olma için gereken fonksyonu yerleştir
            }
        ) {
            Text(
                text = "Kayıt ol",
                fontSize = 15.sp
            )
        }
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Zaten hesabınız var mı ?",
            fontSize = 20.sp
        )

        Text(
            text = "Giriş yapın ?",
            fontSize = 20.sp
        )

    }



}


@Preview
@Composable
fun deneme () {
    SignUp()
}