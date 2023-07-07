package com.linhtetko.laundryapp.ui.screens.checkout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.OrderVO
import com.linhtetko.laundryapp.navigation.navigator.CheckoutNavigatorImpl
import com.linhtetko.laundryapp.ui.components.LaundryButton
import com.linhtetko.laundryapp.ui.components.items.OrderItem
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(checkoutViewModel: CheckoutViewModel, modifier: Modifier = Modifier) {
    val checkoutState: CheckoutState by remember {
        checkoutViewModel.checkoutState
    }

    CheckoutStatelessScreen(
        checkoutState = checkoutState,
        onTapBack = checkoutViewModel::onTapBack,
        onTapMore = checkoutViewModel::onTapMore,
        onTapCheckout = checkoutViewModel::onTapCheckout,
        onTapAddCategory = checkoutViewModel::onTapAddCategory,
        onAddOrderItem = checkoutViewModel::onAddOrderItem,
        onRemoveOrderItem = checkoutViewModel::onRemoveOrderItem
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutStatelessScreen(
    modifier: Modifier = Modifier,
    checkoutState: CheckoutState,
    onTapBack: () -> Unit,
    onTapMore: () -> Unit,
    onTapCheckout: () -> Unit,
    onTapAddCategory: () -> Unit,
    onAddOrderItem: (OrderVO) -> Unit,
    onRemoveOrderItem: (OrderVO) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier,
        topBar = {
            CheckoutTopAppbar(onTapBack = onTapBack, onTapMore = onTapMore)
        },
        bottomBar = {
            CheckoutBottomNav(itemCount = 5, price = 2.5, onTapCheckout = onTapCheckout)
        }) {
        Column(modifier = Modifier.padding(it)) {
            Text(
                text = checkoutState.laundry.name,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                SuggestionChip(
                    modifier = Modifier.weight(1f),
                    onClick = {},
                    label = {
                        Text(
                            text = checkoutState.laundry.location,
                            fontSize = MaterialTheme.typography.labelSmall.fontSize
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.primary
                        )
                    },
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        borderWidth = 0.5.dp,
                        borderColor = Color.LightGray
                    ),
                )
                Spacer(modifier = Modifier.width(5.dp))
                SuggestionChip(
                    modifier = Modifier.weight(0.8f),
                    onClick = {},
                    label = {
                        Text(
                            text = "${checkoutState.laundry.rating} (${checkoutState.laundry.reviewCount} Reviews)",
                            fontSize = MaterialTheme.typography.labelSmall.fontSize
                        )
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = Color(0xFFfdc944)
                        )
                    },
                    border = SuggestionChipDefaults.suggestionChipBorder(
                        borderWidth = 0.5.dp,
                        borderColor = Color.LightGray
                    )
                )
            }
            Image(
                painter = painterResource(id = R.drawable.placeholder_laundry_machine),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(200.dp)
                    .padding(horizontal = 10.dp)
                    .clip(RoundedCornerShape(percent = 5))
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 10.dp),
                shape = RoundedCornerShape(topStartPercent = 10, topEndPercent = 10),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary.copy(
                        alpha = 0.1f
                    )
                )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp, horizontal = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.lbl_order_list),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    TextButton(onClick = onTapAddCategory) {
                        Text(text = stringResource(R.string.lbl_add_category))
                    }
                }
                LazyColumn(modifier = Modifier.padding(bottom = 10.dp)) {
                    items(checkoutState.orders) { value ->
                        OrderItem(
                            orderVO = value,
                            onTapAdd = onAddOrderItem,
                            onTapRemove = onRemoveOrderItem
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CheckoutStatelessScreenPreview() {
    LaundryAppTheme {
        CheckoutStatelessScreen(
            checkoutState = CheckoutState.idle(),
            onTapBack = { /*TODO*/ },
            onTapMore = { /*TODO*/ },
            onTapCheckout = { /*TODO*/ },
            onTapAddCategory = { /*TODO*/ },
            onAddOrderItem = {},
            onRemoveOrderItem = {}
        )
    }
}

@Composable
fun CheckoutBottomNav(
    modifier: Modifier = Modifier,
    itemCount: Int,
    price: Double,
    onTapCheckout: () -> Unit
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .clip(RoundedCornerShape(topStartPercent = 15, topEndPercent = 15))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(20.dp)
            .padding(top = 10.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_laundry_service),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(50.dp)
            )
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .weight(1f)
            ) {
                Text(
                    text = stringResource(R.string.lbl_total_items),
                    color = Color.Gray.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "$itemCount Items", fontWeight = FontWeight.Medium, fontSize = 19.sp)
            }
            Column() {
                Text(
                    text = stringResource(R.string.lbl_cost),
                    color = Color.Gray.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "\$$price", fontWeight = FontWeight.Medium, fontSize = 19.sp)
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        LaundryButton(
            label = "Checkout",
            onClick = onTapCheckout,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun CheckoutBottomNavPreview() {
    LaundryAppTheme() {
        CheckoutBottomNav(itemCount = 5, price = 2.5, onTapCheckout = {})
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutScreenPreview() {
    LaundryAppTheme {
        CheckoutScreen(CheckoutViewModel(CheckoutNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutTopAppbar(
    onTapBack: () -> Unit,
    onTapMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
        modifier = modifier,
        navigationIcon = {
            IconButton(onClick = onTapBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        title = {
        },
        actions = {
            IconButton(onClick = onTapMore) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        })
}

@Preview
@Composable
fun CheckoutTopAppbarPreview() {
    LaundryAppTheme() {
        CheckoutTopAppbar(
            onTapBack = { /*TODO*/ },
            onTapMore = { /*TODO*/ },
        )
    }
}