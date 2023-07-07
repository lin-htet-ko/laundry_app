package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.navigator.RegisterNavigator
import com.linhtetko.laundryapp.navigation.routes.AuthRoute
import com.linhtetko.laundryapp.navigation.routes.MainRoute
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import javax.inject.Inject

class RegisterNavigatorImpl @Inject constructor(): RegisterNavigator {

    override var navController: NavController? = null

    override fun navigateToLogin() {
        navController?.popBackStack(AuthRoute.ROUTE_LOGIN, inclusive = false)
    }

    override fun navigateToHome() {
        navController?.navigate(RootRoute.ROUTE_MAIN)
    }

}