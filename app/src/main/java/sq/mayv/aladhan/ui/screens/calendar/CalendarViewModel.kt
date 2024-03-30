package sq.mayv.aladhan.ui.screens.calendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aladhan.repository.RoomPrayerRepository
import sq.mayv.aladhan.ui.screens.calendar.viewstate.CalendarTimingViewState
import sq.mayv.aladhan.ui.screens.calendar.viewstate.CalendarViewState
import javax.inject.Inject

@HiltViewModel
class CalendarViewModel @Inject constructor(
    private val roomPrayerRepository: RoomPrayerRepository
) : ViewModel() {

    private val _viewState: MutableStateFlow<CalendarViewState> =
        MutableStateFlow(CalendarViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _timingsViewState: MutableStateFlow<CalendarTimingViewState> =
        MutableStateFlow(CalendarTimingViewState.Loading)
    val timingsViewState = _timingsViewState.asStateFlow()

    fun loadDates() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_viewState.value != CalendarViewState.Loading) {
                _viewState.value = CalendarViewState.Loading
            }

            val dates = roomPrayerRepository.getAllDates()

            _viewState.value = CalendarViewState.Success(
                dates = dates
            )
        }
    }

    fun loadClickedDateTimings(day: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            if (_timingsViewState.value != CalendarTimingViewState.Loading) {
                _timingsViewState.value = CalendarTimingViewState.Loading
            }

            val prayer = roomPrayerRepository.getPrayerByDate(
                day = day
            )

            _timingsViewState.value = CalendarTimingViewState.Success(
                timings = prayer.timings
            )
        }
    }

}