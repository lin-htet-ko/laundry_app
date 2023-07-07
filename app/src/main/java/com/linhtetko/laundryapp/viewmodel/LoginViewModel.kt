package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.navigator.LoginNavigator
import com.linhtetko.laundryapp.ui.screens.auth.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigator: LoginNavigator
): ViewModel() {

    private var _loginState = mutableStateOf(LoginState.idle())
    private val _cacheState  = _loginState
    val loginState: State<LoginState> = _loginState

    fun setNavController(navHostController: NavController) {
        navigator.navController = navHostController
    }

    fun onEmailChange(value: String){
        _loginState.value = _cacheState.value.copy(email = value)
    }
    fun onPasswordChange(value: String){
        _loginState.value = _cacheState.value.copy(password = value)
    }

    fun onTapLogin(){
        navigator.navigateToHome()
    }
    fun onTapLoginWithAnotherAcc(){
        navigator.navigateToHome()
    }
    fun onTapForgotPassword(){
        navigator.navigateToHome()
    }
    fun onTapRegister(){
        navigator.navigateToRegister()
    }
    fun onTapGoogleAuth(){
        navigator.navigateToHome()
    }
    fun onTapFacebookAuth(){
        navigator.navigateToHome()
    }

}