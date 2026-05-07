package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component.UsersContent
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component.UsersError
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component.UsersLoading
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.TestUiData
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.ui.theme.AppBackground
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersScreen(
    uiState: UsersUiState,
    onRetryClick: () -> Unit,
    onUserClick: (Int) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .testTag(UsersScreenTags.SURFACE_USERS_SCREEN),
        color = AppBackground
    ) {
        when (uiState) {
            is UsersUiState.Content -> UsersContent(
                users = uiState.users,
                offline = uiState.offline,
                onUserClick = onUserClick
            )

            is UsersUiState.Loading -> UsersLoading()

            is UsersUiState.Error -> UsersError(
                message = uiState.message,
                onRetryClick = onRetryClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenContentPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersScreen(
            uiState = TestUiData.usersContent,
            onRetryClick = {},
            onUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenLoadingPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersScreen(
            uiState = UsersUiState.Loading,
            onRetryClick = {},
            onUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenErrorPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersScreen(
            uiState = TestUiData.usersError,
            onRetryClick = {},
            onUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersScreenOfflinePreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersScreen(
            uiState = TestUiData.usersOfflineContent,
            onRetryClick = {},
            onUserClick = {}
        )
    }
}