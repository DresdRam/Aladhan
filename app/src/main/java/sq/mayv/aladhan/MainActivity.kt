package sq.mayv.aladhan

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splashViewModel: SplashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]

        val channelId = "3025"
        val channelName = "aladhan"
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channel = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        notificationManager.createNotificationChannel(channel)

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