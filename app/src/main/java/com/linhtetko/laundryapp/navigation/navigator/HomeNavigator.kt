package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.LaundryVO

interface HomeNavigator: MainBaseNavigator {
    fun navigateToCheckout()
    fun navigateToOrderDetail()
    fun navigateToLaundryItem(item: LaundryVO)
}