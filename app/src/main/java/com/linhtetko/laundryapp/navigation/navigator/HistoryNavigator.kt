package com.linhtetko.laundryapp.navigation.navigator

import com.linhtetko.laundryapp.data.vos.HistoryVO

interface HistoryNavigator: MainBaseNavigator {
    fun navigateToDetail(history: HistoryVO)
}