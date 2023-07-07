package com.linhtetko.laundryapp.ui.screens.main.history

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import com.linhtetko.laundryapp.data.vos.HistoryVO
import com.linhtetko.laundryapp.enums.FinishingStatus
import com.linhtetko.laundryapp.enums.LaundryStatus
import com.linhtetko.laundryapp.navigation.navigator.HistoryNavigatorImpl
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.HistoryViewModel

@Composable
fun HistoryScreen(historyViewModel: HistoryViewModel, modifier: Modifier = Modifier) {
    val historyState by remember {
        historyViewModel.historyState
    }
    Crossfade(targetState = historyState.histories.isEmpty()) { status ->
        if (status) {
            EmptyHistoryScreen(modifier = modifier)
        } else {
            HistoryListStatelessScreen(
                modifier = modifier,
                histories = historyState.histories,
                onTapHistoryItem = historyViewModel::onTapHistoryItem
            )
        }
    }


}

@Preview(showBackground = true)
@Composable
fun HistoryScreenPreview() {
    LaundryAppTheme() {
        HistoryScreen(HistoryViewModel(HistoryNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryListStatelessScreen(
    modifier: Modifier = Modifier, histories: List<HistoryVO>, onTapHistoryItem: (HistoryVO) -> Unit
) {
    Scaffold(
        topBar = {
            HistoryTopAppbar()
        }, modifier = modifier.fillMaxSize(), containerColor = MaterialTheme.colorScheme.surface
    ) {
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            items(histories) { value ->
                HistoryItem(history = value, onTap = onTapHistoryItem)
            }
        }
    }
}

@Preview
@Composable
fun HistoryListStatelessScreenPreview() {
    LaundryAppTheme() {
        HistoryListStatelessScreen(histories = emptyList(), onTapHistoryItem = {})
    }
}

@Composable
fun EmptyHistoryScreen(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_empty_laundry),
            contentDescription = null,
            modifier = Modifier,
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.lbl_no_laundry_history_yet),
            fontWeight = FontWeight.Medium,
            fontSize = 25.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = stringResource(R.string.lbl_let_s_do_laundry_transactions_now_and_feel_the_convenience),
            textAlign = TextAlign.Center
        )

    }
}

