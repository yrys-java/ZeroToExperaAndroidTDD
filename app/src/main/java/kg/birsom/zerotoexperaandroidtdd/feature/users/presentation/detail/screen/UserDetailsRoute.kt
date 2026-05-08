package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.viewmodel.UserDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UserDetailsRoute(
    userId: Int,
    onBackClick: () -> Unit,
    viewModel: UserDetailsViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel, userId) {
        viewModel.showUser(userId)
    }

    UserDetailsScreen(
        uiState = uiState,
        onBackClick = onBackClick
    )
}