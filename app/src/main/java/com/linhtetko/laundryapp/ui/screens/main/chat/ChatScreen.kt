package com.linhtetko.laundryapp.ui.screens.main.chat

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.navigation.navigator.ChatNavigatorImpl
import com.linhtetko.laundryapp.ui.components.SearchBar
import com.linhtetko.laundryapp.ui.components.app_specific.UserChatItem
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.ChatViewModel

@Composable
fun ChatScreen(chatViewModel: ChatViewModel, modifier: Modifier = Modifier) {
    val chatState: ChatState by remember {
        chatViewModel.chatState
    }
    ChatStatelessScreen(
        modifier = modifier,
        chatState = chatState,
        onSearchKeywordChange = chatViewModel::onChangeSearchKeyword
    )

}

@Preview
@Composable
private fun ChatScreenPreview() {
    LaundryAppTheme() {
        ChatScreen(
            chatViewModel = ChatViewModel(ChatNavigatorImpl())
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatStatelessScreen(
    chatState: ChatState,
    onSearchKeywordChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier, topBar = {
        ChatTopAppbar()
    }) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item {
                SearchBar(
                    placeholder = stringResource(R.string.lbl_search_in_chats),
                    value = chatState.keyword,
                    onValueChange = onSearchKeywordChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .padding(top = 5.dp)
                )
            }
            item {
                val contentColor = LocalContentColor.provides(Color.Gray)
                CompositionLocalProvider(values = arrayOf(contentColor)) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 10.dp)
                            .padding(top = 20.dp)
                    ) {
                        Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = stringResource(R.string.lbl_pin_messages))
                    }
                }
            }
            items(chatState.pinMessages) { value ->
                UserChatItem(
                    message = value,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 5.dp)
                )
            }
            item {
                val contentColor = LocalContentColor.provides(Color.Gray)
                CompositionLocalProvider(values = arrayOf(contentColor)) {
                    Text(
                        text = stringResource(R.string.lbl_all_messages), modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .padding(top = 20.dp)
                    )
                }
            }
            items(chatState.allMessages) { value ->
                UserChatItem(
                    message = value,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ChatStatelessScreenPreview() {
    LaundryAppTheme() {
        ChatStatelessScreen(ChatState.idle(), onSearchKeywordChange = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChatTopAppbar(modifier: Modifier = Modifier) {
    TopAppBar(modifier = modifier, title = {
        Text(
            text = stringResource(R.string.lbl_chat),
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium
        )
    })
}

@Preview
@Composable
private fun ChatTopAppbarPreview() {
    LaundryAppTheme() {
        ChatTopAppbar()
    }
}