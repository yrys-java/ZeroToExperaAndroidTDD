package kg.birsom.zerotoexperaandroidtdd

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import kg.birsom.zerotoexperaandroidtdd.app.UsersApp
import kg.birsom.zerotoexperaandroidtdd.ui.theme.ZeroToExperaAndroidTDDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ZeroToExperaAndroidTDDTheme {
                UsersApp()
            }
        }
    }
}