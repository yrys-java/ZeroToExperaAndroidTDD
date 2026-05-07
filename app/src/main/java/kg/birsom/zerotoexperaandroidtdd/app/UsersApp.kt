package kg.birsom.zerotoexperaandroidtdd.app

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.UserDetailsScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.viewmodel.UserDetailsViewModel
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.UsersScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.viewmodel.UsersViewModel
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun UsersApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = UsersRoutes.USERS
    ) {
        composable(route = UsersRoutes.USERS) {
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
                        viewModel.retry()
                    }
                },
                onUserClick = { userId ->
                    navController.navigate(UsersRoutes.userDetails(userId))
                }
            )
        }

        composable(
            route = UsersRoutes.USER_DETAILS,
            arguments = listOf(
                navArgument(UsersRoutes.USER_ID) {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(UsersRoutes.USER_ID)
            val viewModel: UserDetailsViewModel = koinViewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            LaunchedEffect(viewModel, userId) {
                if (userId != null) {
                    viewModel.loadUser(userId)
                } else {
                    viewModel.loadUser()
                }
            }

            UserDetailsScreen(
                uiState = uiState,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}

private object UsersRoutes {

    const val USERS = "users"
    const val USER_ID = "userId"
    const val USER_DETAILS = "user_details/{$USER_ID}"

    fun userDetails(userId: Int) = "user_details/$userId"
}
