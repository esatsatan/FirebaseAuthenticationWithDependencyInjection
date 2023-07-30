package com.example.personeltakip.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.personeltakip.repository.AuthRepository
import com.example.personeltakip.utils.Resource
import com.example.personeltakip.utils.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    val _signInState = Channel<SignInState>()
    val singInState = _signInState.receiveAsFlow()

    fun loginUser(email : String, password : String) = viewModelScope.launch {
        repository.loginUser(email, password).collect{ result ->

            when(result) {

                is Resource.Success -> {
                    _signInState.send(SignInState(isSuccess = "Giriş Başarılı"))
                }

                is Resource.Loading -> {
                    _signInState.send(SignInState(isLoading = true))
                }

                is Resource.Error -> {
                    _signInState.send(SignInState(isError = result.message))
                }

            }
        }
    }



}