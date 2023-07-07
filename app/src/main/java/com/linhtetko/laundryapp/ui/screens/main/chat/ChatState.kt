package com.linhtetko.laundryapp.ui.screens.main.chat

import com.linhtetko.laundryapp.data.vos.MessageVO

data class ChatState(
    val keyword: String,
    val pinMessages: List<MessageVO>,
    val allMessages: List<MessageVO>
){
    companion object{
        fun idle() = ChatState(
            keyword = "",
            pinMessages = listOf(MessageVO.idle(),MessageVO.idle(),),
            allMessages = listOf(MessageVO.idle(),MessageVO.idle(),MessageVO.idle(),MessageVO.idle(),)
        )
    }
}
