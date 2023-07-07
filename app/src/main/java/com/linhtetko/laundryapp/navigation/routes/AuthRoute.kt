package com.linhtetko.laundryapp.navigation.routes

sealed class AuthRoute(val route: String){

    companion object{
        const val ROUTE_AUTH = "/auth"
        const val ROUTE_LOGIN = "/login"
        const val ROUTE_REGISTER = "/register"
    }

    object RegisterScreen: AuthRoute(ROUTE_REGISTER)
    object LoginScreen: AuthRoute(ROUTE_LOGIN)
}