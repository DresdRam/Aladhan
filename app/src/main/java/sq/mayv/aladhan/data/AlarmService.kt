package sq.mayv.aladhan.data

import android.os.Build
import android.util.Log
import sq.mayv.aladhan.model.Alarm
import sq.mayv.aladhan.model.Prayer
import sq.mayv.aladhan.util.AlarmScheduler
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class AlarmService @Inject constructor(
    private val alarmScheduler: AlarmScheduler
) {

    fun schedulePrayerAlarms(prayer: Prayer) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val dateTime = Calendar.getInstance().time
            val formatter = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH)
            val currentDate = formatter.parse(formatter.format(dateTime))!!
            val alarms = listOf<Alarm>().toMutableList()

            val fajr = prayer.date.gregorian.date.plus(" ").plus(prayer.timings.fajr.take(5)).plus(":00")
            val sunrise = prayer.date.gregorian.date.plus(" ").plus(prayer.timings.sunrise.take(5)).plus(":00")
            val dhuhr = prayer.date.gregorian.date.plus(" ").plus(prayer.timings.dhuhr.take(5)).plus(":00")
            val asr = prayer.date.gregorian.date.plus(" ").plus(prayer.timings.asr.take(5)).plus(":00")
            val maghrib = prayer.date.gregorian.date.plus(" ").plus(prayer.timings.maghrib.take(5)).plus(":00")
            val isha = prayer.date.gregorian.date.plus(" ").plus(prayer.timings.isha.take(5)).plus(":00")

            if (currentDate < formatter.parse(fajr)) {
                alarms.add(
                    Alarm(
                        LocalDateTime.of(
                            prayer.date.gregorian.year.toInt(),
                            prayer.date.gregorian.month.number,
                            prayer.date.gregorian.day.toInt(),
                            getHoursFromTime(prayer.timings.fajr),
                            getMinutesFromTime(prayer.timings.fajr),
                            0
                        ),
                        "It is Fajr Prayer Time."
                    )
                )
            }

            if (currentDate < formatter.parse(sunrise)) {
                alarms.add(
                    Alarm(
                        LocalDateTime.of(
                            prayer.date.gregorian.year.toInt(),
                            prayer.date.gregorian.month.number,
                            prayer.date.gregorian.day.toInt(),
                            getHoursFromTime(prayer.timings.sunrise),
                            getMinutesFromTime(prayer.timings.sunrise),
                            0
                        ),
                        "It is Sunrise Prayer Time."
                    )
                )
            }

            if (currentDate < formatter.parse(dhuhr)) {
                alarms.add(
                    Alarm(
                        LocalDateTime.of(
                            prayer.date.gregorian.year.toInt(),
                            prayer.date.gregorian.month.number,
                            prayer.date.gregorian.day.toInt(),
                            getHoursFromTime(prayer.timings.dhuhr),
                            getMinutesFromTime(prayer.timings.dhuhr),
                            0
                        ),
                        "It is Dhuhr Prayer Time."
                    )
                )
            }

            if (currentDate < formatter.parse(asr)) {
                alarms.add(
                    Alarm(
                        LocalDateTime.of(
                            prayer.date.gregorian.year.toInt(),
                            prayer.date.gregorian.month.number,
                            prayer.date.gregorian.day.toInt(),
                            getHoursFromTime(prayer.timings.asr),
                            getMinutesFromTime(prayer.timings.asr),
                            0
                        ),
                        "It is Asr Prayer Time."
                    )
                )
            }

            if (currentDate < formatter.parse(maghrib)) {
                alarms.add(
                    Alarm(
                        LocalDateTime.of(
                            prayer.date.gregorian.year.toInt(),
                            prayer.date.gregorian.month.number,
                            prayer.date.gregorian.day.toInt(),
                            getHoursFromTime(prayer.timings.maghrib),
                            getMinutesFromTime(prayer.timings.maghrib),
                            0
                        ),
                        "It is Maghrib Prayer Time."
                    )
                )
            }

            if (currentDate < formatter.parse(isha)) {
                alarms.add(
                    Alarm(
                        LocalDateTime.of(
                            prayer.date.gregorian.year.toInt(),
                            prayer.date.gregorian.month.number,
                            prayer.date.gregorian.day.toInt(),
                            getHoursFromTime(prayer.timings.isha),
                            getMinutesFromTime(prayer.timings.isha),
                            0
                        ),
                        "It is Isha Prayer Time."
                    )
                )
            }

            alarmScheduler.scheduleMultiple(alarms)
        }
    }

    private fun getHoursFromTime(time: String): Int = time.take(5).split(":")[0].toInt()

    private fun getMinutesFromTime(time: String): Int = time.take(5).split(":")[0].toInt()

}