package sq.mayv.aladhan.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "date")
data class DateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "readable_date") val readableDate: String,
    @ColumnInfo(name = "day") val day: String,
    @ColumnInfo(name = "month") val month: String,
    @ColumnInfo(name = "month_number") val monthNumber: Int,
    @ColumnInfo(name = "year") val year: String,
    @ColumnInfo(name = "week_day") val weekDay: String,
)
