package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag

class UsersPage(
    private val composeTestRule: ComposeTestRule
) {

    fun assertUserAt(
        position: Int,
        name: String,
        email: String
    ) {
        composeTestRule.onNodeWithTag("User item at $position")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("User name at $position", useUnmergedTree = true)
            .assertTextEquals(name)

        composeTestRule.onNodeWithTag("User email at $position", useUnmergedTree = true)
            .assertTextEquals(email)
    }
}