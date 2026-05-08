package kg.birsom.zerotoexperaandroidtdd

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.graphics.toArgb
import kg.birsom.zerotoexperaandroidtdd.app.UsersApp
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.DarkBackground
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.LightBackground
import kg.birsom.zerotoexperaandroidtdd.core.ui.theme.ZeroToExperaAndroidTDDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                lightScrim = Color.TRANSPARENT,
                darkScrim = Color.TRANSPARENT
            ),
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim = LightBackground.toArgb(),
                darkScrim = DarkBackground.toArgb()
            )
        )
        setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersApp()
            }
        }
    }
}