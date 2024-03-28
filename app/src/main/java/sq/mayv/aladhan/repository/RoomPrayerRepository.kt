package sq.mayv.aladhan.repository

import sq.mayv.aladhan.model.Prayer
import sq.mayv.aladhan.room.dao.PrayerDao
import sq.mayv.aladhan.room.entity.PrayerEntity
import javax.inject.Inject

class RoomPrayerRepository @Inject constructor(
    private val dao: PrayerDao
) {

    suspend fun storeMonthlyPrayers(prayers: List<Prayer>) {

        for(prayer in prayers) {
            val prayerEntity =
                PrayerEntity(
                    readableDate = prayer.date.readable,
                    day = prayer.date.gregorian.day,
                    month = prayer.date.gregorian.month.en,
                    monthNumber = prayer.date.gregorian.month.number,
                    year = prayer.date.gregorian.year,
                    weekDay = prayer.date.gregorian.weekday.en,
                    fajr = prayer.timings.fajr,
                    sunrise = prayer.timings.sunrise,
                    dhuhr = prayer.timings.dhuhr,
                    asr = prayer.timings.asr,
                    maghrib = prayer.timings.maghrib,
                    isha = prayer.timings.isha
                )

            dao.insert(prayer = prayerEntity)
        }

    }

    suspend fun getTodayId(day: Int, month: Int, year: Int) = dao.getTodayId(day, month, year)

    suspend fun getMonthlyPrayers() = dao.getAll()
}