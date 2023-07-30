package com.example.personeltakip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personeltakip.repository.AuthRepository
import com.example.personeltakip.utils.Resource
import com.example.personeltakip.utils.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signUpState = Channel<SignInState>()
    val singUpState = _signUpState.receiveAsFlow()

    fun registerUser(email : String, password : String) = viewModelScope.launch {
        repository.registerUser(email, password).collect{ result ->

            when(result) {

                is Resource.Success -> {
                    _signUpState.send(SignInState(isSuccess = "Kayıt Başarılı"))
                }

                is Resource.Loading -> {
                    _signUpState.send(SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _signUpState.send(SignInState(isError = result.message))
                }

            }
        }
    }



}