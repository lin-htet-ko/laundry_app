package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import javax.inject.Inject

class CheckoutPaymentNavigatorImpl @Inject constructor(): CheckoutPaymentNavigator {

    override var navController: NavController? = null

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToPaymentSuccess() {
        navController?.navigate(RootRoute.ROUTE_CHECKOUT_SUCCESS)
    }
}