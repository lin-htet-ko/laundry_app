package com.linhtetko.laundryapp.ui.screens.main.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.linhtetko.laundryapp.R
import com.linhtetko.laundryapp.navigation.navigator.ProfileNavigator
import com.linhtetko.laundryapp.navigation.navigator.ProfileNavigatorImpl
import com.linhtetko.laundryapp.ui.theme.LaundryAppTheme
import com.linhtetko.laundryapp.viewmodel.ProfileViewModel

@Composable
fun ProfileScreen(profileViewModel: ProfileViewModel, modifier: Modifier = Modifier) {
    val profileState: ProfileState by remember {
        profileViewModel.profileState
    }
    ProfileStatelessScreen(
        modifier = modifier,
        profileState = profileState,
        onTapAccount = profileViewModel::onTapAccount,
        onTapLanguage = profileViewModel::onTapLanguage,
        onTapNotification = profileViewModel::onTapNotification,
        onTapPreference = profileViewModel::onTapPreference,
        onTapHelp = profileViewModel::onTapHelp
    )
}

@Preview
@Composable
fun ProfileScreenPreview() {
    LaundryAppTheme() {
        ProfileScreen(profileViewModel = ProfileViewModel(ProfileNavigatorImpl()))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileStatelessScreen(
    modifier: Modifier = Modifier,
    profileState: ProfileState,
    onTapAccount: () -> Unit,
    onTapLanguage: () -> Unit,
    onTapNotification: () -> Unit,
    onTapPreference: () -> Unit,
    onTapHelp: () -> Unit
) {
    Scaffold(
        topBar = {
            ProfileTopAppbar()
        },
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                ProfileImage(profileState.profile.image)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = profileState.profile.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "ID: ${profileState.profile.id}", fontSize = 15.sp)
            }
            item {
                ProfileCardItem(
                    leadingIcon = Icons.Default.Person,
                    label = stringResource(R.string.lbl_account),
                    endIcon = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                    onClick = onTapAccount
                )
                ProfileCardItem(
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_language),
                    label = stringResource(R.string.lbl_language),
                    endIcon = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                    onClick = onTapLanguage
                )
                ProfileCardItem(
                    leadingIcon = Icons.Default.Notifications,
                    label = stringResource(R.string.lbl_notification),
                    endIcon = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                    onClick = onTapNotification
                )
                ProfileCardItem(
                    leadingIcon = Icons.Default.Settings,
                    label = stringResource(R.string.lbl_preference),
                    endIcon = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                    onClick = onTapPreference
                )
                ProfileCardItem(
                    leadingIcon = Icons.Default.Info,
                    label = stringResource(R.string.lbl_help_center),
                    endIcon = ImageVector.vectorResource(id = R.drawable.ic_chevron_right),
                    onClick = onTapHelp
                )
            }
        }
    }
}

@Preview
@Composable
fun ProfileStatelessScreenPreview() {
    LaundryAppTheme() {
        ProfileStatelessScreen(
            profileState = ProfileState.idle(),
            onTapAccount = { /*TODO*/ },
            onTapLanguage = { /*TODO*/ },
            onTapNotification = { /*TODO*/ },
            onTapPreference = { /*TODO*/ }) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopAppbar(modifier: Modifier = Modifier) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(
                text = stringResource(id = R.string.lbl_profile),
                fontWeight = FontWeight.Medium
            )
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = Color.Transparent
        )
    )
}

@Preview
@Composable
fun ProfileTopAppbarPreview() {
    LaundryAppTheme() {
        ProfileTopAppbar()
    }
}

@Composable
fun ProfileImage(image: Any, modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(id = image as Int),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(120.dp)
            .clip(CircleShape)
    )
}

@Preview
@Composable
fun ProfileImagePreview() {
    LaundryAppTheme() {
        ProfileImage(image = R.drawable.placeholder_person)
    }
}

@Composable
fun ProfileCardItem(
    leadingIcon: ImageVector,
    label: String,
    endIcon: ImageVector,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f)),
        modifier = modifier
            .padding(top = 15.dp)
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
            .height(70.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Gray.copy(alpha = 0.05f))
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = label, modifier = Modifier.weight(1f))
            Icon(
                imageVector = endIcon,
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
fun ProfileCardItemPreview() {
    LaundryAppTheme() {
        ProfileCardItem(Icons.Default.Person, "Account", Icons.Default.ArrowForward) {}
    }
}
