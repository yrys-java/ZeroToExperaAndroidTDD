package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ThemePreviews
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.UiText
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.text.asString
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.TestUiData
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppCardBackground
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppCardBorder
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextPrimary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextSecondary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersError(
    message: UiText,
    showBackButton: Boolean,
    onRetryClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.testTag(UsersScreenTags.CARD_USERS_ERROR),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = AppCardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            border = BorderStroke(1.dp, AppCardBorder)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.users_error_title),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = AppTextPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(UsersScreenTags.TEXT_USERS_ERROR_TITLE)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = message.asString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = AppTextSecondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.testTag(UsersScreenTags.TEXT_USERS_ERROR_MESSAGE)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {
                    Button(
                        onClick = onRetryClick,
                        modifier = Modifier.testTag(UsersScreenTags.BUTTON_USERS_RETRY)
                    ) {
                        Text(text = stringResource(R.string.users_retry))
                    }

                    if (showBackButton) {
                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = onBackClick,
                            modifier = Modifier.testTag(UsersScreenTags.BUTTON_USERS_BACK)
                        ) {
                            Text(text = stringResource(R.string.users_back))
                        }
                    }
                }
            }
        }
    }
}

@ThemePreviews
@Composable
private fun UsersErrorPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersError(
            message = TestUiData.usersError.message,
            showBackButton = false,
            onRetryClick = {},
            onBackClick = {}
        )
    }
}

@ThemePreviews
@Composable
private fun UsersErrorPreviewWithCache() {
    ZeroToExperaAndroidTDDTheme {
        UsersError(
            message = TestUiData.usersErrorWithCache.message,
            showBackButton = true,
            onRetryClick = {},
            onBackClick = {}
        )
    }
}