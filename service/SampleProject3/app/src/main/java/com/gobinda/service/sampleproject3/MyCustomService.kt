package com.gobinda.service.sampleproject3

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
 * In this sample project we are using this class as a bound service.
 * So we can start this service from our activity, there will be no issue.
 * Also we can pause or stop the activity that won't affect this service.
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
        Log.d(LOG_TAG, "onBind: invoked")
        return null
    }
}