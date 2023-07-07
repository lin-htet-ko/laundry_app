package com.linhtetko.laundryapp.navigation.routes

import androidx.annotation.DrawableRes
import com.linhtetko.laundryapp.R

sealed class MainRoute(val route: String, val label: String, @DrawableRes val icon: Int) {
    companion object {
        const val ROUTE_HOME = "/home"
        const val ROUTE_CHAT = "/chat"
        const val ROUTE_HISTORY = "/history"
        const val ROUTE_PROFILE = "/profile"
    }

    data class HomeRoute(private val name: String) :
        MainRoute(route = ROUTE_HOME, label = name, icon = R.drawable.ic_round_home)

    data class ChatRoute(private val name: String) :
        MainRoute(route = ROUTE_CHAT, label = name, icon = R.drawable.ic_round_chat)

    data class HistoryRoute(private val name: String) :
        MainRoute(route = ROUTE_HISTORY, label = name, icon = R.drawable.ic_round_notes)

    data class ProfileRoute(private val name: String) :
        MainRoute(route = ROUTE_PROFILE, label = name, icon = R.drawable.ic_outline_person)
}
