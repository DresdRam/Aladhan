package sq.mayv.aladhan.ui.screens.home.viewstate

import sq.mayv.aladhan.room.entity.PrayerEntity

sealed interface HomeViewState {
    data object Loading : HomeViewState
    data class Success(val prayers: List<PrayerEntity>, val todayId: Int) : HomeViewState
    data object Failure : HomeViewState
}