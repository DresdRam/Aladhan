package sq.mayv.aladhan.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import sq.mayv.aladhan.room.entity.PrayerEntity

@Dao
interface PrayerDao {

    @Insert
    suspend fun insert(prayer: PrayerEntity)

    @Query("SELECT * FROM prayer ORDER BY id ASC")
    suspend fun getAll(): List<PrayerEntity>

    @Query("SELECT id FROM prayer WHERE day = :day AND month_number = :month AND year = :year")
    suspend fun getTodayId(day: Int, month: Int, year: Int): Int

    @Query("DELETE FROM prayer")
    suspend fun deleteAll()

}