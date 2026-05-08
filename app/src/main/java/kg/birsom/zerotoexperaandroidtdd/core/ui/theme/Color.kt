package kg.birsom.zerotoexperaandroidtdd.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

internal val LightBackground = Color(0xFFF6F7FB)
internal val LightCardBackground = Color.White
internal val LightCardBorder = Color(0xFFDDE4F0)
internal val LightTextPrimary = Color.Black
internal val LightTextSecondary = Color.Gray
internal val LightBrand = Color(0xFF3C5BF5)
internal val LightOnBrand = Color.White
internal val LightBrandContainer = Color(0xFFEFF4FF)
internal val LightMutedIcon = Color.LightGray
internal val LightOfflineContainer = Color(0xFFEFF8FF)
internal val LightOfflineText = Color(0xFF175CD3)

internal val DarkBackground = Color(0xFF10131A)
internal val DarkCardBackground = Color(0xFF181C24)
internal val DarkCardBorder = Color(0xFF2D3748)
internal val DarkTextPrimary = Color(0xFFF8FAFC)
internal val DarkTextSecondary = Color(0xFFA7B0C0)
internal val DarkBrand = Color(0xFFA9B8FF)
internal val DarkOnBrand = Color(0xFF10131A)
internal val DarkBrandContainer = Color(0xFF24305F)
internal val DarkMutedIcon = Color(0xFF596274)
internal val DarkOfflineContainer = Color(0xFF17324A)
internal val DarkOfflineText = Color(0xFF9BD5FF)

val AppBackground: Color
    @Composable
    get() = MaterialTheme.colorScheme.background

val AppCardBackground: Color
    @Composable
    get() = MaterialTheme.colorScheme.surface

val AppCardBorder: Color
    @Composable
    get() = MaterialTheme.colorScheme.outline

val AppTextPrimary: Color
    @Composable
    get() = MaterialTheme.colorScheme.onSurface

val AppTextSecondary: Color
    @Composable
    get() = MaterialTheme.colorScheme.onSurfaceVariant

val AppBrand: Color
    @Composable
    get() = MaterialTheme.colorScheme.primary

val AppOnBrand: Color
    @Composable
    get() = MaterialTheme.colorScheme.onPrimary

val AppBrandContainer: Color
    @Composable
    get() = MaterialTheme.colorScheme.primaryContainer

val AppMutedIcon: Color
    @Composable
    get() = MaterialTheme.colorScheme.outlineVariant

val AppOfflineContainer: Color
    @Composable
    get() = MaterialTheme.colorScheme.tertiaryContainer

val AppOfflineText: Color
    @Composable
    get() = MaterialTheme.colorScheme.onTertiaryContainer