package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextOverflow
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ThemePreviews
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.R
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.element.UserAvatar
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.TestUiData
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppCardBackground
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppCardBorder
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppMutedIcon
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextPrimary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppTextSecondary
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserListItem(
    user: UserUi,
    position: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .testTag(UsersScreenTags.cardUserItem(position)),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = AppCardBackground),
        elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
        border = BorderStroke(1.dp, AppCardBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .testTag(UsersScreenTags.rowUserItem(position)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserAvatar(
                name = user.name,
                position = position
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp)
                    .testTag(UsersScreenTags.columnUserItemContent(position))
            ) {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = AppTextPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(UsersScreenTags.textUserName(position))
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = user.email,
                    style = MaterialTheme.typography.bodyMedium,
                    color = AppTextSecondary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.testTag(UsersScreenTags.textUserEmail(position))
                )
            }

            Text(
                text = stringResource(R.string.users_item_arrow),
                style = MaterialTheme.typography.headlineSmall,
                color = AppMutedIcon,
                modifier = Modifier.testTag(UsersScreenTags.iconUserArrow(position))
            )
        }
    }
}

@ThemePreviews
@Composable
private fun UserListItemPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserListItem(
            user = TestUiData.users.first(),
            position = 0,
            onClick = {}
        )
    }
}