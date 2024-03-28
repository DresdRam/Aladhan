package sq.mayv.aladhan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import sq.mayv.aladhan.ui.navigation.AppNavigation
import sq.mayv.aladhan.ui.screens.splash.SplashViewModel
import sq.mayv.aladhan.ui.theme.AladhanTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }

        setContent {
            AladhanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    AppNavigation(splashViewModel.startDestination.value)
                }
            }
        }
    }
}