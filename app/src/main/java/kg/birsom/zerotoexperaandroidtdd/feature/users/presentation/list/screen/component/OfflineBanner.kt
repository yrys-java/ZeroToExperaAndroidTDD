package kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.screen.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kg.birsom.zerotoexperaandroidtdd.feature.users.presentation.list.test_data.UsersScreenTags
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

@Composable
fun OfflineBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .testTag(UsersScreenTags.BOX_USERS_OFFLINE_BANNER),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEFF8FF))
    ) {
        Text(
            text = "Offline mode: cached data",
            color = Color(0xFF175CD3),
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .testTag(UsersScreenTags.TEXT_USERS_OFFLINE_BANNER)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun OfflineBannerPreview() {
    ZeroToExperaAndroidTDDTheme {
        Box(
            modifier = Modifier.background(Color(0xFFF6F7FB))
        ) {
            OfflineBanner()
        }
    }
}