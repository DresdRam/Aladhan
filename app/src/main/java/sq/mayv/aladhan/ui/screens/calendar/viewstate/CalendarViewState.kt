package sq.mayv.aladhan.ui.screens.calendar.viewstate

import sq.mayv.aladhan.room.entity.DateEntity

sealed interface CalendarViewState {
    data object Loading : CalendarViewState
    data class Success(val dates: List<DateEntity>): CalendarViewState
    data object Failure : CalendarViewState
}