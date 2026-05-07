package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.state.UserDetailsUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsScreenTags
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsTestUiData
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserDetailsContent(
    user: UserDetailsUi,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        UserDetailsHeroCard(user = user)

        UserDetailsInfoCard(
            title = stringResource(R.string.user_details_section_contacts),
            rows = listOf(
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_email),
                    value = user.email,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_EMAIL
                ),
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_phone),
                    value = user.phone,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_PHONE
                ),
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_website),
                    value = user.website,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_WEBSITE
                )
            )
        )

        UserDetailsInfoCard(
            title = stringResource(R.string.user_details_section_address),
            rows = listOf(
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_home),
                    value = user.address,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_ADDRESS
                )
            )
        )

        UserDetailsInfoCard(
            title = stringResource(R.string.user_details_section_company),
            rows = listOf(
                UserDetailsInfoRow(
                    label = stringResource(R.string.user_details_label_name),
                    value = user.company,
                    testTag = UserDetailsScreenTags.TEXT_DETAILS_COMPANY
                )
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsContentPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserDetailsContent(user = UserDetailsTestUiData.leanne)
    }
}