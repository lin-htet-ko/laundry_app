package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.routes.AuthRoute
import com.linhtetko.laundryapp.navigation.routes.MainRoute
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import javax.inject.Inject

class LoginNavigatorImpl @Inject constructor(): LoginNavigator {

    override var navController: NavController? = null

    override fun navigateToHome() {
        navController?.popBackStack()
        navController?.navigate(RootRoute.ROUTE_MAIN){
            launchSingleTop = true
        }
    }

    override fun navigateToRegister() {
        navController?.navigate(AuthRoute.ROUTE_REGISTER)
    }

}