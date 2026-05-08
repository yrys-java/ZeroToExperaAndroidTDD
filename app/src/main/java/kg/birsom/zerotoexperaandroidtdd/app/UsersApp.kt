package kg.birsom.zerotoexperaandroidtdd.app

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.UserDetailsScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.viewmodel.UserDetailsViewModel
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.UsersScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel.UsersViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersApp() {
    val backStack = rememberNavBackStack(UsersRoute.Users)
    val navigateBack = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    NavDisplay(
        backStack = backStack,
        onBack = navigateBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<UsersRoute.Users> {
                val viewModel: UsersViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()
                val coroutineScope = rememberCoroutineScope()

                LaunchedEffect(viewModel) {
                    viewModel.restoreUsers()

                    if (viewModel.uiState.value == UsersUiState.Loading) {
                        viewModel.loadUsers()
                    }
                }

                UsersScreen(
                    uiState = uiState,
                    onRetryClick = {
                        coroutineScope.launch {
                            viewModel.refreshUsers()
                        }
                    },
                    onBackClick = {
                        viewModel.restoreCachedUsersFromError()
                    },
                    onUserClick = { userId ->
                        backStack.add(UsersRoute.UserDetails(userId))
                    }
                )
            }

            entry<UsersRoute.UserDetails> { route ->
                val viewModel: UserDetailsViewModel = koinViewModel()
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                LaunchedEffect(viewModel, route.userId) {
                    viewModel.loadUser(route.userId)
                }

                UserDetailsScreen(
                    uiState = uiState,
                    onBackClick = navigateBack
                )
            }
        }
    )
}

@Serializable
private sealed interface UsersRoute : NavKey {

    @Serializable
    data object Users : UsersRoute

    @Serializable
    data class UserDetails(val userId: Int) : UsersRoute
}
