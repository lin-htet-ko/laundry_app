package com.linhtetko.laundryapp.ui.screens.main.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.LaundryVO
import com.linhtetko.laundryapp.navigation.navigator.HomeNavigatorImpl
import com.linhtetko.laundryapp.ui.components.LaundryItem
import com.linhtetko.laundryapp.ui.components.LaundryReminderCard
import com.linhtetko.laundryapp.ui.components.SearchBar
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.HomeViewModel

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeViewModel: HomeViewModel) {

    val homeState by remember {
        homeViewModel.homeState
    }
    HomeStatelessScreen(
        modifier = modifier,
        homeState = homeState,
        onSearchKeywordChange = homeViewModel::onSearchKeywordChange,
        onTapNotification = homeViewModel::onTapNotification,
        onTapViewDetail = homeViewModel::onTapViewDetail,
        onTapNearestLaundrySeeMore = homeViewModel::onTapNearestLaundrySeeMore,
        onTapLaundryItem = homeViewModel::onTapLaundryItem
    )
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    LaundryAppTheme() {
        HomeScreen(homeViewModel = HomeViewModel(HomeNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeStatelessScreen(
    homeState: HomeState,
    onSearchKeywordChange: (String) -> Unit,
    onTapNotification: () -> Unit,
    onTapViewDetail: () -> Unit,
    onTapLaundryItem: (LaundryVO) -> Unit,
    onTapNearestLaundrySeeMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeTopAppbar(townName = homeState.townName, onTapNotification = onTapNotification)
        },) {
        LazyColumn(
            modifier = modifier
                .padding(it)
                .fillMaxSize()
        ) {
            item {
                SearchBar(
                    placeholder = stringResource(R.string.lbl_find_the_nearest_laundromat),
                    value = homeState.searchKeyword,
                    onValueChange = onSearchKeywordChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                )
            }

            item {
                LaundryReminderCard(
                    laundryName = homeState.orderLaundryName,
                    message = homeState.orderLaundryDesc,
                    onTapViewDetail = onTapViewDetail,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 10.dp)
                ) {
                    Text(
                        text = stringResource(R.string.lbl_nearest_laundry),
                        fontWeight = FontWeight.Medium
                    )
                    TextButton(onClick = onTapNearestLaundrySeeMore) {
                        Text(text = stringResource(R.string.lbl_see_more))
                    }
                }
            }

            item {
                LazyRow(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ) {
                    items(homeState.laundries) { item ->
                        LaundryItem(
                            laundryVO = item,
                            onTapItem = onTapLaundryItem
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeStatelessScreenPreview() {
    LaundryAppTheme() {
        HomeStatelessScreen(
            homeState = HomeState.idle(),
            onSearchKeywordChange = {},
            onTapNotification = { /*TODO*/ },
            onTapViewDetail = { /*TODO*/ },
            onTapNearestLaundrySeeMore = { /*TODO*/ },
        onTapLaundryItem = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppbar(modifier: Modifier = Modifier, townName: String, onTapNotification: () -> Unit) {
    TopAppBar(colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.Transparent),
        modifier = modifier.padding(10.dp),
        title = {
            Column {
                Text(
                    text = stringResource(R.string.lbl_current_location),
                    fontSize = 15.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(5.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = townName, fontSize = 16.sp, fontWeight = FontWeight.Medium
                    )
                }
            }
        },
        actions = {
            OutlinedIconButton(
                onClick = onTapNotification,
                border = BorderStroke(0.1.dp, Color.Gray),
                shape = RoundedCornerShape(percent = 30)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        })
}

@Preview(showBackground = true)
@Composable
fun HomeTopAppbarPreview() {
    LaundryAppTheme() {
        HomeTopAppbar(townName = "Hlaing, Burma", onTapNotification = {})
    }
}
