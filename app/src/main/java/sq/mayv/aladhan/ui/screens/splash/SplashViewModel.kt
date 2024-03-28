package sq.mayv.aladhan.ui.screens.splash

import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sq.mayv.aladhan.ui.navigation.AppScreens
import sq.mayv.aladhan.util.PreferenceHelper.prayersDownloadIsNeeded
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(preferences: SharedPreferences) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> =
        mutableStateOf(AppScreens.route(AppScreens.LoadPrayersScreen))
    val startDestination: State<String> = _startDestination

    init {
        if (!preferences.prayersDownloadIsNeeded) {
            _startDestination.value = AppScreens.route(AppScreens.HomeScreen)
        } else {
            //Todo some extra logic for checking if prayers needs to download the new month timings.
            //preferences.prayersDownloadIsNeeded = false
        }

        _isLoading.value = false
    }

}