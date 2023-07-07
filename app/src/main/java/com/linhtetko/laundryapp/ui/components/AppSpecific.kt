package com.linhtetko.laundryapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.data.vos.LaundryVO
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@Composable
fun ColumnScope.AuthScreenHeader(title: String, optionText: String) {
    Image(
        imageVector = ImageVector.vectorResource(id = R.drawable.logo),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .width(100.dp)
            .align(Alignment.End)
    )
    Text(text = title, fontSize = 30.sp, fontWeight = FontWeight.Medium)
    Spacer(modifier = Modifier.height(10.dp))
    Text(text = optionText)
}

@Preview(showBackground = true)
@Composable
fun AuthScreenHeaderPreview() {
    LaundryAppTheme() {
        Column(modifier = Modifier.fillMaxWidth()) {
            AuthScreenHeader(title = "Let's get started", optionText = "Login into your account")
        }
    }
}

@Composable
fun AuthFooter(
    primaryText: String, optionText: String, modifier: Modifier = Modifier, onTap: () -> Unit
) {
    TextButton(onClick = onTap, modifier = modifier) {
        Text(text = buildAnnotatedString {
            append(buildAnnotatedString {

                addStyle(
                    style = SpanStyle(
                        color = Color.Gray.copy(alpha = 0.5f)
                    ), 0, optionText.length
                )

                append(optionText)
            })
            append(buildAnnotatedString {
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Medium
                    ), 0, primaryText.length
                )

                append(primaryText)
            })
        })
    }
}

@Preview
@Composable
fun AuthFooterPreview() {
    LaundryAppTheme() {
        AuthFooter(primaryText = "Register", optionText = "Don't have an account?") {}
    }
}

@Composable
fun LaundryReminderCard(
    laundryName: String, message: String, onTapViewDetail: () -> Unit, modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(vertical = 20.dp)
                .padding(start = 20.dp)
        ) {
            Text(text = laundryName)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(80.dp)
            ) {
                Text(
                    text = message,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1.1f)
                )
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_laundry_service),
                    contentDescription = null,
                    modifier = Modifier.weight(0.7f),
                    contentScale = ContentScale.Fit,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            TextButton(onClick = onTapViewDetail) {
                Text(
                    text = stringResource(R.string.lbl_view_detail),
                    color = MaterialTheme.colorScheme.surface,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Preview
@Composable
fun LaundryReminderCardPreview() {
    LaundryAppTheme() {
        LaundryReminderCard(laundryName = "Blah Blah",
            message = "Your clothes \nwill finish in 1 Days",
            onTapViewDetail = {})
    }
}

@Composable
fun LaundryItem(
    modifier: Modifier = Modifier, laundryVO: LaundryVO, onTapItem: (LaundryVO) -> Unit
) {
    Card(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .width(250.dp)
            .clickable(onClick = { onTapItem(laundryVO) }),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.05f
            )
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.placeholder_laundry_machine),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(250.dp)
                .height(150.dp)
                .clip(RoundedCornerShape(percent = 10))
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = laundryVO.name,
            modifier = Modifier.padding(horizontal = 15.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(bottom = 15.dp, top = 5.dp)
        ) {
            Row(modifier = Modifier.weight(1f)) {
                Icon(
                    imageVector = Icons.Sharp.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = laundryVO.location, maxLines = 1, overflow = TextOverflow.Ellipsis)
            }
            Spacer(modifier = Modifier.width(3.dp))
            Text(text = buildAnnotatedString {
                append(buildAnnotatedString {
                    val text = "\$${laundryVO.pricePerCloth}"
                    addStyle(
                        SpanStyle(
                            MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Medium,
                            fontSize = 20.sp
                        ), 0, text.length
                    )
                    append(text)
                })
                append("/pcs")
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LaundryItemPreview() {
    LaundryAppTheme() {
        LaundryItem(laundryVO = LaundryVO.idle(), onTapItem = {})
    }
}