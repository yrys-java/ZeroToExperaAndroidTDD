package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.element

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun UserAvatar(
    name: String,
    position: Int,
    size: Dp = 58.dp
) {
    Box(
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(Color(0xFF3C5BF5))
            .testTag(UsersScreenTags.imageUserAvatar(position)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.initials(),
            color = Color.White,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.testTag(UsersScreenTags.textUserAvatarInitials(position))
        )
    }
}

private fun String.initials(): String {
    return trim()
        .split(" ")
        .filter { it.isNotBlank() }
        .take(2)
        .joinToString(separator = "") { namePart ->
            namePart.first().uppercase()
        }
        .ifEmpty { "?" }
}

@Preview(showBackground = true)
@Composable
private fun UserAvatarPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserAvatar(
            name = "Leanne Graham",
            position = 0
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NameUserAvatarPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserAvatar(
            name = "Leanne",
            position = 0
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyUserAvatarPreview() {
    ZeroToExperaAndroidTDDTheme {
        UserAvatar(
            name = "",
            position = 0
        )
    }
}