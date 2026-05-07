package kg.birsom.zerotoexperaandroidtdd

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsScreenTags

class UserDetailsPage(
    private val composeTestRule: ComposeTestRule
) {

    fun assertContent(
        name: String,
        username: String,
        email: String,
        phone: String,
        website: String,
        address: String,
        company: String
    ) {
        composeTestRule.onNodeWithTag(UserDetailsScreenTags.BUTTON_DETAILS_BACK)
            .assertIsDisplayed()

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_NAME,
            useUnmergedTree = true
        ).assertTextEquals(name)

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_USERNAME,
            useUnmergedTree = true
        ).assertTextEquals(username)

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_EMAIL,
            useUnmergedTree = true
        ).assertTextEquals(email)

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_PHONE,
            useUnmergedTree = true
        ).assertTextEquals(phone)

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_WEBSITE,
            useUnmergedTree = true
        ).assertTextEquals(website)

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_ADDRESS,
            useUnmergedTree = true
        ).assertTextEquals(address)

        composeTestRule.onNodeWithTag(
            testTag = UserDetailsScreenTags.TEXT_DETAILS_COMPANY,
            useUnmergedTree = true
        ).assertTextEquals(company)
    }

    fun assertLoadingDoesNotExist() {
        composeTestRule.onNodeWithTag(UserDetailsScreenTags.BOX_DETAILS_LOADING)
            .assertDoesNotExist()
    }

    fun assertErrorDoesNotExist() {
        composeTestRule.onNodeWithTag(UserDetailsScreenTags.CARD_DETAILS_ERROR)
            .assertDoesNotExist()
    }

    fun back() {
        composeTestRule.onNodeWithTag(UserDetailsScreenTags.BUTTON_DETAILS_BACK)
            .performClick()

    }
}