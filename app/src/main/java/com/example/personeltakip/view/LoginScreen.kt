package com.example.personeltakip.view
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.personeltakip.components.EmailField
import com.example.personeltakip.components.NameField
import com.example.personeltakip.components.PasswordField
import com.example.personeltakip.components.SmallSpacer
import com.example.personeltakip.navigation.Screens
import com.example.personeltakip.viewmodel.SignInViewModel
import com.example.personeltakip.viewmodel.SignUpViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    navController: NavController,
    viewModel : SignInViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val state = viewModel.singInState.collectAsState(initial = null)

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


        EmailField(
            name = email,
            onNameValueChange = { newValue ->
                email = newValue
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
                scope.launch {
                    viewModel.loginUser(email.toString(),password.toString())
                }

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

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            modifier = Modifier.clickable {navController.navigate(Screens.SignInScreen.route)},
            text = "Giriş yapın",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }

    if (state.value?.isLoading == true) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(key1 = state.value?.isSuccess) {
        scope.launch {
            if (state.value?.isSuccess?.isNotEmpty() == true) {
                val success = state.value?.isSuccess
                Toast.makeText(context,"$success",Toast.LENGTH_LONG).show()
            }
        }
    }

    LaunchedEffect(key1 = state.value?.isError) {
        scope.launch {
            if (state.value?.isError?.isNotBlank() == true) {
                val error = state.value?.isError
                Toast.makeText(context,"$error",Toast.LENGTH_LONG).show()
            }
        }
    }



}
