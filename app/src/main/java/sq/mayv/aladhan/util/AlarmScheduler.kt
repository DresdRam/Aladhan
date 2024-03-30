package sq.mayv.aladhan.util

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import sq.mayv.aladhan.broadcast.AlarmBroadCastReceiver
import sq.mayv.aladhan.data.IAlarmScheduler
import sq.mayv.aladhan.model.Alarm
import java.time.ZoneId
import javax.inject.Inject

class AlarmScheduler @Inject constructor(
    private val alarmManager: AlarmManager,
    private val context: Context
) : IAlarmScheduler {

    override fun schedule(alarm: Alarm) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val intent = Intent(context, AlarmBroadCastReceiver::class.java).apply {
                putExtra("EXTRA_MESSAGE", alarm.message)
            }

            val alarmTime = alarm.time.atZone(ZoneId.systemDefault()).toEpochSecond() * 1000L
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                alarmTime,
                PendingIntent.getBroadcast(
                    context,
                    alarm.hashCode(),
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
                )
            )
        }
    }

    override fun scheduleMultiple(alarms: List<Alarm>) {
        for (alarm in alarms) {
            schedule(alarm)
        }
    }
}