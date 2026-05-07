package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsScreenTags
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsTestUiData
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserDetailsScreen(
    uiState: UserDetailsUiState,
    onBackClick: () -> Unit,
    onRetryClick: () -> Unit
) {
    when (uiState) {
        is UserDetailsUiState.Content -> UserDetailsContent(
            user = uiState.user,
            onBackClick = onBackClick
        )

        UserDetailsUiState.Loading -> Unit

        is UserDetailsUiState.Error -> Unit
    }
}

@Composable
private fun UserDetailsContent(
    user: UserDetailsUi,
    onBackClick: () -> Unit
) {
    Column {
        Button(
            onClick = onBackClick,
            modifier = Modifier.testTag(UserDetailsScreenTags.BUTTON_DETAILS_BACK)
        ) {
            Text(text = "Back")
        }

        Text(
            text = user.name,
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_NAME)
        )
        Text(
            text = "@${user.username}",
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_USERNAME)
        )
        Text(
            text = user.email,
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_EMAIL)
        )
        Text(
            text = user.phone,
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_PHONE)
        )
        Text(
            text = user.website,
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_WEBSITE)
        )
        Text(
            text = user.address,
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_ADDRESS)
        )
        Text(
            text = user.company,
            modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_COMPANY)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsScreenContentPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserDetailsScreen(
            uiState = UserDetailsTestUiData.detailsContent,
            onBackClick = {},
            onRetryClick = {}
        )
    }
}