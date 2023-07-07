package com.linhtetko.laundryapp.ui.components.app_specific

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.MessageVO
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@Composable
fun UserChatItem(message: MessageVO,modifier: Modifier = Modifier) {

    Row(modifier = modifier
        .height(70.dp)
        .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = message.senderImage as Int),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = message.senderName, fontWeight = FontWeight.Medium, fontSize = 16.sp)
                Text(text = message.changeDateFormat())
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = message.message,
                softWrap = false,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = Color.Gray
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun UserChatItemPreview() {
    LaundryAppTheme() {
        UserChatItem(message = MessageVO.idle())
    }
}