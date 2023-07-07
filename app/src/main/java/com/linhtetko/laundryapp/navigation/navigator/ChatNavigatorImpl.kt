package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController
import javax.inject.Inject

class ChatNavigatorImpl @Inject constructor() : ChatNavigator {
    override var childNavController: NavController? = null
    override var navController: NavController? = null
}