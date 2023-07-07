package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.navigator.OrderDetailNavigator
import com.linhtetko.laundryapp.ui.screens.order_detail.OrderDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class OrderDetailViewModel @Inject constructor(
    private val orderDetailNavigator: OrderDetailNavigator
): ViewModel() {

    private val _orderDetailStatus = mutableStateOf(OrderDetailState.idle())
    private val _cacheState = { _orderDetailStatus.value }
    val orderDetailState: State<OrderDetailState> = _orderDetailStatus

    fun setNavController(navController: NavController){
        orderDetailNavigator.navController = navController
    }

    fun onTapBack() {
        orderDetailNavigator.navigateBack()
    }

    fun onTapMore() {
    }
}