package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.linhtetko.laundryapp.main
import com.linhtetko.laundryapp.navigation.navigator.ProfileNavigator
import com.linhtetko.laundryapp.ui.screens.main.profile.ProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileNavigator: ProfileNavigator
): ViewModel() {


    private val _profileState = mutableStateOf(ProfileState.idle())
    private val _cacheState = { _profileState.value }
    val profileState : State<ProfileState> = _profileState

    fun setUpNavController(rootController: NavController, mainNavController: NavHostController) {
        profileNavigator.navController = rootController
        profileNavigator.childNavController = mainNavController
    }


    fun onTapAccount() {
        profileNavigator.navigateToAccount()
    }

    fun onTapLanguage() {
        profileNavigator.navigateToLanguage()
    }

    fun onTapNotification() {
        profileNavigator.navigateToNotification()
    }

    fun onTapPreference() {
        profileNavigator.navigateToPreference()
    }

    fun onTapHelp() {
        profileNavigator.navigateToHelpAndCenter()
    }
}