package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import javax.inject.Inject

class ProfileNavigatorImpl @Inject constructor(): ProfileNavigator {

    override var childNavController: NavController? = null
    override var navController: NavController? = null

    override fun navigateToAccount() {

    }

    override fun navigateToLanguage() {

    }

    override fun navigateToNotification() {

    }

    override fun navigateToPreference() {

    }

    override fun navigateToHelpAndCenter() {

    }
}