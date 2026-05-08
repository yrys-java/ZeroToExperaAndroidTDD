package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ThemePreviews
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.common.component.UsersTopBar
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppBrand
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppBrandContainer
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersHeader(usersCount: Int) {
    UsersTopBar(
        title = stringResource(R.string.users_title),
        titleModifier = Modifier.testTag(UsersScreenTags.TEXT_USERS_TITLE),
        subtitle = stringResource(R.string.users_subtitle, usersCount),
        subtitleModifier = Modifier.testTag(UsersScreenTags.TEXT_USERS_SUBTITLE),
        modifier = Modifier
            .testTag(UsersScreenTags.COLUMN_USERS_HEADER),
        action = {
            Surface(
                color = AppBrandContainer,
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = usersCount.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = AppBrand,
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .testTag(UsersScreenTags.TEXT_USERS_COUNT)
                )
            }
        }
    )
}

@ThemePreviews
@Composable
private fun UsersHeaderPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersHeader(usersCount = 3)
    }
}