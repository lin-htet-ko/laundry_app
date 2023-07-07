package com.linhtetko.laundryapp.ui.screens.checkout_payment

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
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.PaymentVO
import com.linhtetko.laundryapp.navigation.navigator.CheckoutPaymentNavigatorImpl
import com.linhtetko.laundryapp.ui.components.LaundryButton
import com.linhtetko.laundryapp.ui.components.items.PaymentItem
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.CheckoutPaymentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPaymentScreen(
    checkoutPaymentViewModel: CheckoutPaymentViewModel,
    modifier: Modifier = Modifier
) {

    val checkoutPaymentState: CheckoutPaymentState by remember {
        checkoutPaymentViewModel.checkoutPaymentState
    }

    CheckoutPaymentStatelessScreen(
        checkoutPaymentState = checkoutPaymentState,
        onTapBack = checkoutPaymentViewModel::onTapBack,
        onTapMore = checkoutPaymentViewModel::onTapMore,
        onTapSelfService = checkoutPaymentViewModel::onTapSelfService,
        onTapDeliService = checkoutPaymentViewModel::onTapDeliService,
        onTapPaymentItem = checkoutPaymentViewModel::onTapPaymentItem,
        onTapEditAddress = checkoutPaymentViewModel::onTapEditAddress,
        onAddNewPayment = checkoutPaymentViewModel::onAddNewPayment,
        onPay = checkoutPaymentViewModel::onPay
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPaymentStatelessScreen(
    modifier: Modifier = Modifier,
    checkoutPaymentState: CheckoutPaymentState,
    onTapBack: () -> Unit,
    onTapMore: () -> Unit,
    onTapSelfService: () -> Unit,
    onTapDeliService: () -> Unit,
    onTapPaymentItem: (PaymentVO) -> Unit,
    onTapEditAddress: () -> Unit,
    onAddNewPayment: () -> Unit,
    onPay: () -> Unit
) {
    val payments = checkoutPaymentState.paymentMethods.toMutableList()
    Scaffold(
        topBar = { CheckoutPaymentAppbar(onTapBack = onTapBack, onTapMore = onTapMore) },
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
        bottomBar = {
            LaundryButton(
                label = "Pay ( $ ${checkoutPaymentState.price} )",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 20.dp),
                onClick = onPay
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {
            item {
                CheckoutPaymentServiceCard(
                    address = checkoutPaymentState.address,
                    isDelService = checkoutPaymentState.isDeliService,
                    isSelfService = checkoutPaymentState.isSelfService,
                    onTapDeliService = onTapDeliService,
                    onTapSelfService = onTapSelfService,
                    onTapEditAddress = onTapEditAddress
                )
            }
            item {
                Text(
                    text = stringResource(R.string.lbl_payment_method),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 30.dp)
                )
            }
            item {
                val size = payments.size
                PaymentMethodsCard(
                    modifier = Modifier.padding(top = 20.dp),
                    onTapAddNew = onAddNewPayment,
                    content = {
                        payments.forEachIndexed { index: Int, item: PaymentVO ->
                            PaymentItem(paymentItem = item, onTap = onTapPaymentItem)
                            if (index >= 0 && index < size - 1) {
                                Divider(
                                    modifier = Modifier.padding(
                                        vertical = 5.dp,
                                        horizontal = 15.dp
                                    )
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun CheckoutPaymentStatelessScreenPreview() {
    LaundryAppTheme() {
        CheckoutPaymentStatelessScreen(
            checkoutPaymentState = CheckoutPaymentState.idle(),
            onTapBack = { /*TODO*/ },
            onTapMore = { /*TODO*/ },
            onTapSelfService = { /*TODO*/ },
            onTapDeliService = { /*TODO*/ },
            onTapPaymentItem = {},
            onAddNewPayment = {},
            onPay = {},
            onTapEditAddress = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CheckoutPaymentScreenPreview() {
    LaundryAppTheme {
        CheckoutPaymentScreen(CheckoutPaymentViewModel(CheckoutPaymentNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutPaymentAppbar(
    modifier: Modifier = Modifier,
    onTapBack: () -> Unit,
    onTapMore: () -> Unit
) {
    val shape = RoundedCornerShape(percent = 20)
    val containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f)
    val border = BorderStroke(0.dp, containerColor)
    val colors = IconButtonDefaults.outlinedIconButtonColors(containerColor = containerColor)

    CenterAlignedTopAppBar(
        modifier = modifier,
        title = { Text(text = "Checkout") },
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
fun CheckoutPaymentAppbarPreview() {
    LaundryAppTheme() {
        CheckoutPaymentAppbar(onTapBack = {}, onTapMore = {})
    }
}

@Composable
fun CheckoutPaymentServiceCard(
    modifier: Modifier = Modifier,
    isSelfService: Boolean,
    isDelService: Boolean,
    address: String,
    onTapSelfService: () -> Unit,
    onTapDeliService: () -> Unit,
    onTapEditAddress: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .clip(RoundedCornerShape(percent = 5))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .requiredHeight(200.dp)
            .padding(10.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RadioButton(selected = isSelfService, onClick = onTapSelfService)
            Text(
                text = stringResource(R.string.lbl_self_service),
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp
            )
        }
        Divider(modifier = Modifier.padding(5.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            RadioButton(
                selected = isDelService,
                onClick = onTapDeliService,
                modifier = Modifier.height(40.dp)
            )
            Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.lbl_deli_service),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .height(40.dp)
                        .padding(vertical = 7.dp)
                )

                Text(
                    text = address,
                    color = Color.DarkGray
                )
                TextButton(onClick = onTapEditAddress, modifier = Modifier.align(Alignment.End)) {
                    Text(
                        text = stringResource(R.string.lbl_edit_address),
                        textDecoration = TextDecoration.Underline,
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun CheckoutPaymentServiceCardPreview() {
    var isSelfService: Boolean by remember {
        mutableStateOf(false)
    }
    var isDelService: Boolean by remember {
        mutableStateOf(false)
    }
    LaundryAppTheme() {
        CheckoutPaymentServiceCard(
            address = "La Min Condo, Hlaing, Burma",
            isDelService = isDelService,
            isSelfService = isSelfService,
            onTapDeliService = {
                isSelfService = false
                isDelService = !isDelService
            },
            onTapSelfService = {
                isDelService = false
                isSelfService = !isSelfService
            }, onTapEditAddress = {}
        )
    }
}

@Composable
fun PaymentMethodsCard(
    modifier: Modifier = Modifier,
    onTapAddNew: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(percent = 5))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        content.invoke()

//        Spacer(modifier = Modifier.height(5.dp))
        TextButton(onClick = onTapAddNew) {
            Text(
                text = stringResource(R.string.lbl_add_new_method),
                textDecoration = TextDecoration.Underline
            )
        }
    }
}

@Preview
@Composable
fun PaymentMethodsCardPreview() {
    val payments: MutableList<PaymentVO> = remember {
        mutableStateListOf(
            PaymentVO(id = 1, name = "Apple Pay", icon = R.drawable.ic_apple),
            PaymentVO(id = 2, name = "Credit Card", icon = R.drawable.ic_credit_card),
            PaymentVO(id = 3, name = "Paypal", icon = R.drawable.ic_paypal)
        )
    }
    LaundryAppTheme() {
        LazyColumn {
            val size = payments.size
            item {
                PaymentMethodsCard(
                    onTapAddNew = {},
                    content = {
                        payments.forEachIndexed { index: Int, item: PaymentVO ->
                            PaymentItem(paymentItem = item, onTap = { tItem ->
                                val temp = payments.toList()
                                payments.clear()
                                payments.addAll(temp.map { tempItem ->
                                    tempItem.copy(isSelected = tempItem.id == tItem.id)
                                })
                            })
                            if (index >= 0 && index < size - 1) {
                                Divider(
                                    modifier = Modifier.padding(
                                        vertical = 5.dp,
                                        horizontal = 15.dp
                                    )
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}