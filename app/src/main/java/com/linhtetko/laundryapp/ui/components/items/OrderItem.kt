package com.linhtetko.laundryapp.ui.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.OrderVO
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@Composable
fun OrderItem(
    orderVO: OrderVO,
    onTapRemove: (OrderVO) -> Unit,
    onTapAdd: (OrderVO) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp, 5.dp)
            .clip(RoundedCornerShape(percent = 20))
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_laundry_machine),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 10.dp)
        ) {
            Text(text = orderVO.name, fontWeight = FontWeight.Medium, fontSize = 18.sp)
            Text(text = "\$ ${orderVO.price}")
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(0.7f)
        ) {
            OutlinedIconButton(
                onClick = { onTapRemove(orderVO) },
                border = BorderStroke(0.5.dp, Color.Gray),
                modifier = Modifier.size(30.dp)
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_remove),
                    contentDescription = null,
                    modifier = Modifier.padding(5.dp)
                )
            }
            Text(text = orderVO.count.toString(), fontSize = 18.sp)
            OutlinedIconButton(
                onClick = { onTapAdd(orderVO) },
                border = BorderStroke(0.5.dp, Color.Gray),
                modifier = Modifier.size(30.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderItemPreview() {
    LaundryAppTheme {
        OrderItem(
            orderVO = OrderVO.idle(), onTapAdd = {}, onTapRemove = {})
    }
}