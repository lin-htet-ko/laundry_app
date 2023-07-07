package com.linhtetko.laundryapp.ui.screens.main.history

import com.linhtetko.laundryapp.data.vos.HistoryVO

data class HistoryState(
    val histories: List<HistoryVO>
){
    companion object{
        fun idle() = HistoryState(
            listOf(
                HistoryVO.idle(),
                HistoryVO.idle(),
                HistoryVO.idle(),
                HistoryVO.idle(),
            )
        )
    }
}
