package com.linhtetko.laundryapp.navigation.navigator

import androidx.navigation.NavController

interface MainBaseNavigator: BaseNavigator{

    var childNavController: NavController?

}