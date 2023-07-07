package com.linhtetko.laundryapp.navigation.routes

sealed class RootRoute{
    companion object{
        const val ROUTE_MAIN = "/main"

        const val ROUTE_BOARDING = "/boarding"
        const val ROUTE_CHECKOUT = "/checkout"
        const val ROUTE_CHECKOUT_PAYMENT = "/checkout_payments"
        const val ROUTE_CHECKOUT_SUCCESS = "/checkout_success"
        const val ROUTE_ORDER_DETAIL = "/order_detail"
    }
}