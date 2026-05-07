package kg.birsom.zerotoexperaandroidtdd.app

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.UserDetailsScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.UsersScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersApp(
    usersUiState: UsersUiState = UsersAppDemoData.usersContent,
    detailsState: (Int) -> UserDetailsUiState = UsersAppDemoData::detailsState,
    onRetryClick: () -> Unit = {}
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = UsersRoutes.USERS
    ) {
        composable(route = UsersRoutes.USERS) {
            UsersScreen(
                uiState = usersUiState,
                onRetryClick = onRetryClick,
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

            UserDetailsScreen(
                uiState = detailsState(userId ?: return@composable),
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

@Preview(showBackground = true)
@Composable
private fun UsersAppPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersApp()
    }
}