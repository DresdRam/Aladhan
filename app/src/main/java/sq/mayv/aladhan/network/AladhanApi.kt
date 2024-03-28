package sq.mayv.aladhan.network

import retrofit2.http.GET
import retrofit2.http.Query
import sq.mayv.aladhan.model.Prayer
import sq.mayv.aladhan.model.Response

interface AladhanApi {

    @GET("v1/calendar")
    suspend fun getCalendarPrayers(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("method") method: Int,
    ): Response<List<Prayer>>

}