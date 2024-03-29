package sq.mayv.aladhan.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import sq.mayv.aladhan.room.entity.DateEntity
import sq.mayv.aladhan.room.entity.TimingsEntity
import sq.mayv.aladhan.room.entity.relation.DateWithTimings

@Dao
interface PrayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDate(date: DateEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTimings(timings: TimingsEntity): Long

    @Query("SELECT * FROM date ORDER BY id ASC")
    suspend fun getAll(): List<DateWithTimings>

    @Query("SELECT id FROM date WHERE day = :day AND month_number = :month AND year = :year")
    suspend fun getTodayId(day: Int, month: Int, year: Int): Long

    @Query("DELETE FROM date")
    suspend fun deleteAllDates()

    @Query("DELETE FROM timings")
    suspend fun deleteAllTimings()

}