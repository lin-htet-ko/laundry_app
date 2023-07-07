package com.linhtetko.laundryapp.ui.screens.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.linhtetko.laundryapp.navigation.routes.MainRoute
import com.linhtetko.laundryapp.ui.screens.main.chat.ChatScreen
import com.linhtetko.laundryapp.ui.screens.main.history.HistoryScreen
import com.linhtetko.laundryapp.ui.screens.main.home.HomeScreen
import com.linhtetko.laundryapp.ui.screens.main.profile.ProfileScreen
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.ChatViewModel
import com.linhtetko.laundryapp.viewmodel.HistoryViewModel
import com.linhtetko.laundryapp.viewmodel.HomeViewModel
import com.linhtetko.laundryapp.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(modifier: Modifier = Modifier, rootController: NavController) {

    val mainNavController = rememberNavController()
    val currentRoute = mainNavController.currentBackStackEntryAsState().value?.destination?.route

    val bottomItems = remember {
        mutableListOf(
            MainRoute.HomeRoute("Home"),
            MainRoute.ChatRoute("Chat"),
            MainRoute.HistoryRoute("History"),
            MainRoute.ProfileRoute("Profile"),
        )
    }

    Scaffold(
        bottomBar = {
            MainBottomNavigation(
                routes = bottomItems,
                currentRoute = { currentRoute },
                onTapItem = { item ->
                    mainNavController.navigate(item.route) {
                        popUpTo(mainNavController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    ) {
        val mainModifier = modifier.padding(it)
        NavHost(navController = mainNavController, startDestination = MainRoute.ROUTE_HOME) {
            composable(MainRoute.ROUTE_HOME) {
                val homeViewModel = hiltViewModel<HomeViewModel>()
                homeViewModel.setUpNavController(rootController, mainNavController)

                HomeScreen( homeViewModel = homeViewModel)
            }

            composable(MainRoute.ROUTE_CHAT) {
                val chatViewModel = hiltViewModel<ChatViewModel>()
                chatViewModel.setUpNavController(rootController, mainNavController)

                ChatScreen(modifier = mainModifier, chatViewModel = chatViewModel)
            }

            composable(MainRoute.ROUTE_HISTORY) {
                val historyViewModel = hiltViewModel<HistoryViewModel>()
                historyViewModel.setUpNavController(rootController, mainNavController)

                HistoryScreen(modifier = mainModifier, historyViewModel = historyViewModel)
            }

            composable(MainRoute.ROUTE_PROFILE) {
                val profileViewModel = hiltViewModel<ProfileViewModel>()
                profileViewModel.setUpNavController(rootController, mainNavController)

                ProfileScreen(modifier = mainModifier, profileViewModel = profileViewModel)
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    LaundryAppTheme {
        MainScreen(rootController = rememberNavController())
    }
}

@Composable
fun MainBottomNavigation(
    routes: List<MainRoute>,
    currentRoute: () -> String?,
    onTapItem: (MainRoute) -> Unit,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary

    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface
    ) {
        routes.forEach { item ->
            NavigationBarItem(
                selected = item.route == currentRoute(),
                onClick = { onTapItem(item) },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = item.label,
                    )
                },
                label = {
                    Text(text = item.label)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = primaryColor,
                    selectedTextColor = primaryColor,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(1.dp)
                )
            )
        }
    }
}

@Preview
@Composable
fun MainBottomNavigationPreview() {
    val bottomItems = remember {
        mutableListOf(
            MainRoute.HomeRoute("Home"),
            MainRoute.ChatRoute("Chat"),
            MainRoute.HistoryRoute("History"),
            MainRoute.ProfileRoute("Profile"),
        )
    }
    LaundryAppTheme() {
        MainBottomNavigation(
            routes = bottomItems,
            currentRoute = { MainRoute.HomeRoute("Home").route },
            onTapItem = {})
    }
}