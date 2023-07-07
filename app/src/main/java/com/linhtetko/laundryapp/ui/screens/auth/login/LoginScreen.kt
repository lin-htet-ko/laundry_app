package com.linhtetko.laundryapp.ui.screens.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.navigation.navigator.LoginNavigatorImpl
import com.linhtetko.laundryapp.ui.components.AuthFooter
import com.linhtetko.laundryapp.ui.components.AuthProviderButton
import com.linhtetko.laundryapp.ui.components.AuthScreenHeader
import com.linhtetko.laundryapp.ui.components.LaundryButton
import com.linhtetko.laundryapp.ui.components.LaundryTextField
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel, modifier: Modifier = Modifier) {
    val loginState: LoginState by remember {
        loginViewModel.loginState
    }

    LoginStatelessScreen(
        modifier = modifier,
        loginState = loginState,
        onEmailChange = loginViewModel::onEmailChange,
        onPasswordChange = loginViewModel::onPasswordChange,
        onTapLogin = loginViewModel::onTapLogin,
        onTapLoginWithAnotherAcc = loginViewModel::onTapLoginWithAnotherAcc,
        onTapForgotPassword = loginViewModel::onTapForgotPassword,
        onTapRegister = loginViewModel::onTapRegister,
        onTapGoogleAuth = loginViewModel::onTapGoogleAuth,
        onTapFacebookAuth = loginViewModel::onTapFacebookAuth
    )
}

@Preview
@Composable
fun LoginScreenPreview() {
    LaundryAppTheme {
        LoginScreen(loginViewModel = LoginViewModel(navigator = LoginNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginStatelessScreen(
    loginState: LoginState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onTapLogin: () -> Unit,
    onTapLoginWithAnotherAcc: () -> Unit,
    onTapForgotPassword: () -> Unit,
    onTapRegister: () -> Unit,
    onTapGoogleAuth: () -> Unit,
    onTapFacebookAuth: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(modifier = modifier) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxSize()
                .padding(it)
                .padding(20.dp),
            verticalArrangement = Arrangement.Center
        ) {

            AuthScreenHeader(
                title = stringResource(R.string.lbl_let_s_get_started),
                optionText = stringResource(R.string.lbl_login_into_your_account)
            )

            Spacer(modifier = Modifier.height(50.dp))
            LaundryTextField(
                label = stringResource(id = R.string.lbl_email),
                placeholder = stringResource(R.string.lbl_example_gmail_com),
                keyboardType = KeyboardType.Email,
                value = loginState.email,
                onValueChange = onEmailChange
            )
            Spacer(modifier = Modifier.height(20.dp))
            LaundryTextField(
                label = stringResource(id = R.string.lbl_password),
                placeholder = "********",
                keyboardType = KeyboardType.Password,
                value = loginState.password,
                onValueChange = onPasswordChange
            )
            Spacer(modifier = Modifier.height(5.dp))

            TextButton(onClick = onTapForgotPassword, modifier = Modifier.align(Alignment.End)) {
                Text(text = stringResource(R.string.lbl_forgot_password))
            }
            Spacer(modifier = Modifier.height(30.dp))
            LaundryButton(
                label = stringResource(id = R.string.lbl_login),
                modifier = Modifier.fillMaxWidth(),
                onClick = onTapLogin
            )
            Spacer(modifier = Modifier.height(5.dp))
            TextButton(
                onClick = onTapLoginWithAnotherAcc,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.textButtonColors(contentColor = Color.Gray)
            ) {
                Text(
                    text = stringResource(R.string.lbl_or_login_with_account),
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row {
                AuthProviderButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_google),
                    label = stringResource(R.string.lbl_google),
                    modifier = Modifier.weight(1f),
                    onTap = onTapGoogleAuth
                )
                Spacer(modifier = Modifier.width(10.dp))
                AuthProviderButton(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_facebook),
                    label = stringResource(R.string.lbl_facebook),
                    modifier = Modifier.weight(1f),
                    onTap = onTapFacebookAuth
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            AuthFooter(
                primaryText = stringResource(id = R.string.lbl_register),
                optionText = stringResource(R.string.lbl_don_t_have_an_account),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onTap = onTapRegister
            )
        }
    }
}

@Preview
@Composable
fun LoginStatelessScreenPreview() {
    LaundryAppTheme() {
        LoginStatelessScreen(
            loginState = LoginState.idle(),
            onEmailChange = {},
            onPasswordChange = {},
            onTapLogin = { /*TODO*/ },
            onTapLoginWithAnotherAcc = { /*TODO*/ },
            onTapForgotPassword = { /*TODO*/ },
            onTapRegister = { /*TODO*/ },
            onTapGoogleAuth = { /*TODO*/ },
            onTapFacebookAuth = { /*TODO*/ })
    }
}