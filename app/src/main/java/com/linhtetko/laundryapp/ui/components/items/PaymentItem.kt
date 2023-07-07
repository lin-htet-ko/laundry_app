package com.linhtetko.laundryapp.ui.components.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.PaymentVO
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@Composable
fun PaymentItem(paymentItem: PaymentVO, onTap: (PaymentVO) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(horizontal = 10.dp, vertical = 5.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = paymentItem.icon),
            contentDescription = null,
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray.copy(alpha = 0.2f))
        )
        Text(
            text = paymentItem.name, fontWeight = FontWeight.Medium, modifier = Modifier
                .weight(1f)
                .padding(horizontal = 10.dp)
        )
        RadioButton(selected = paymentItem.isSelected, onClick = { onTap.invoke(paymentItem) })
    }
}

@Preview(showBackground = true)
@Composable
fun PaymentItemPreview() {
    var paymentVO : PaymentVO by remember {
        mutableStateOf(PaymentVO(id = 1, name = "Apple Pay", icon = R.drawable.ic_apple))
    }
    LaundryAppTheme() {
        PaymentItem(
            paymentItem = paymentVO,
            onTap = { paymentVO = paymentVO.copy(isSelected = !paymentVO.isSelected)},
            modifier = Modifier.fillMaxWidth()
        )
    }
}