package sq.mayv.aladhan.ui.screens.home.viewstate

import sq.mayv.aladhan.room.entity.relation.DateWithTimings

sealed interface TimingsViewState {
    data object Loading : TimingsViewState
    data class Success(val prayers: List<DateWithTimings>, val indexOfToday: Int) : TimingsViewState
    data object Failure : TimingsViewState
}