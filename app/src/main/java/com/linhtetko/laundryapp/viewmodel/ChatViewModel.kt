package com.linhtetko.laundryapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.linhtetko.laundryapp.navigation.navigator.ChatNavigator
import com.linhtetko.laundryapp.ui.screens.main.chat.ChatState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatNavigator: ChatNavigator
) : ViewModel() {

    private var _chatState = mutableStateOf(ChatState.idle())
    private val _cacheState = { _chatState.value }
    val chatState: State<ChatState> = _chatState

    fun setUpNavController(rootController: NavController, childNavController: NavController) {
        chatNavigator.navController = rootController
        chatNavigator.childNavController = childNavController
    }

    fun onChangeSearchKeyword(keyword: String) {
        _chatState.value = _cacheState().copy(keyword = keyword)
    }
}