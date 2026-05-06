package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags

class UsersPage(
    private val composeTestRule: ComposeTestRule
) {

    fun assertUserAt(
        position: Int,
        name: String,
        email: String
    ) {
        composeTestRule.onNodeWithTag(UsersScreenTags.cardUserItem(position))
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(UsersScreenTags.textUserName(position), useUnmergedTree = true)
            .assertTextEquals(name)

        composeTestRule.onNodeWithTag(UsersScreenTags.textUserEmail(position), useUnmergedTree = true)
            .assertTextEquals(email)
    }

    fun assertAvatarAt(
        position: Int,
        initials: String
    ) {
        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.imageUserAvatar(position),
            useUnmergedTree = true
        ).assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.textUserAvatarInitials(position),
            useUnmergedTree = true
        ).assertTextEquals(initials)
    }
}