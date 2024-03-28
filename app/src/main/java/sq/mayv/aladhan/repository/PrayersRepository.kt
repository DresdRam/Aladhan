package sq.mayv.aladhan.repository

import sq.mayv.aladhan.data.Resource
import sq.mayv.aladhan.model.Response
import sq.mayv.aladhan.network.AladhanApi
import javax.inject.Inject

class PrayersRepository @Inject constructor(
    private val api: AladhanApi
) {

    suspend fun getCalendarPrayers(): Resource<Response<String>> {

        val resource = Resource<Response<String>>()

        try {

            val result = api.getCalendarPrayers(
                latitude = 0.00,
                longitude = 0.00,
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