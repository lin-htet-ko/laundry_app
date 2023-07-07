package com.linhtetko.laundryapp.data.vos

import com.linhtetko.laundryapp.enums.FinishingStatus
import com.linhtetko.laundryapp.enums.LaundryStatus
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class HistoryVO(
    val id: Long,
    val name: String,
    val deadline: Long,
    val laundryStatus: LaundryStatus
){
    fun changeDateFormat(): String{
        val date = Date(deadline)
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(date)
    }

    companion object{
        fun idle() = HistoryVO(
            id = System.currentTimeMillis(),
            name = "Happy Laundry",
            deadline = System.currentTimeMillis(),
            laundryStatus = LaundryStatus.Deliver(
                status = FinishingStatus.Finish,
                FinishingStatus.Finish.name
            )
        )
    }
}
