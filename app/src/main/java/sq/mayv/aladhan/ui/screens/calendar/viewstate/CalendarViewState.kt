package sq.mayv.aladhan.ui.screens.calendar.viewstate

sealed interface CalendarViewState {
    data object Loading : CalendarViewState
    data object Success: CalendarViewState
    data object Failure : CalendarViewState
}