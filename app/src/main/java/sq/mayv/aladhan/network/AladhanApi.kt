package sq.mayv.aladhan.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query
import sq.mayv.aladhan.model.Response

interface AladhanApi {

    @GET("calendar/")
    suspend fun getCalendarPrayers(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("method") method: Int,
    ): Response<String>

}