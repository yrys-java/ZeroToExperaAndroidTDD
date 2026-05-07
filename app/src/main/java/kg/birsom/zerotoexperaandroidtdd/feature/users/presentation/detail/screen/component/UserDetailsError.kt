package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.asString
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsScreenTags
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.test_data.UserDetailsTestUiData
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppCardBackground
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextPrimary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextSecondary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserDetailsError(
    message: UiText,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.testTag(UserDetailsScreenTags.CARD_DETAILS_ERROR),
            shape = RoundedCornerShape(28.dp),
            colors = CardDefaults.cardColors(containerColor = AppCardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(28.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.user_details_error_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = AppTextPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_ERROR_TITLE)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message.asString(),
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppTextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(UserDetailsScreenTags.TEXT_DETAILS_ERROR_MESSAGE)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UserDetailsErrorPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserDetailsError(message = UserDetailsTestUiData.detailsError.message)
    }
}