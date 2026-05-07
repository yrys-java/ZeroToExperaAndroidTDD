package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.junit4.v2.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.UserDetailsScreen
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUiState
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDetailsScreenUiTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val detailsPage = UserDetailsPage(composeTestRule)

    @Test
    fun shows_user_details_content_without_loading_and_error() {
        composeTestRule.setContent {
            ZeroToExperaAndroidTDDTheme {
                UserDetailsScreen(
                    uiState = UserDetailsUiState.Content(
                        user = UserDetailsUi(
                            id = 1,
                            name = "Leanne Graham",
                            username = "Bret",
                            email = "Sincere@april.biz",
                            phone = "1-770-736-8031 x56442",
                            website = "hildegard.org",
                            address = "Kulas Light, Apt. 556, Gwenborough, 92998-3874",
                            company = "Romaguera-Crona"
                        )
                    ),
                    onBackClick = {},
                    onRetryClick = {}
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
        detailsPage.assertLoadingDoesNotExist()
        detailsPage.assertErrorDoesNotExist()
    }
}