package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UsersHeader(usersCount: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 20.dp, vertical = 18.dp)
            .testTag(UsersScreenTags.COLUMN_USERS_HEADER)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Users",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF101828),
                modifier = Modifier
                    .weight(1f)
                    .testTag(UsersScreenTags.TEXT_USERS_TITLE)
            )
            Surface(
                color = Color(0xFFEFF4FF),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = usersCount.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF3C5BF5),
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                        .testTag(UsersScreenTags.TEXT_USERS_COUNT)
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$usersCount teammates available",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFF667085),
            modifier = Modifier.testTag(UsersScreenTags.TEXT_USERS_SUBTITLE)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun UsersHeaderPreview() {
    ZeroToExperaAndroidTDDTheme {
        UsersHeader(usersCount = 3)
    }
}