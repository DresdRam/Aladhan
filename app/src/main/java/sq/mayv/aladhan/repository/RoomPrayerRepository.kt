package sq.mayv.aladhan.repository

import sq.mayv.aladhan.model.Prayer
import sq.mayv.aladhan.room.dao.PrayerDao
import sq.mayv.aladhan.room.entity.DateEntity
import sq.mayv.aladhan.room.entity.TimingsEntity
import javax.inject.Inject

class RoomPrayerRepository @Inject constructor(
    private val dao: PrayerDao
) {

    suspend fun storeMonthlyPrayers(prayers: List<Prayer>) {

        for(prayer in prayers) {

            val isJumaa = prayer.date.gregorian.weekday.en == "Friday"

            val dateEntity =
                DateEntity(
                    readableDate = prayer.date.readable,
                    day = prayer.date.gregorian.day,
                    month = prayer.date.gregorian.month.en,
                    monthNumber = prayer.date.gregorian.month.number,
                    year = prayer.date.gregorian.year,
                    weekDay = prayer.date.gregorian.weekday.en
                )

            val dateId = dao.insertDate(date = dateEntity)
            val timings = TimingsEntity(
                dateId = dateId,
                fajr = prayer.timings.fajr,
                fajrReadable = "Fajr",
                sunrise = prayer.timings.sunrise,
                sunriseReadable = "Sunrise",
                dhuhr = prayer.timings.dhuhr,
                dhuhrReadable = if(isJumaa) "Jumaa" else "Dhuhr",
                asr = prayer.timings.asr,
                asrReadable = "Asr",
                maghrib = prayer.timings.maghrib,
                maghribReadable = "Maghrib",
                isha = prayer.timings.isha,
                ishaReadable = "Isha"
            )

            dao.insertTimings(timings)
        }

    }

    suspend fun getTodayId(day: Int, month: Int, year: Int) = dao.getTodayId(day, month, year)

    suspend fun getMonthlyPrayers() = dao.getAll()
}