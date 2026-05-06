package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.UsersScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UsersUiState
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UsersScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val usersPage = UsersPage(composeTestRule)

    @Test
    fun shows_users_content() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersScreen(
                    uiState = UsersUiState.Content(
                        users = listOf(
                            UserUi(
                                id = 1,
                                name = "Leanne Graham",
                                email = "Sincere@april.biz"
                            ),
                            UserUi(
                                id = 2,
                                name = "Ervin Howell",
                                email = "Shanna@melissa.tv"
                            )
                        ),
                        offline = false
                    ),
                    onRetryClick = {},
                    onUserClick = {}
                )
            }
        }

        usersPage.assertUserAt(
            position = 0,
            name = "Leanne Graham",
            email = "Sincere@april.biz"
        )
        usersPage.assertUserAt(
            position = 1,
            name = "Ervin Howell",
            email = "Shanna@melissa.tv"
        )

        usersPage.assertAvatarAt(
            position = 0,
            initials = "LG"
        )
        usersPage.assertAvatarAt(
            position = 1,
            initials = "EH"
        )
    }
}