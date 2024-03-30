package sq.mayv.aladhan.ui.screens.splash

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import sq.mayv.aladhan.ui.navigation.AppScreens
import sq.mayv.aladhan.util.PreferenceHelper.downloadEndDate
import sq.mayv.aladhan.util.PreferenceHelper.prayersDownloadIsNeeded
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(preferences: SharedPreferences) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(true)
    val isLoading: State<Boolean> = _isLoading

    private val _startDestination: MutableState<String> =
        mutableStateOf(AppScreens.route(AppScreens.LoadPrayersScreen))
    val startDestination: State<String> = _startDestination

    init {
        if(preferences.downloadEndDate != "") {
            val dateTime = Calendar.getInstance().time
            val formatter = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val currentDate = formatter.parse(formatter.format(dateTime))
            val endDate = formatter.parse(preferences.downloadEndDate)

            if(currentDate!! <= endDate) {
                if (!preferences.prayersDownloadIsNeeded) {
                    _startDestination.value = AppScreens.route(AppScreens.HomeScreen)
                }
            }
        }

        _isLoading.value = false
    }

}