package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.PaymentVO
import com.linhtetko.laundryapp.navigation.navigator.CheckoutPaymentNavigator
import com.linhtetko.laundryapp.ui.screens.checkout_payment.CheckoutPaymentState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CheckoutPaymentViewModel @Inject constructor(private val paymentNavigator: CheckoutPaymentNavigator) :
    ViewModel() {

    private val _checkoutPaymentState = mutableStateOf(CheckoutPaymentState.idle())
    private val _cacheState = { _checkoutPaymentState.value }
    val checkoutPaymentState = _checkoutPaymentState

    fun setUpNavController(navController: NavController) {
        paymentNavigator.navController = navController
    }


    fun onTapBack() {
        paymentNavigator.navigateBack()
    }

    fun onTapMore() {
    }

    fun onTapSelfService() {
        val state = _cacheState()
        _checkoutPaymentState.value =
            state.copy(isDeliService = false, isSelfService = !state.isSelfService)
    }

    fun onTapDeliService() {
        val state = _cacheState()
        _checkoutPaymentState.value = state.copy(
            isSelfService = false, isDeliService = !state.isDeliService
        )
    }

    fun onTapPaymentItem(paymentVO: PaymentVO) {
        val state = _cacheState.invoke()
        val payments = state.paymentMethods.toMutableList()
        val temp = payments.toList()
        payments.clear()
        payments.addAll(temp.map { tempItem ->
            tempItem.copy(isSelected = tempItem.id == paymentVO.id)
        })
        _checkoutPaymentState.value = state.copy(paymentMethods = payments)
    }

    fun onTapEditAddress() {
    }

    fun onAddNewPayment() {

    }

    fun onPay() {
        paymentNavigator.navigateToPaymentSuccess()
    }
}