package com.linhtetko.laundryapp.ui.screens.auth.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.navigation.navigator.RegisterNavigatorImpl
import com.linhtetko.laundryapp.ui.components.AuthFooter
import com.linhtetko.laundryapp.ui.components.AuthScreenHeader
import com.linhtetko.laundryapp.ui.components.LaundryButton
import com.linhtetko.laundryapp.ui.components.LaundryTextField
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    registerViewModel: RegisterViewModel,
    modifier: Modifier = Modifier
) {
    val registerState: RegisterState by remember {
        registerViewModel.registerState
    }

    RegisterStatelessScreen(
        modifier = modifier,
        registerState = registerState,
        onNameChange = registerViewModel::onNameChange,
        onEmailChange = registerViewModel::onEmailChange,
        onPasswordChange = registerViewModel::onPasswordChange,
        onTapRegister = registerViewModel::onTapRegister,
        onTapLogin = registerViewModel::onTapLogin
    )
}

@Preview
@Composable
fun RegisterScreenPreview() {
    LaundryAppTheme {
        RegisterScreen(
            registerViewModel = RegisterViewModel(
                registerNavigator = RegisterNavigatorImpl()
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterStatelessScreen(
    registerState: RegisterState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTapRegister: () -> Unit,
    onTapLogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(20.dp)
        ) {
            AuthScreenHeader(
                title = stringResource(R.string.lbl_register),
                optionText = stringResource(R.string.lbl_create_new_account_for_you)
            )
            Spacer(modifier = Modifier.weight(0.5f))
            LaundryTextField(
                label = stringResource(R.string.lbl_name),
                placeholder = stringResource(R.string.lbl_name),
                value = registerState.name,
                onValueChange = onNameChange
            )
            Spacer(modifier = Modifier.height(10.dp))
            LaundryTextField(
                label = stringResource(R.string.lbl_email),
                placeholder = stringResource(R.string.lbl_email),
                value = registerState.email,
                onValueChange = onEmailChange
            )
            Spacer(modifier = Modifier.height(10.dp))
            LaundryTextField(
                label = stringResource(R.string.lbl_password),
                placeholder = "******",
                value = registerState.password,
                onValueChange = onPasswordChange
            )
            Spacer(modifier = Modifier.height(30.dp))

            LaundryButton(
                label = stringResource(R.string.lbl_register),
                modifier = Modifier.fillMaxWidth(),
                onClick = onTapRegister
            )
            Spacer(modifier = Modifier.weight(1f))
            AuthFooter(
                primaryText = stringResource(R.string.lbl_login),
                optionText = stringResource(R.string.lbl_already_have_an_account),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally),
                onTap = onTapLogin
            )
        }
    }
}

@Preview
@Composable
fun RegisterStatelessScreenPreview() {
    LaundryAppTheme() {
        RegisterStatelessScreen(
            registerState = RegisterState.idle(),
            onNameChange = {},
            onEmailChange = {},
            onPasswordChange = {},
            onTapRegister = {},
            onTapLogin = { /*TODO*/ })
    }
}