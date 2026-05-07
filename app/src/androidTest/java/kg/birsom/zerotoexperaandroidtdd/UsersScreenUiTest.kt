package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase.assertEquals
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

    @Test
    fun shows_placeholder_initial_when_user_name_is_empty() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersScreen(
                    uiState = UsersUiState.Content(
                        users = listOf(
                            UserUi(
                                id = 1,
                                name = "",
                                email = "empty@example.com"
                            )
                        ),
                        offline = false
                    ),
                    onRetryClick = {},
                    onUserClick = {}
                )
            }
        }

        usersPage.assertAvatarAt(
            position = 0,
            initials = "?"
        )
    }

    @Test
    fun shows_avatar_without_lastname() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersScreen(
                    uiState = UsersUiState.Content(
                        users = listOf(
                            UserUi(
                                id = 1,
                                name = "Ervin",
                                email = "empty@example.com"
                            )
                        ),
                        offline = false
                    ),
                    onRetryClick = {},
                    onUserClick = {}
                )
            }
        }

        usersPage.assertAvatarAt(
            position = 0,
            initials = "E"
        )
    }

    @Test
    fun shows_loading_without_users_content() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersScreen(
                    uiState = UsersUiState.Loading,
                    onRetryClick = {},
                    onUserClick = {}
                )
            }
        }

        usersPage.assertLoading()
        usersPage.assertUserDoesNotExist(position = 0)
    }

    @Test
    fun shows_error_with_retry_without_users_and_loading() {
        var retryClickedCount = 0

        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersScreen(
                    uiState = UsersUiState.Error(
                        message = "No internet connection"
                    ),
                    onRetryClick = {
                        retryClickedCount++
                    },
                    onUserClick = {}
                )
            }
        }

        usersPage.assertError(message = "No internet connection")
        usersPage.assertUserDoesNotExist(position = 0)
        usersPage.assertLoadingDoesNotExist()

        usersPage.retry()

        assertEquals(1, retryClickedCount)
    }

    @Test
    fun shows_offline_banner_with_cached_users_without_loading_and_error() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersScreen(
                    uiState = UsersUiState.Content(
                        users = listOf(
                            UserUi(
                                id = 1,
                                name = "Cached Leanne",
                                email = "cached@example.com"
                            )
                        ),
                        offline = true
                    ),
                    onRetryClick = {},
                    onUserClick = {}
                )
            }
        }

        usersPage.assertUserAt(
            position = 0,
            name = "Cached Leanne",
            email = "cached@example.com"
        )
        usersPage.assertOfflineBanner(message = "Offline mode: cached data")
        usersPage.assertLoadingDoesNotExist()
        usersPage.assertErrorDoesNotExist()
    }

    @Test
    fun clicks_user_item_with_correct_user_id() {
        var clickedUserId: Int? = null

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
                    onUserClick = { userId ->
                        clickedUserId = userId
                    }
                )
            }
        }

        assertEquals(null, clickedUserId)

        usersPage.clickUserAt(position = 1)

        assertEquals(2, clickedUserId)
    }
}