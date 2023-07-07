package com.linhtetko.laundryapp.ui.screens.checkout

import com.linhtetko.laundryapp.data.vos.LaundryVO
import com.linhtetko.laundryapp.data.vos.OrderVO

data class CheckoutState(val laundry: LaundryVO, val orders: List<OrderVO> = emptyList()) {

    companion object {
        fun idle() = CheckoutState(
            laundry = LaundryVO.idle(),
            orders = listOf(
                OrderVO.idle(),
                OrderVO.idle(),
                OrderVO.idle(),
                OrderVO.idle(),
                OrderVO.idle(),
            )
        )
    }
}