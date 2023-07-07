package com.linhtetko.laundryapp.data.vos

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.linhtetko.laundryapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class OrderVO(
    val id: Long,
    val img: Any,
    val name: String,
    val price: Double,
    val deadLine: Long,
    val address: String,
    val count: Int = 0
){
    companion object{
        fun idle() = OrderVO(
            id = System.currentTimeMillis(),
            img = R.drawable.placeholder_laundry_machine,
            name = "T-Shirt",
            deadLine = System.currentTimeMillis(),
            address = "LaMin Condo, Hlaing, Burma",
            price = 0.5,
        )
    }

    fun changeDateFormat(): String{
        val date = Date(deadLine)
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(date)
    }
}
