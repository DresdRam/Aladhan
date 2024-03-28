package sq.mayv.aladhan.ui.screens.home.viewstate

sealed interface HomeViewState {
    data object Loading : HomeViewState
    data object Success: HomeViewState
    data object Failure : HomeViewState
}