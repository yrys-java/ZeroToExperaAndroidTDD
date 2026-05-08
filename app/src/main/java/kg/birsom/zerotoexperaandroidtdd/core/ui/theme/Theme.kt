package kg.birsom.zerotoexperaandroidtdd.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkBrand,
    onPrimary = DarkOnBrand,
    primaryContainer = DarkBrandContainer,
    onPrimaryContainer = DarkBrand,
    background = DarkBackground,
    onBackground = DarkTextPrimary,
    surface = DarkCardBackground,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = DarkTextSecondary,
    outline = DarkCardBorder,
    outlineVariant = DarkMutedIcon,
    tertiaryContainer = DarkOfflineContainer,
    onTertiaryContainer = DarkOfflineText
)

private val LightColorScheme = lightColorScheme(
    primary = LightBrand,
    onPrimary = LightOnBrand,
    primaryContainer = LightBrandContainer,
    onPrimaryContainer = LightBrand,
    background = LightBackground,
    onBackground = LightTextPrimary,
    surface = LightCardBackground,
    onSurface = LightTextPrimary,
    onSurfaceVariant = LightTextSecondary,
    outline = LightCardBorder,
    outlineVariant = LightMutedIcon,
    tertiaryContainer = LightOfflineContainer,
    onTertiaryContainer = LightOfflineText
)

@Composable
fun ZeroToExperaAndroidTDDTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+ and can be enabled by callers.
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}