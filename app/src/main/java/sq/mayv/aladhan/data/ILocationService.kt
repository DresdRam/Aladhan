package sq.mayv.aladhan.data

import kotlinx.coroutines.flow.Flow
import sq.mayv.aladhan.model.Coordinates

interface ILocationService {

    fun getLastKnownLocation(): Flow<Coordinates?>

}