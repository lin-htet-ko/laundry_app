package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import javax.inject.Inject

class CheckoutNavigatorImpl @Inject constructor(): CheckoutNavigator {


    override var navController: NavController? = null

    override fun navigateBack() {
        navController?.popBackStack()
    }

    override fun navigateToCheckoutPayment() {
        navController?.navigate(RootRoute.ROUTE_CHECKOUT_PAYMENT)
    }
}