package com.linhtetko.laundryapp.ui.screens.order_detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.OrderVO
import com.linhtetko.laundryapp.enums.FinishingStatus
import com.linhtetko.laundryapp.enums.LaundryStatus
import com.linhtetko.laundryapp.navigation.navigator.OrderDetailNavigatorImpl
import com.linhtetko.laundryapp.ui.screens.main.history.HistoryCompleteStatus
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.OrderDetailViewModel

@Composable
fun OrderDetailScreen(modifier: Modifier = Modifier, orderDetailViewModel: OrderDetailViewModel) {

    val orderDetailState: OrderDetailState by remember {
        orderDetailViewModel.orderDetailState
    }

    OrderDetailStatelessScreen(
        modifier = modifier,
        orderDetailState = orderDetailState,
        onTapBack = orderDetailViewModel::onTapBack,
        onTapMore = orderDetailViewModel::onTapMore
    )

}

@Preview
@Composable
fun OrderDetailScreenPreview() {
    LaundryAppTheme {
        OrderDetailScreen(orderDetailViewModel = OrderDetailViewModel(OrderDetailNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailStatelessScreen(
    modifier: Modifier = Modifier,
    orderDetailState: OrderDetailState,
    onTapBack: () -> Unit,
    onTapMore: () -> Unit,
) {

    val isFinish = orderDetailState.list.last().finishingStatus == FinishingStatus.Finish
    val current = orderDetailState.list.first { it.finishingStatus == FinishingStatus.Continue }

    Scaffold(topBar = {
        OrderDetailTopAppbar(onTapBack = onTapBack, onTapMore = onTapMore)
    }, modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(20.dp)
        ) {
            if (!isFinish)
                OrderDetailStatusCard(current)
            Spacer(modifier = Modifier.height(40.dp))
            OrderDetailStatusProgress(items = orderDetailState.list)
            Spacer(modifier = Modifier.height(30.dp))
            OrderDetailCard(orderVO = orderDetailState.orderVO)
        }
    }
}

@Preview
@Composable
fun OrderDetailStatelessScreenPreview() {
    LaundryAppTheme() {
        OrderDetailStatelessScreen(
            orderDetailState = OrderDetailState.idle(),
            onTapBack = { },
            onTapMore = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDetailTopAppbar(
    modifier: Modifier = Modifier,
    onTapBack: () -> Unit,
    onTapMore: () -> Unit
) {
    val shape = RoundedCornerShape(percent = 20)
    val containerColor = MaterialTheme.colorScheme.surface
    val border = BorderStroke(0.2.dp, Color.LightGray)
    val colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = containerColor)
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = stringResource(R.string.lbl_order_detail)) },
        navigationIcon = {
            OutlinedIconButton(
                onClick = onTapBack,
                border = border,
                shape = shape,
                colors = colors
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            OutlinedIconButton(
                onClick = onTapMore,
                border = border,
                shape = shape,
                colors = colors
            ) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Preview
@Composable
fun OrderDetailTopAppbarPreview() {
    LaundryAppTheme() {
        OrderDetailTopAppbar(onTapBack = {}, onTapMore = {})
    }
}

@Composable
fun OrderDetailItem(title: String, value: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        Text(text = title, modifier = Modifier.fillMaxWidth(0.4f))
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.fillMaxWidth(1f)
        )
    }
}

@Preview
@Composable
fun OrderDetailItemPreview() {
    LaundryAppTheme() {
        OrderDetailItem(title = "laundry in", value = "August 24,2022/07.25pm")
    }
}

@Composable
fun ColumnScope.OrderDetailCard(orderVO: OrderVO, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "#${orderVO.id}", fontSize = 23.sp, fontWeight = FontWeight.Bold)
        HistoryCompleteStatus(status = false)
    }
    OrderDetailItem(
        title = stringResource(R.string.lbl_laundry_in),
        value = orderVO.changeDateFormat()
    )
    OrderDetailItem(
        title = stringResource(R.string.lbl_delivery_address),
        value = orderVO.address
    )
    OrderDetailItem(title = stringResource(R.string.lbl_estimated_time), value = "Finish in 1 day")
}

@Preview
@Composable
fun OrderDetailCardPreview() {
    LaundryAppTheme() {
        Column {
            OrderDetailCard(orderVO = OrderVO.idle())
        }
    }
}

@Composable
fun OrderDetailStatusCard(laundryStatus: LaundryStatus, modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = ImageVector.vectorResource(id = statusImage(laundryStatus = laundryStatus)),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Your clothes are still ${laundryStatus.label}.\nwill be finished soon",
            textAlign = TextAlign.Center,
            fontSize = 17.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderDetailStatusCardPreview() {
    LaundryAppTheme() {
        OrderDetailStatusCard(
            LaundryStatus.Cleaning(
                FinishingStatus.Finish,
                "Cleaning"
            )
        )
    }
}

@Composable
fun OrderDetailStatusProgressItem(
    status: LaundryStatus,
    modifier: Modifier = Modifier
) {
    val primaryColor = MaterialTheme.colorScheme.primary
    val surfaceColor = MaterialTheme.colorScheme.surface

    val icon = statusImage(laundryStatus = status)
    val isFinished = status.finishingStatus == FinishingStatus.Finish
    val isNotStart = status.finishingStatus == FinishingStatus.NotStart

    Column {
        Box(
            modifier = modifier
                .size(50.dp)
                .clip(CircleShape)
                .border(
                    1.dp,
                    if (isNotStart) Color.LightGray else primaryColor,
                    CircleShape
                )
                .background(if (isFinished) primaryColor else Color.Transparent),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = if (isFinished)
                    Icons.Default.Check else ImageVector.vectorResource(id = icon),
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = if (isNotStart) Color.LightGray else if (isFinished) surfaceColor else primaryColor
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = status.label, fontWeight = FontWeight.Medium)
    }
}

@Preview(showBackground = false)
@Composable
fun OrderDetailStatusProgressItemPreview() {
    LaundryAppTheme() {
        OrderDetailStatusProgressItem(
            status = LaundryStatus.Deliver(
                FinishingStatus.Finish,
                "Washing"
            )
        )
    }
}

@Composable
fun OrderDetailStatusProgress(items: List<LaundryStatus>, modifier: Modifier = Modifier) {
    Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        items.forEach {
            OrderDetailStatusProgressItem(
                status = it
            )
        }
    }
}

@Preview
@Composable
fun OrderDetailStatusProgressPreview() {
    val items = listOf(
        LaundryStatus.Washing(
            FinishingStatus.Finish,
            "Washing"
        ),
        LaundryStatus.Cleaning(
            FinishingStatus.Finish,
            "Washing"
        ), LaundryStatus.Drying(
            FinishingStatus.Continue,
            "Washing"
        ), LaundryStatus.Deliver(
            FinishingStatus.NotStart,
            "Washing"
        )
    )
    LaundryAppTheme() {
        OrderDetailStatusProgress(items = items)
    }
}


private fun statusImage(laundryStatus: LaundryStatus) = when (laundryStatus) {
    is LaundryStatus.Washing -> {
        R.drawable.ic_washing
    }

    is LaundryStatus.Cleaning -> {
        R.drawable.ic_cleaning
    }

    is LaundryStatus.Drying -> {
        R.drawable.ic_drying
    }

    is LaundryStatus.Deliver -> {
        R.drawable.ic_bicycle
    }
}