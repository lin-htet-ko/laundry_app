package com.linhtetko.laundryapp.ui.screens.main.home

import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.LaundryVO

data class HomeState(
    val townName: String,
    val searchKeyword: String,
    val orderLaundryName: String,
    val orderLaundryDesc: String,
    val laundries: List<LaundryVO>
) {
    companion object {
        fun idle() = HomeState(
            townName = "Hlaing, Burma",
            searchKeyword = "",
            orderLaundryName = "Blah Blah Laundry",
            orderLaundryDesc = "Your clothes \nwill finish in 1 Days",
            laundries = listOf(
                LaundryVO.idle(),
                LaundryVO.idle(),
                LaundryVO.idle(),
                LaundryVO.idle(),
            )
        )
    }
}
