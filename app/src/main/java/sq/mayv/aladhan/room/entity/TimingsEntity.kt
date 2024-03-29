package sq.mayv.aladhan.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timings")
data class TimingsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "date_id") val dateId: Long,
    @ColumnInfo(name = "fajr") val fajr: String,
    @ColumnInfo(name = "fajr_readable") val fajrReadable: String,
    @ColumnInfo(name = "sunrise") val sunrise: String,
    @ColumnInfo(name = "sunrise_readable") val sunriseReadable: String,
    @ColumnInfo(name = "dhuhr") val dhuhr: String,
    @ColumnInfo(name = "dhuhr_readable") val dhuhrReadable: String,
    @ColumnInfo(name = "asr") val asr: String,
    @ColumnInfo(name = "asr_readable") val asrReadable: String,
    @ColumnInfo(name = "maghrib") val maghrib: String,
    @ColumnInfo(name = "maghrib_readable") val maghribReadable: String,
    @ColumnInfo(name = "isha") val isha: String,
    @ColumnInfo(name = "isha_readable") val ishaReadable: String,
)
