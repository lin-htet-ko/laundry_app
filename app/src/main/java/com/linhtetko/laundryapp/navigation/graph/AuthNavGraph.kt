package com.linhtetko.laundryapp.navigation.graph

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.linhtetko.laundryapp.navigation.routes.AuthRoute
import com.linhtetko.laundryapp.ui.screens.auth.login.LoginScreen
import com.linhtetko.laundryapp.ui.screens.auth.register.RegisterScreen
import com.linhtetko.laundryapp.viewmodel.LoginViewModel
import com.linhtetko.laundryapp.viewmodel.RegisterViewModel

fun NavGraphBuilder.authNavGraph(navHostController: NavHostController) {
    navigation(startDestination = AuthRoute.ROUTE_LOGIN, route = AuthRoute.ROUTE_AUTH) {

        composable(AuthRoute.ROUTE_REGISTER) {
            val registerViewModel = hiltViewModel<RegisterViewModel>()
            registerViewModel.setNavController(navController = navHostController)

            RegisterScreen(registerViewModel = registerViewModel)
        }
        composable(AuthRoute.ROUTE_LOGIN) {
            val loginViewModel = hiltViewModel<LoginViewModel>()
            loginViewModel.setNavController(navHostController = navHostController)

            LoginScreen(loginViewModel = loginViewModel)
        }
    }
}