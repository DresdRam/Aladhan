package sq.mayv.aladhan.ui.screens.calendar

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    preferences: SharedPreferences
): ViewModel() {
}