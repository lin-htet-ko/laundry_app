package com.linhtetko.laundryapp.ui.screens.auth.login

data class LoginState(
    val email: String,
    val password: String
) {
    companion object {
        fun idle(): LoginState {
            return LoginState(
                email = "",
                password = ""
            )
        }
    }
}
