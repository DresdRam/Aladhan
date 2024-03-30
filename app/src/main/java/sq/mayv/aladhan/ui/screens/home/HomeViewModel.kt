package sq.mayv.aladhan.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aladhan.model.NextPrayer
import sq.mayv.aladhan.repository.RoomPrayerRepository
import sq.mayv.aladhan.room.entity.TimingsEntity
import sq.mayv.aladhan.ui.screens.home.viewstate.NextPrayerViewState
import sq.mayv.aladhan.ui.screens.home.viewstate.TimingsViewState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val roomPrayerRepository: RoomPrayerRepository
) : ViewModel() {

    private val _timingsViewState: MutableStateFlow<TimingsViewState> =
        MutableStateFlow(TimingsViewState.Loading)
    val timingsViewState = _timingsViewState.asStateFlow()

    private val _nextPrayerViewState: MutableStateFlow<NextPrayerViewState> =
        MutableStateFlow(NextPrayerViewState.Loading)
    val nextPrayerViewState = _nextPrayerViewState.asStateFlow()

    private val _countDown = MutableStateFlow(1L)

    private val _remainingTime = MutableStateFlow("")
    val remainingTime: StateFlow<String> = _remainingTime

    fun loadTimings() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_timingsViewState.value != TimingsViewState.Loading) {
                _timingsViewState.value = TimingsViewState.Loading
            }

            val calendar = Calendar.getInstance()
            val prayers = roomPrayerRepository.getMonthlyPrayers()

            val todayPrayer = roomPrayerRepository.getPrayerByDate(
                day = calendar.get(Calendar.DAY_OF_MONTH)
            )

            _timingsViewState.value = TimingsViewState.Success(
                prayers = prayers,
                indexOfToday = prayers.indexOf(todayPrayer)
            )
        }
    }

    fun loadNextPrayer() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_nextPrayerViewState.value != NextPrayerViewState.Loading) {
                _nextPrayerViewState.value = NextPrayerViewState.Loading
            }

            val calendar = Calendar.getInstance()

            val todayPrayer = roomPrayerRepository.getPrayerByDate(
                day = calendar.get(Calendar.DAY_OF_MONTH)
            )

            val nextPrayer = getNextPrayer(timings = todayPrayer.timings)

            if (!nextPrayer.nextPrayerExist) {
                _nextPrayerViewState.value = NextPrayerViewState.NoNextPrayer
            } else {
                handleCountDown(nextPrayer)
                _nextPrayerViewState.value = NextPrayerViewState.Success(nextPrayer = nextPrayer)
            }
        }
    }

    private fun handleCountDown(nextPrayer: NextPrayer) {
        viewModelScope.launch(Dispatchers.IO) {
            if (nextPrayer.nextPrayerExist) {
                while (_countDown.value >= 0L) {
                    if (_countDown.value > 0L) {
                        val dateTime = Calendar.getInstance().time
                        val formatter = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)

                        val currentTime = formatter.parse(formatter.format(dateTime))
                        val nextPrayerTime = formatter.parse(nextPrayer.timing.plus(":00"))

                        // Difference in seconds between current time and next prayer time.
                        _countDown.value = (nextPrayerTime!!.time - currentTime!!.time) / 1000

                        val hours = _countDown.value / 3600
                        val minutes = (_countDown.value % 3600) / 60
                        val seconds = _countDown.value % 60

                        _remainingTime.value = "$hours:$minutes:$seconds"

                        delay(1000)
                    } else {
                        _countDown.value = 1L
                        loadNextPrayer()
                        break
                    }
                }
            }
        }
    }

    private fun getNextPrayer(timings: TimingsEntity): NextPrayer {
        val dateTime = Calendar.getInstance().time
        val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        val currentTime = formatter.format(dateTime)

        return if (currentTime < timings.fajr) {
            NextPrayer(prayerReadable = timings.fajrReadable, timing = timings.fajr)
        } else if (currentTime < timings.sunrise) {
            NextPrayer(prayerReadable = timings.sunriseReadable, timing = timings.sunrise)
        } else if (currentTime < timings.dhuhr) {
            NextPrayer(prayerReadable = timings.dhuhrReadable, timing = timings.dhuhr)
        } else if (currentTime < timings.asr) {
            NextPrayer(prayerReadable = timings.asrReadable, timing = timings.asr)
        } else if (currentTime < timings.maghrib) {
            NextPrayer(prayerReadable = timings.maghribReadable, timing = timings.maghrib)
        } else if (currentTime < timings.isha) {
            NextPrayer(prayerReadable = timings.ishaReadable, timing = timings.isha)
        } else {
            NextPrayer(nextPrayerExist = false)
        }

    }

}