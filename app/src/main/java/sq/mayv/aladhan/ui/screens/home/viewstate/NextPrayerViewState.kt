package sq.mayv.aladhan.ui.screens.home.viewstate

import sq.mayv.aladhan.model.NextPrayer

sealed interface NextPrayerViewState {
    data object Loading : NextPrayerViewState
    data class Success(val nextPrayer: NextPrayer) : NextPrayerViewState
    data object NoNextPrayer : NextPrayerViewState
}