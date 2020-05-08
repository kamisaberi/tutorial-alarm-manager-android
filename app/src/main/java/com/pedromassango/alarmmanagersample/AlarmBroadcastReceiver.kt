package com.pedromassango.alarmmanagersample

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.media.RingtoneManager
import android.media.Ringtone




/**
 * Created by Pedro Massango on 5/28/18.
 */
class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {

        // Create the notification to be shown
        val mBuilder = NotificationCompat.Builder(context!!, "my_app")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Alarm Manager")
                .setContentText("Hora de tomar seus comprimidos.")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Get the Notification manager service
        val am = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Generate an Id for each notification
        val id = System.currentTimeMillis()/1000

        // Show a notification
        am.notify(id.toInt(), mBuilder.build())


        val notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val r = RingtoneManager.getRingtone(context, notification)
        r.play()

        val startIntent = context
                .packageManager
                .getLaunchIntentForPackage(context.packageName)

        startIntent!!.flags = Intent.FLAG_ACTIVITY_REORDER_TO_FRONT or
                Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED
        context.startActivity(startIntent)
    }
}