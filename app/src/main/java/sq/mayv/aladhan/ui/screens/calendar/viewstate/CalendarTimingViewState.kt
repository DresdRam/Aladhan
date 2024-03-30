package sq.mayv.aladhan.ui.screens.calendar.viewstate

import sq.mayv.aladhan.room.entity.TimingsEntity

sealed interface CalendarTimingViewState {
    data object Loading : CalendarTimingViewState
    data class Success(val timings: TimingsEntity) : CalendarTimingViewState
    data object Failure : CalendarTimingViewState
}