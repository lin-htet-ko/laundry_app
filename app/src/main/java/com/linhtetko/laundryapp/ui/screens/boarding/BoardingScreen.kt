package com.linhtetko.laundryapp.ui.screens.boarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.ui.components.LaundryButton
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardingScreen(modifier: Modifier = Modifier, onGetStarted: () -> Unit) {
    Scaffold(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(it),
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                painter = painterResource(id = R.drawable.boarding_image),
                contentDescription = null,
                modifier = Modifier.weight(1f),
                contentScale = ContentScale.FillWidth
            )
            Text(
                text = stringResource(R.string.lbl_boarding_title),
                color = Color.White,
                fontSize = 30.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 40.sp,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            )
            Text(
                text = stringResource(R.string.lbl_boarding_desc),
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp)
            )
            LaundryButton(
                label = stringResource(R.string.lbl_get_started),
                isReversed = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 30.dp),
                onClick = onGetStarted
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BoardingScreenPreview() {
    LaundryAppTheme {
        BoardingScreen{}
    }
}