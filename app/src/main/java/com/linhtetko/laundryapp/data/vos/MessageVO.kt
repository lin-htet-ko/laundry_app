package com.linhtetko.laundryapp.data.vos

import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import com.linhtetko.laundryapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class MessageVO(
    val id: Long,
    val senderName: String,
    val senderImage: Any,
    val message: String,
    val sentTime: Long,
){

    companion object{
        fun idle() = MessageVO(
            id = System.currentTimeMillis(),
            sentTime = System.currentTimeMillis(),
            senderImage = R.drawable.placeholder_person,
            senderName = "Mr. Lin Htet Ko",
            message = LoremIpsum(10).values.joinToString(" ")
        )
    }
    fun changeDateFormat(): String{
        val date = Date(sentTime)
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(date)
    }
}
