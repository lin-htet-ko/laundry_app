package com.linhtetko.laundryapp.ui.screens.order_detail

import com.linhtetko.laundryapp.data.vos.OrderVO
import com.linhtetko.laundryapp.enums.FinishingStatus
import com.linhtetko.laundryapp.enums.LaundryStatus

data class OrderDetailState(
    val list: List<LaundryStatus> = emptyList(),
    val orderVO: OrderVO
){
    companion object{
        fun idle() = OrderDetailState(
            list = listOf(
                LaundryStatus.Washing(
                    FinishingStatus.Finish,
                    "Washing"
                ),
                LaundryStatus.Cleaning(
                    FinishingStatus.Finish,
                    "Cleaning"
                ), LaundryStatus.Drying(
                    FinishingStatus.Continue,
                    "Drying"
                ), LaundryStatus.Deliver(
                    FinishingStatus.NotStart,
                    "Deliver"
                )
            ),
            orderVO = OrderVO.idle()
        )
    }
}
