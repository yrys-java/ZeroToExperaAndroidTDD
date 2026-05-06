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
        composeTestRule.onNodeWithTag("card_users_item_$position")
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag("text_users_item_name_$position", useUnmergedTree = true)
            .assertTextEquals(name)

        composeTestRule.onNodeWithTag("text_users_item_email_$position", useUnmergedTree = true)
            .assertTextEquals(email)
    }
}