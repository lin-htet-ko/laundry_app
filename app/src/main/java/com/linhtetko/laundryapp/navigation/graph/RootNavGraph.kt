package com.linhtetko.laundryapp.navigation.graph

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.linhtetko.laundryapp.navigation.routes.AuthRoute
import com.linhtetko.laundryapp.navigation.routes.RootRoute
import com.linhtetko.laundryapp.ui.screens.boarding.BoardingScreen
import com.linhtetko.laundryapp.ui.screens.checkout.CheckoutScreen
import com.linhtetko.laundryapp.ui.screens.checkout_payment.CheckoutPaymentScreen
import com.linhtetko.laundryapp.ui.screens.main.MainScreen
import com.linhtetko.laundryapp.ui.screens.order_detail.OrderDetailScreen
import com.linhtetko.laundryapp.ui.screens.payment_success.PaymentSuccessScreen
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.CheckoutPaymentViewModel
import com.linhtetko.laundryapp.viewmodel.CheckoutViewModel
import com.linhtetko.laundryapp.viewmodel.OrderDetailViewModel

@Composable
fun RootNavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RootRoute.ROUTE_BOARDING) {
        authNavGraph(navHostController = navController)
        composable(RootRoute.ROUTE_BOARDING) {
            BoardingScreen(onGetStarted = {
                navController.popBackStack()
                navController.navigate(AuthRoute.ROUTE_AUTH) {
                    launchSingleTop = true
                }
            })
        }
        composable(RootRoute.ROUTE_MAIN) {
            MainScreen(rootController = navController)
        }
        composable(RootRoute.ROUTE_CHECKOUT) {
            val checkoutViewModel = hiltViewModel<CheckoutViewModel>()
            checkoutViewModel.setUpNavController(navController)

            CheckoutScreen(checkoutViewModel = checkoutViewModel)
        }
        composable(RootRoute.ROUTE_CHECKOUT_PAYMENT) {
            val checkoutPaymentViewModel = hiltViewModel<CheckoutPaymentViewModel>()
            checkoutPaymentViewModel.setUpNavController(navController)

            CheckoutPaymentScreen(checkoutPaymentViewModel = checkoutPaymentViewModel)
        }
        composable(RootRoute.ROUTE_CHECKOUT_SUCCESS) {
            PaymentSuccessScreen(
                onTapBackHome = {
                    navController.popBackStack(
                        RootRoute.ROUTE_MAIN,
                        inclusive = false,
                        saveState = false
                    )
                },
                onTapDetailOrder = {
                    navController.navigate(RootRoute.ROUTE_ORDER_DETAIL)
                }
            )
        }
        composable(RootRoute.ROUTE_ORDER_DETAIL) {
            val orderDetailViewModel = hiltViewModel<OrderDetailViewModel>()
            orderDetailViewModel.setNavController(navController = navController)

            OrderDetailScreen(orderDetailViewModel = orderDetailViewModel)
        }
    }
}

@Preview
@Composable
fun RootNavGraphPreview() {
    LaundryAppTheme {
        RootNavGraph()
    }
}