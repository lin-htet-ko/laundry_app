package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.navigator.RegisterNavigator
import com.linhtetko.laundryapp.ui.screens.auth.register.RegisterState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerNavigator: RegisterNavigator
) : ViewModel() {

    private val _registerState = mutableStateOf(RegisterState.idle())

    private val _cacheState = _registerState

    val registerState: State<RegisterState> = _registerState

    fun setNavController(navController: NavController){
        registerNavigator.navController = navController
    }

    fun onNameChange(name: String) {
        _registerState.value = _cacheState.value.copy(name = name)
    }

    fun onEmailChange(email: String) {
        _registerState.value = _cacheState.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _registerState.value = _cacheState.value.copy(password = password)
    }

    fun onTapLogin() {
        registerNavigator.navigateToLogin()
    }

    fun onTapRegister() {
        registerNavigator.navigateToHome()
    }
}