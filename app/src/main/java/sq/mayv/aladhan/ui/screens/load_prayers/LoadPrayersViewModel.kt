package sq.mayv.aladhan.ui.screens.load_prayers

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import sq.mayv.aladhan.data.PermissionEvent
import sq.mayv.aladhan.data.Resource
import sq.mayv.aladhan.model.Coordinates
import sq.mayv.aladhan.model.Prayer
import sq.mayv.aladhan.model.Response
import sq.mayv.aladhan.repository.PrayersRepository
import sq.mayv.aladhan.repository.RoomPrayerRepository
import sq.mayv.aladhan.ui.screens.load_prayers.viewstate.LoadPrayersViewState
import sq.mayv.aladhan.ui.screens.load_prayers.viewstate.PermissionViewState
import sq.mayv.aladhan.usecase.LocationUseCase
import sq.mayv.aladhan.util.PreferenceHelper.downloadEndDate
import sq.mayv.aladhan.util.PreferenceHelper.prayersDownloadIsNeeded
import javax.inject.Inject

@HiltViewModel
class LoadPrayersViewModel @Inject constructor(
    private val prayersRepository: PrayersRepository,
    private val roomPrayerRepository: RoomPrayerRepository,
    private val locationUseCase: LocationUseCase,
    private val preferences: SharedPreferences,
) : ViewModel() {

    private val _viewState: MutableStateFlow<LoadPrayersViewState> =
        MutableStateFlow(LoadPrayersViewState.Loading)
    val viewState = _viewState.asStateFlow()

    private val _permissionViewState: MutableStateFlow<PermissionViewState> =
        MutableStateFlow(PermissionViewState.CheckingPermissions)
    val permissionViewState = _permissionViewState.asStateFlow()

    private val _prayersData = MutableStateFlow(Resource<Response<List<Prayer>>>())

    fun getMonthlyPrayers() {
        viewModelScope.launch(Dispatchers.IO) {
            if (_viewState.value != LoadPrayersViewState.Loading) {
                _viewState.value = LoadPrayersViewState.Loading
            }

            locationUseCase.invoke().collect { coordinates ->
                if (_viewState.value != LoadPrayersViewState.Loading) {
                    _viewState.value = LoadPrayersViewState.Loading
                }

                _prayersData.value =
                    prayersRepository.getCalendarPrayers(
                        coordinates = coordinates ?: Coordinates(0.00, 0.00)
                    )

                val statusCode = _prayersData.value.data?.code ?: 0
                val isSuccessful = statusCode == 200 || statusCode == 201

                if (!isSuccessful) {
                    _viewState.value = LoadPrayersViewState.Failure(
                        _prayersData.value.exception.message ?: "Loading Failed!"
                    )
                } else {
                    _viewState.value =
                        LoadPrayersViewState.Loaded(_prayersData.value.data?.data ?: listOf(Prayer()))

                    val lastIndex = _prayersData.value.data!!.data.lastIndex
                    val lastDate = _prayersData.value.data!!.data[lastIndex].date.gregorian.date

                    preferences.downloadEndDate = lastDate
                }
            }
        }
    }

    fun storeTimings() {
        viewModelScope.launch(Dispatchers.IO) {
            roomPrayerRepository.deleteAll()
            roomPrayerRepository.storeMonthlyPrayers(_prayersData.value.data?.data ?: listOf())
            preferences.prayersDownloadIsNeeded = false // So the app opens on Home Screen.
            _viewState.value = LoadPrayersViewState.Stored
        }
    }

    fun handleViewState(event: PermissionEvent) {
        when (event) {
            PermissionEvent.Granted -> {
                _permissionViewState.value = PermissionViewState.GrantedPermissions
            }

            PermissionEvent.Revoked -> {
                _permissionViewState.value = PermissionViewState.RevokedPermissions
            }
        }
    }
}