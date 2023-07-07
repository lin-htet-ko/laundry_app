package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.HistoryVO
import javax.inject.Inject

class HistoryNavigatorImpl @Inject constructor(): HistoryNavigator {


    override var childNavController: NavController? = null
    override var navController: NavController? = null


    override fun navigateToDetail(history: HistoryVO) {

    }
}