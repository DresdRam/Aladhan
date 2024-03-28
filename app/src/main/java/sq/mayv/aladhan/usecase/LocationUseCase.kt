package sq.mayv.aladhan.usecase

import kotlinx.coroutines.flow.Flow
import sq.mayv.aladhan.data.ILocationService
import sq.mayv.aladhan.model.Coordinates
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationService: ILocationService
) {
    operator fun invoke(): Flow<Coordinates?> = locationService.getLastKnownLocation()
}