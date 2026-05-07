package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextSecondary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(UsersScreenTags.BOX_USERS_LOADING),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.testTag(UsersScreenTags.COLUMN_USERS_LOADING)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.testTag(UsersScreenTags.PROGRESS_USERS_LOADING)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.users_loading),
                style = MaterialTheme.typography.bodyMedium,
                color = AppTextSecondary,
                modifier = Modifier.testTag(UsersScreenTags.TEXT_USERS_LOADING)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersLoadingPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersLoading()
    }
}