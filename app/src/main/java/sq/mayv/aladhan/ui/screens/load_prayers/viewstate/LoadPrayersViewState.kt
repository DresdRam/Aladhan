package sq.mayv.aladhan.ui.screens.load_prayers.viewstate

import sq.mayv.aladhan.model.Prayer

sealed interface LoadPrayersViewState {
    data object Loading : LoadPrayersViewState
    data class Loaded(val prayers: List<Prayer>) : LoadPrayersViewState
    data object Stored : LoadPrayersViewState
    data class Failure(val message: String) : LoadPrayersViewState
}