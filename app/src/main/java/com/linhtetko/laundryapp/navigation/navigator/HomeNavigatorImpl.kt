package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.LaundryVO
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import javax.inject.Inject

class HomeNavigatorImpl @Inject constructor(): HomeNavigator {

    override var navController: NavController? = null
    override var childNavController: NavController? = null

    override fun navigateToCheckout() {
    }

    override fun navigateToOrderDetail() {
        TODO("Not yet implemented")
    }

    override fun navigateToLaundryItem(item: LaundryVO) {
        navController?.navigate(RootRoute.ROUTE_CHECKOUT)
    }

}