package kg.birsom.zerotoexperaandroidtdd.core.ui.theme

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFF6F7FB,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    name = "Dark",
    showBackground = true,
    backgroundColor = 0xFF10131A,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class ThemePreviews