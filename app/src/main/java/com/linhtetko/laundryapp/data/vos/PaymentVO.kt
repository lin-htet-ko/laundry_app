package com.linhtetko.laundryapp.data.vos

import androidx.annotation.DrawableRes

data class PaymentVO(
    val id: Long,
    val name: String,
    @DrawableRes val icon: Int,
    var isSelected: Boolean = false
)
