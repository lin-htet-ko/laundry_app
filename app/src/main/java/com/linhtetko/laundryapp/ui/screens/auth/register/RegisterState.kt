package com.linhtetko.laundryapp.ui.screens.auth.register

data class RegisterState(
    val name: String,
    val email: String,
    val password: String
){
    companion object{
        fun idle(): RegisterState{
            return RegisterState(
                name = "",
                email = "",
                password = "",
            )
        }
    }
}
