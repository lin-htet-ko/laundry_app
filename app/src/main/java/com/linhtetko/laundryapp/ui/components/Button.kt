package com.linhtetko.laundryapp.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@Composable
fun LaundryButton(
    label: String,
    modifier: Modifier = Modifier,
    isReversed: Boolean = false,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier.height(45.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (!isReversed) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface,
            contentColor = if (isReversed) MaterialTheme.colorScheme.primary
            else MaterialTheme.colorScheme.surface
        ),
        shape = RoundedCornerShape(percent = 20),
        onClick = onClick
    ) {
        Text(text = label)
    }
}

@Preview
@Composable
fun LaundryButtonPreview() {
    LaundryAppTheme(darkTheme = false) {
        LaundryButton(modifier = Modifier.fillMaxWidth(), label = "Get Started", onClick = {})
    }
}

@Composable
fun AuthProviderButton(
    imageVector: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    onTap: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onTap,
        shape = RoundedCornerShape(percent = 20),
        border = BorderStroke(0.5.dp, Color.Gray.copy(alpha = 0.5f))
    ) {
        Image(
            imageVector = imageVector,
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = label)
    }
}

@Preview
@Composable
fun AuthProviderButtonPreview() {
    LaundryAppTheme {
        AuthProviderButton(
            label = "Google",
            imageVector = Icons.Default.Settings,
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
        ) {}
    }
}