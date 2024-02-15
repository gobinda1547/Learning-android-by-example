package com.gobinda.service.sampleproject1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * In this sample project we are using this class as a background service.
 * So we can start this service from our activity, there will be no issue.
 * But if the activity gets paused then within 60 seconds, this background
 * service will be stopped.
 */
class MyCustomService : Service() {

    private var mCoroutine: CoroutineScope? = null

    override fun onCreate() {
        Log.d(LOG_TAG, "onCreate: invoked")
        super.onCreate()
        mCoroutine = CoroutineScope(Dispatchers.IO)
        mCoroutine?.launch {
            for (i in 0..100) {
                Log.d(LOG_TAG, "onCreate: inside for loop $i")
                delay(1000)
            }
        }
    }

    override fun onDestroy() {
        Log.d(LOG_TAG, "onDestroy: invoked")
        super.onDestroy()
        mCoroutine?.cancel()
        mCoroutine = null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(LOG_TAG, "onStartCommand: invoked")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}