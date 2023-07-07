package com.linhtetko.laundryapp.ui.screens.checkout_payment

import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.PaymentVO

data class CheckoutPaymentState(
    val address: String,
    val isDeliService: Boolean,
    val isSelfService: Boolean,
    val price: Double,
    val paymentMethods: List<PaymentVO> = emptyList()
){
    companion object{
        fun idle() = CheckoutPaymentState(
            address = "LaMin Condo, Hlaing, Burma",
            isDeliService = true,
            isSelfService = false,
            price = 2.0,
            paymentMethods = listOf(
                PaymentVO(id = 1, name = "Apple Pay", icon = R.drawable.ic_apple),
                PaymentVO(id = 2, name = "Credit Card", icon = R.drawable.ic_credit_card),
                PaymentVO(id = 3, name = "Paypal", icon = R.drawable.ic_paypal)
            )
        )
    }
}