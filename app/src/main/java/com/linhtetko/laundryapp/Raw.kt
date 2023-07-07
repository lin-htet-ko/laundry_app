package com.linhtetko.laundryapp

import com.linhtetko.laundryapp.data.vos.MessageVO

fun main() {
    val message = MessageVO(
        id = System.currentTimeMillis(),
        sentTime = System.currentTimeMillis(),
        senderImage = "",
        senderName = "",
        message = ""
    )
    println(message.changeDateFormat())
}