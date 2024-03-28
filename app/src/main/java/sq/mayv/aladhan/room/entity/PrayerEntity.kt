package sq.mayv.aladhan.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prayer")
data class PrayerEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "readable_date") val readableDate: String,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "month_number") val monthNumber: Int,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "week_day") val weekDay: String,
    @ColumnInfo(name = "fajr") val fajr: String,
    @ColumnInfo(name = "sunrise") val sunrise: String,
    @ColumnInfo(name = "dhuhr") val dhuhr: String,
    @ColumnInfo(name = "asr") val asr: String,
    @ColumnInfo(name = "maghrib") val maghrib: String,
    @ColumnInfo(name = "isha") val isha: String,
)
