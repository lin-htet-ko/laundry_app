package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import javax.inject.Inject

class OrderDetailNavigatorImpl @Inject constructor() : OrderDetailNavigator {

    override var navController: NavController? = null

    override fun navigateBack() {
        navController?.popBackStack(route = RootRoute.ROUTE_MAIN, inclusive = false, saveState = false)
    }
}