@Preview
@Composable
fun EmptyHistoryScreenPreview() {
    LaundryAppTheme() {
        EmptyHistoryScreen()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryTopAppbar(modifier: Modifier = Modifier) {
    TopAppBar(modifier = modifier,
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
        title = {
            Text(
                text = stringResource(id = R.string.lbl_history),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
        })
}

@Preview
@Composable
fun HistoryTopAppbarPreview() {
    LaundryAppTheme() {
        HistoryTopAppbar()
    }
}

@Composable
fun HistoryItem(modifier: Modifier = Modifier, history: HistoryVO, onTap: (HistoryVO) -> Unit) {
    Column(
        modifier = modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .clip(RoundedCornerShape(percent = 10))
            .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
            .fillMaxWidth()
            .height(200.dp)
            .padding(15.dp)
            .clickable(onClick = { onTap(history) }),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Text(text = history.name, fontSize = 20.sp, fontWeight = FontWeight.Medium)
            HistoryCompleteStatus(status = history.laundryStatus.finishingStatus == FinishingStatus.Finish)
        }
        Text(text = history.changeDateFormat(), color = Color.Gray)
        Divider()
        LaundryProgress(
            selectedStatus = history.laundryStatus, modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = false)
@Composable
fun HistoryItemPreview() {
    LaundryAppTheme() {
        HistoryItem(history = HistoryVO.idle(), onTap = {})
    }
}

@Composable
fun HistoryStatus(
    laundryStatus: LaundryStatus, modifier: Modifier = Modifier
) {
    val circleModifier = Modifier
        .clip(CircleShape)
        .size(20.dp)
        .background(MaterialTheme.colorScheme.primary)

    val finishingStatus = laundryStatus.finishingStatus

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        when (finishingStatus) {

            FinishingStatus.Finish, FinishingStatus.Start -> {
                Box(modifier = circleModifier)
            }

            FinishingStatus.Continue -> {
                Box(modifier = circleModifier) {
                    Box(
                        modifier = circleModifier
                            .padding(7.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                    )
                }
            }

            FinishingStatus.NotStart -> {
                Box(
                    modifier = circleModifier.background(Color.Gray)
                )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = laundryStatus.label,
            color = when (finishingStatus) {
                FinishingStatus.NotStart -> Color.Gray
                FinishingStatus.Start, FinishingStatus.Continue, FinishingStatus.Finish -> MaterialTheme.colorScheme.onSurface
            },
            fontWeight = FontWeight.Medium,
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        )
    }
}

@Composable
fun LaundryProgress(
    selectedStatus: LaundryStatus, modifier: Modifier = Modifier
) {


    val laundryStatus = mutableListOf(
        LaundryStatus.Washing(FinishingStatus.NotStart, "Washing"),
        LaundryStatus.Cleaning(FinishingStatus.NotStart, "Cleaning"),
        LaundryStatus.Drying(FinishingStatus.NotStart, "Drying"),
        LaundryStatus.Deliver(FinishingStatus.NotStart, "Deliver")
    )
    val temp = laundryStatus.toMutableList()
    temp.clear()

    when (selectedStatus) {
        is LaundryStatus.Washing -> {
            temp.addAll(
                listOf(
                    LaundryStatus.Washing(FinishingStatus.Continue, "Washing"),
                    LaundryStatus.Cleaning(FinishingStatus.NotStart, "Cleaning"),
                    LaundryStatus.Drying(FinishingStatus.NotStart, "Drying"),
                    LaundryStatus.Deliver(FinishingStatus.NotStart, "Deliver")
                )
            )
        }

        is LaundryStatus.Cleaning -> {
            temp.addAll(
                listOf(
                    LaundryStatus.Washing(FinishingStatus.Start, "Washing"),
                    LaundryStatus.Cleaning(FinishingStatus.Continue, "Cleaning"),
                    LaundryStatus.Drying(FinishingStatus.NotStart, "Drying"),
                    LaundryStatus.Deliver(FinishingStatus.NotStart, "Deliver")
                )
            )
        }

        is LaundryStatus.Drying -> {
            temp.addAll(
                listOf(
                    LaundryStatus.Washing(FinishingStatus.Start, "Washing"),
                    LaundryStatus.Cleaning(FinishingStatus.Start, "Cleaning"),
                    LaundryStatus.Drying(FinishingStatus.Continue, "Drying"),
                    LaundryStatus.Deliver(FinishingStatus.NotStart, "Deliver")
                )
            )
        }

        is LaundryStatus.Deliver -> {
            temp.addAll(
                listOf(
                    LaundryStatus.Washing(FinishingStatus.Start, "Washing"),
                    LaundryStatus.Cleaning(FinishingStatus.Start, "Cleaning"),
                    LaundryStatus.Drying(FinishingStatus.Start, "Drying"),
                    LaundryStatus.Deliver(
                        if (selectedStatus.finishingStatus != FinishingStatus.Finish) FinishingStatus.Finish else FinishingStatus.Continue,
                        "Deliver"
                    )
                )
            )
        }
    }

    laundryStatus.clear()
    laundryStatus.addAll(temp)

    val length = laundryStatus.size
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        laundryStatus.forEachIndexed { index, item ->
            HistoryStatus(laundryStatus = item)
            if (index >= 0 && index < length - 1) Spacer(modifier = Modifier.width(10.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaundryProgressPreview() {
    LaundryAppTheme {
        LaundryProgress(
            selectedStatus = LaundryStatus.Cleaning(
                FinishingStatus.Continue, "Washing"
            ), modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryStatusPreview() {
    val laundryStatus by remember {
        mutableStateOf(LaundryStatus.Drying(FinishingStatus.Finish, "Drying"))
    }
    LaundryAppTheme() {
        HistoryStatus(laundryStatus)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryCompleteStatus(status: Boolean, modifier: Modifier = Modifier) {
    Badge(modifier = modifier, containerColor = Color(if (status) 0xFF61e7be else 0xFFfdc944)) {
        Text(
            text = if (status) "Complete" else "Ongoing",
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 5.dp),
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HistoryCompleteStatusPreview() {
    LaundryAppTheme() {
        HistoryCompleteStatus(status = true)
    }
}