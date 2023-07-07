package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.data.vos.LaundryVO
import com.linhtetko.laundryapp.navigation.navigator.HomeNavigator
import com.linhtetko.laundryapp.ui.screens.main.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeNavigator: HomeNavigator
): ViewModel() {

    private var _homeState = mutableStateOf(HomeState.idle())
    private val _cacheState = { _homeState.value }
    val homeState: State<HomeState> = _homeState

    fun setUpNavController(rootNavController: NavController, childNavController: NavController){
        homeNavigator.navController = rootNavController
        homeNavigator.childNavController = childNavController
    }

    fun onSearchKeywordChange(keyword: String) {
        _homeState.value = _cacheState().copy(searchKeyword = keyword)
    }

    fun onTapNotification() {

    }

    fun onTapViewDetail() {

    }

    fun onTapNearestLaundrySeeMore() {

    }

    fun onTapLaundryItem(item: LaundryVO) {
        homeNavigator.navigateToLaundryItem(item)
    }
}