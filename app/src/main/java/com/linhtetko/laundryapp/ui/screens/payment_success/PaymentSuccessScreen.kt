package com.linhtetko.laundryapp.ui.screens.payment_success

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.ui.components.LaundryButton
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaymentSuccessScreen(
    modifier: Modifier = Modifier,
    onTapDetailOrder: () -> Unit,
    onTapBackHome: () -> Unit
) {
    val successComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.anim_success))
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LottieAnimation(composition = successComposition)
            Text(
                text = stringResource(R.string.lbl_payment_success),
                fontSize = 25.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(R.string.lbl_checkout_success),
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.weight(1f))
            LaundryButton(
                isReversed = true,
                label = stringResource(R.string.lbl_detail_order),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onClick = onTapDetailOrder
            )
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                onClick = onTapBackHome,
                shape = RoundedCornerShape(percent = 20),
                border = BorderStroke(0.5.dp, Color.LightGray)
            ) {
                Text(text = stringResource(R.string.lbl_back_home), color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun PaymentSuccessScreenPreview() {
    LaundryAppTheme() {
        PaymentSuccessScreen(onTapDetailOrder = { /*TODO*/ }, onTapBackHome = {})
    }
}