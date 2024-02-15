package com.gobinda.service.sampleproject2

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * In this sample project we are using this class as a foreground service.
 * So we can start this service from our activity, there will be no issue.
 * Also we can pause or stop the activity that won't affect this service.
 * Even destroying the activity won't affect the service. Cause we will
 * show a notification. It is a requirement that - for starting foreground
 * service, we must have to show a notification.
 */
class MyCustomService : Service() {

    companion object {
        private const val CHANNEL_ID = "ForegroundServiceChannelId"
        private const val CHANNEL_NAME = "ForegroundServiceChannelName"
        private const val CHANNEL_PRIORITY = NotificationManager.IMPORTANCE_HIGH
        private const val MY_NOTIFICATION_ID = 99
    }

    private var mCoroutine: CoroutineScope? = null

    /**
     * Since we are using this as a foreground service, So after starting the service
     * we must call [startForeground] method within 5 seconds, otherwise the service
     * will be stopped. And to call [startForeground] method we need a notification
     * object. And to create & show a notification we have to add it to a notification
     * channel for Android API level 26 and onwards. So we will perform all these things
     * one by one here in this on create function.
     */
    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate: invoked")
        super.onCreate()

        createNotificationChannel()
        startForeground(MY_NOTIFICATION_ID, createNotification())

        mCoroutine = CoroutineScope(Dispatchers.IO)
        mCoroutine?.launch {
            for (i in 0..100) {
                Log.d(LOG_TAG, "onCreate: inside for loop $i")
                delay(1000)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand: invoked")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy: invoked")
        mCoroutine?.cancel()
        mCoroutine = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nManager = getSystemService(NotificationManager::class.java)
            val nChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, CHANNEL_PRIORITY)
            nManager.createNotificationChannel(nChannel)
        }
    }

    /**
     * Here we are creating a new notification with id [MY_NOTIFICATION_ID]. attached it
     * with [CHANNEL_ID]. Also we have inserted a pending intent.
     */
    private fun createNotification(): Notification {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service Example")
            .setContentText("This service is running in the foreground.")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .build()
    }
}