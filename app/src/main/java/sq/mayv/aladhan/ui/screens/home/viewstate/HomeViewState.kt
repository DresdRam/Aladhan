package sq.mayv.aladhan.ui.screens.home.viewstate

import sq.mayv.aladhan.room.entity.relation.DateWithTimings

sealed interface HomeViewState {
    data object Loading : HomeViewState
    data class Success(val prayers: List<DateWithTimings>, val todayId: Long) : HomeViewState
    data object Failure : HomeViewState
}