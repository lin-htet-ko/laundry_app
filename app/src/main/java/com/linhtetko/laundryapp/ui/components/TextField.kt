package com.linhtetko.laundryapp.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.LaundryTextField(
    label: String,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(text = label, fontSize = 15.sp)
    Spacer(modifier = Modifier.height(10.dp))
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        modifier = Modifier.fillMaxWidth(),

        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = Color.Gray.copy(
                alpha = 0.5f
            )
        )
    )
}

@Preview(showBackground = true)
@Composable
fun LaundryTextFieldPreview() {
    var value by remember {
        mutableStateOf("")
    }
    LaundryAppTheme {
        Column() {
            LaundryTextField(
                label = "Email",
                placeholder = "example@gmail.com",
                value = value,
                onValueChange = { value = it },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    placeholder: String,
    modifier: Modifier = Modifier,
    isEnable: Boolean = true,
    value: String,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        leadingIcon = {
            Icon(
                imageVector = Icons.Sharp.Search,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        },
        placeholder = { Text(text = placeholder, fontSize = 15.sp) },
        value = value,
        enabled = isEnable,
        onValueChange = onValueChange,
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(percent = 20),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            unfocusedBorderColor = Color.LightGray
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    var value: String by remember {
        mutableStateOf("")
    }
    LaundryAppTheme() {
        SearchBar(placeholder = "Search By Blah", value = value) { value = it }
    }
}