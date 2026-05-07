package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.detail.screen.element

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppBrand
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.AppOnBrand
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun DetailsAvatar(
    name: String
) {
    Box(
        modifier = Modifier
            .size(82.dp)
            .clip(CircleShape)
            .background(AppBrand),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name.initials(),
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = AppOnBrand
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
private fun DetailsAvatarPreview() {
    ZeroToExperaAndroidTDDTheme {
        DetailsAvatar(name = "Leanne Graham")
    }
}

@Preview(showBackground = true)
@Composable
private fun SingleNameDetailsAvatarPreview() {
    ZeroToExperaAndroidTDDTheme {
        DetailsAvatar(name = "Leanne")
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyDetailsAvatarPreview() {
    ZeroToExperaAndroidTDDTheme {
        DetailsAvatar(name = "")
    }
}