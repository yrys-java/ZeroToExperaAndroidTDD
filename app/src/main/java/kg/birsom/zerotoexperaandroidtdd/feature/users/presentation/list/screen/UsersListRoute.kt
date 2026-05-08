package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel.UsersViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersListRoute(
    onUserClick: (Int) -> Unit,
    viewModel: UsersViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.onStarted()
    }

    UsersScreen(
        uiState = uiState,
        onRetryClick = viewModel::retryUsers,
        onBackClick = viewModel::restoreCachedUsersFromError,
        onUserClick = onUserClick
    )
}