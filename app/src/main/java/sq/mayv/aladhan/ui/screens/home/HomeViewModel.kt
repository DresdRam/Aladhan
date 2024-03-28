package sq.mayv.aladhan.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aladhan.repository.RoomPrayerRepository
import sq.mayv.aladhan.ui.screens.home.viewstate.HomeViewState
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomPrayerRepository: RoomPrayerRepository
) : ViewModel() {

    private val _viewState: MutableStateFlow<HomeViewState> =
        MutableStateFlow(HomeViewState.Loading)
    val viewState = _viewState.asStateFlow()

    fun loadTimings() {
        viewModelScope.launch(Dispatchers.IO) {
            _viewState.value = HomeViewState.Loading

            val calendar = Calendar.getInstance()
            val prayers = roomPrayerRepository.getMonthlyPrayers()
            val todayId = roomPrayerRepository.getTodayId(
                day = calendar.get(Calendar.DAY_OF_MONTH),
                month = calendar.get(Calendar.MONTH) + 1, // +1 Because months start from index 0 so January is 0
                year = calendar.get(Calendar.YEAR)
            )

            _viewState.value = HomeViewState.Success(prayers = prayers, todayId = todayId)
        }
    }
}