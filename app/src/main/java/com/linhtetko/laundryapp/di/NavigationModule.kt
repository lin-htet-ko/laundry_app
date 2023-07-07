package com.linhtetko.laundryapp.di

import com.linhtetko.laundryapp.navigation.navigator.ChatNavigator
import com.linhtetko.laundryapp.navigation.navigator.ChatNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.CheckoutNavigator
import com.linhtetko.laundryapp.navigation.navigator.CheckoutNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.CheckoutPaymentNavigator
import com.linhtetko.laundryapp.navigation.navigator.CheckoutPaymentNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.HistoryNavigator
import com.linhtetko.laundryapp.navigation.navigator.HistoryNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.HomeNavigator
import com.linhtetko.laundryapp.navigation.navigator.HomeNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.LoginNavigator
import com.linhtetko.laundryapp.navigation.navigator.LoginNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.OrderDetailNavigator
import com.linhtetko.laundryapp.navigation.navigator.OrderDetailNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.ProfileNavigator
import com.linhtetko.laundryapp.navigation.navigator.ProfileNavigatorImpl
import com.linhtetko.laundryapp.navigation.navigator.RegisterNavigator
import com.linhtetko.laundryapp.navigation.navigator.RegisterNavigatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface NavigationModule {

    @Binds
    @ViewModelScoped
    fun bindRegisterNavigator(impl: RegisterNavigatorImpl): RegisterNavigator

    @Binds
    @ViewModelScoped
    fun bindLoginNavigator(impl: LoginNavigatorImpl): LoginNavigator

    @Binds
    @ViewModelScoped
    fun bindHomeNavigator(impl: HomeNavigatorImpl): HomeNavigator

    @Binds
    @ViewModelScoped
    fun bindChatNavigator(impl: ChatNavigatorImpl): ChatNavigator

    @Binds
    @ViewModelScoped
    fun bindHistoryNavigator(impl: HistoryNavigatorImpl): HistoryNavigator

    @Binds
    @ViewModelScoped
    fun bindProfileNavigator(impl: ProfileNavigatorImpl): ProfileNavigator

    @Binds
    @ViewModelScoped
    fun bindCheckoutNavigator(impl: CheckoutNavigatorImpl): CheckoutNavigator

    @Binds
    @ViewModelScoped
    fun bindCheckoutPaymentNavigator(impl: CheckoutPaymentNavigatorImpl): CheckoutPaymentNavigator

    @Binds
    @ViewModelScoped
    fun bindOrderDetailNavigator(impl: OrderDetailNavigatorImpl): OrderDetailNavigator
}