package sq.mayv.aladhan.broadcast

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import sq.mayv.aladhan.R

class AlarmBroadCastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra("EXTRA_MESSAGE") ?: return
        val channelId = "3025"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Aladhan")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        notificationManager.notify(1, builder.build())
    }

}