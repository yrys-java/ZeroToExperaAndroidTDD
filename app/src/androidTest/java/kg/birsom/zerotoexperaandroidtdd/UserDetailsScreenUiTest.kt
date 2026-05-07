package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.UserDetailsScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDetailsScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val detailsPage = UserDetailsPage(composeTestRule)

    @Test
    fun shows_user_details_content_without_error() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UserDetailsScreen(
                    uiState = UserDetailsUiState.Content(
                        user = leanne()
                    ),
                    onBackClick = {}
                )
            }
        }

        detailsPage.assertContent(
            name = "Leanne Graham",
            username = "@Bret",
            email = "Sincere@april.biz",
            phone = "1-770-736-8031 x56442",
            website = "hildegard.org",
            address = "Kulas Light, Apt. 556, Gwenborough, 92998-3874",
            company = "Romaguera-Crona"
        )
        detailsPage.assertErrorDoesNotExist()
    }

    @Test
    fun clicks_back_button_once() {
        var backClickedCount = 0

        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UserDetailsScreen(
                    uiState = UserDetailsUiState.Content(
                        user = leanne()
                    ),
                    onBackClick = {
                        backClickedCount++
                    }
                )
            }
        }

        assertEquals(0, backClickedCount)

        detailsPage.back()

        assertEquals(1, backClickedCount)
    }

    @Test
    fun shows_not_found_error_without_content() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UserDetailsScreen(
                    uiState = UserDetailsUiState.Error(
                        message = "User not found"
                    ),
                    onBackClick = {},
                )
            }
        }

        detailsPage.assertNotFoundError(
            title = "Cannot open user",
            message = "User not found"
        )
        detailsPage.assertContentDoesNotExist()
    }

    private fun leanne() = UserDetailsUi(
        id = 1,
        name = "Leanne Graham",
        username = "Bret",
        email = "Sincere@april.biz",
        phone = "1-770-736-8031 x56442",
        website = "hildegard.org",
        address = "Kulas Light, Apt. 556, Gwenborough, 92998-3874",
        company = "Romaguera-Crona"
    )
}