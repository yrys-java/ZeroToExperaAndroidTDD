package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.state.UserUi
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.TestUiData
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersContent(
    users: List<UserUi>,
    offline: Boolean,
    onUserClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .testTag(UsersScreenTags.COLUMN_USERS_CONTENT)
    ) {
        UsersHeader(usersCount = users.size)

        if (offline) {
            OfflineBanner()
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag(UsersScreenTags.LAZY_COLUMN_USERS),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 12.dp,
                end = 16.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            itemsIndexed(users) { index, user ->
                UserListItem(
                    user = user,
                    position = index,
                    onClick = { onUserClick(user.id) }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersContentPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersContent(
            users = TestUiData.users,
            offline = false,
            onUserClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersOfflineContentPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersContent(
            users = TestUiData.users,
            offline = true,
            onUserClick = {}
        )
    }
}