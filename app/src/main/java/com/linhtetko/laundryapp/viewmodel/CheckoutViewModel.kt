package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.OrderVO
import com.linhtetko.laundryapp.navigation.navigator.CheckoutNavigator
import com.linhtetko.laundryapp.ui.screens.checkout.CheckoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutViewModel @Inject constructor(
    private val checkoutNavigator: CheckoutNavigator
) : ViewModel() {

    private val _checkoutState = mutableStateOf(CheckoutState.idle())
    private val _cacheState = { _checkoutState.value }
    val checkoutState: State<CheckoutState> = _checkoutState

    fun setUpNavController(navController: NavController) {
        checkoutNavigator.navController = navController
    }

    fun onTapBack() {
        checkoutNavigator.navigateBack()
    }

    fun onTapMore() {

    }

    fun onTapCheckout() {
        checkoutNavigator.navigateToCheckoutPayment()
    }

    fun onTapAddCategory() {

    }

    fun onAddOrderItem(orderVO: OrderVO) {
        val temp = _cacheState().orders
        val tempList = temp.map {
            if (it.id == orderVO.id)
                it.copy(count = orderVO.count + 1)
            else
                it
        }
        _checkoutState.value = _cacheState().copy(orders = tempList)
    }

    fun onRemoveOrderItem(orderVO: OrderVO) {
        val temp = _cacheState().orders
        val tempList = temp.map {
            if (it.id == orderVO.id)
                it.copy(count = if (orderVO.count <= 0) 0 else orderVO.count - 1)
            else
                it
        }
        _checkoutState.value = _cacheState().copy(orders = tempList)
    }
}