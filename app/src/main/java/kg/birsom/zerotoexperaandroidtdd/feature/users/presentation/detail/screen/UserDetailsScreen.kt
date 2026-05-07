package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.component.UsersTopBar
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.component.UserDetailsContent
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.component.UserDetailsError
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsScreenTags
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsTestUiData
import kg.birsom.zerotoexperaandroidtdd.ui.theme.AppBackground
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserDetailsScreen(
    uiState: UserDetailsUiState,
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag(UserDetailsScreenTags.SURFACE_DETAILS_SCREEN),
        containerColor = AppBackground,
        topBar = {
            UsersTopBar(
                title = stringResource(R.string.user_details_title),
                onBackClick = onBackClick,
                backButtonModifier = Modifier.testTag(UserDetailsScreenTags.BUTTON_DETAILS_BACK)
            )
        }
    ) { innerPadding ->
        when (uiState) {
            is UserDetailsUiState.Content -> UserDetailsContent(
                user = uiState.user,
                modifier = Modifier.padding(innerPadding)
            )

            is UserDetailsUiState.Error -> UserDetailsError(
                message = uiState.message,
                modifier = Modifier.padding(innerPadding)
            )

            UserDetailsUiState.Loading -> Unit
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UserDetailsScreenErrorPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserDetailsScreen(
            uiState = UserDetailsTestUiData.detailsError,
            onBackClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsScreenContentPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserDetailsScreen(
            uiState = UserDetailsTestUiData.detailsContent,
            onBackClick = {}
        )
    }
}