package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
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

    fun assertLoading() {
        composeTestRule.onNodeWithTag(UsersScreenTags.BOX_USERS_LOADING)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.PROGRESS_USERS_LOADING,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    fun assertUserDoesNotExist(position: Int) {
        composeTestRule.onNodeWithTag(UsersScreenTags.cardUserItem(position))
            .assertDoesNotExist()
    }

    fun assertError(message: String) {
        composeTestRule.onNodeWithTag(UsersScreenTags.CARD_USERS_ERROR)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.TEXT_USERS_ERROR_MESSAGE,
            useUnmergedTree = true
        ).assertTextEquals(message)

        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.BUTTON_USERS_RETRY,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    fun assertBack() {
        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.BUTTON_USERS_BACK,
            useUnmergedTree = true
        ).assertIsDisplayed()
    }

    fun assertBackDoesNotExist() {
        composeTestRule.onNodeWithTag(UsersScreenTags.BUTTON_USERS_BACK)
            .assertDoesNotExist()
    }

    fun retry() {
        composeTestRule.onNodeWithTag(UsersScreenTags.BUTTON_USERS_RETRY)
            .performClick()
    }

    fun back() {
        composeTestRule.onNodeWithTag(UsersScreenTags.BUTTON_USERS_BACK)
            .performClick()
    }

    fun pullToRefresh() {
        composeTestRule.onNodeWithTag(UsersScreenTags.LAZY_COLUMN_USERS)
            .performTouchInput {
                swipeDown(
                    startY = centerY,
                    endY = bottom,
                    durationMillis = 1_000
                )
            }
    }

    fun assertLoadingDoesNotExist() {
        composeTestRule.onNodeWithTag(UsersScreenTags.BOX_USERS_LOADING)
            .assertDoesNotExist()
    }

    fun assertOfflineBanner(message: String) {
        composeTestRule.onNodeWithTag(UsersScreenTags.BOX_USERS_OFFLINE_BANNER)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            testTag = UsersScreenTags.TEXT_USERS_OFFLINE_BANNER,
            useUnmergedTree = true
        ).assertTextEquals(message)
    }

    fun assertErrorDoesNotExist() {
        composeTestRule.onNodeWithTag(UsersScreenTags.CARD_USERS_ERROR)
            .assertDoesNotExist()
    }

    fun clickUserAt(position: Int) {
        composeTestRule.onNodeWithTag(UsersScreenTags.cardUserItem(position))
            .performClick()
    }
}