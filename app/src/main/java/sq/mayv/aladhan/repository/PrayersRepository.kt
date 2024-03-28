package sq.mayv.aladhan.repository

import sq.mayv.aladhan.data.Resource
import sq.mayv.aladhan.model.Coordinates
import sq.mayv.aladhan.model.Prayer
import sq.mayv.aladhan.model.Response
import sq.mayv.aladhan.network.AladhanApi
import javax.inject.Inject

class PrayersRepository @Inject constructor(
    private val api: AladhanApi
) {

    suspend fun getCalendarPrayers(coordinates: Coordinates): Resource<Response<List<Prayer>>> {

        val resource = Resource<Response<List<Prayer>>>()

        try {

            val result = api.getCalendarPrayers(
                latitude = coordinates.latitude,
                longitude = coordinates.longitude,
                method = 5
            )

            if (result.code == 200) {
                resource.data = result
            } else {
                resource.exception = when (result.code) {
                    400 -> Exception("Bad Request Exception!")
                    401 -> Exception("Unauthenticated!")
                    404 -> Exception("There is no connection to the server!")
                    409 -> Exception("Unauthorized!")
                    else -> Exception(result.status)
                }
            }
        } catch (exception: Exception) {
            resource.exception = exception
        }

        return resource
    }
}