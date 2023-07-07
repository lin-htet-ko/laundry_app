package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.HistoryVO
import com.linhtetko.laundryapp.navigation.navigator.HistoryNavigator
import com.linhtetko.laundryapp.ui.screens.main.history.HistoryState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyNavigator: HistoryNavigator
): ViewModel() {

    private val _historyState = mutableStateOf(HistoryState.idle())
    private val _cacheState = { _historyState.value }
    var historyState : State<HistoryState> = _historyState


    fun setUpNavController(rootController: NavController, childNavController: NavController) {
        historyNavigator.navController = rootController
        historyNavigator.childNavController = childNavController
    }

    fun onTapHistoryItem(history: HistoryVO) {
        historyNavigator.navigateToDetail(history)
    }
}