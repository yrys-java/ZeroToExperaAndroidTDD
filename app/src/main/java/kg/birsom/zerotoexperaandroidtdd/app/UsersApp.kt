package kg.birsom.zerotoexperaandroidtdd.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppBackground
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.UserDetailsRoute
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.UsersListRoute
import kotlinx.serialization.Serializable

@Composable
fun UsersApp() {
    val backStack = rememberNavBackStack(UsersNavRoute.Users)
    val navigateBack = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    NavDisplay(
        backStack = backStack,
        modifier = Modifier
            .fillMaxSize()
            .background(AppBackground),
        onBack = navigateBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<UsersNavRoute.Users> {
                UsersListRoute(
                    onUserClick = { userId ->
                        backStack.add(UsersNavRoute.UserDetails(userId))
                    }
                )
            }

            entry<UsersNavRoute.UserDetails> { route ->
                UserDetailsRoute(
                    userId = route.userId,
                    onBackClick = navigateBack
                )
            }
        }
    )
}

@Serializable
private sealed interface UsersNavRoute : NavKey {

    @Serializable
    data object Users : UsersNavRoute

    @Serializable
    data class UserDetails(val userId: Int) : UsersNavRoute
}
