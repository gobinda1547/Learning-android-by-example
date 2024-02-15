package com.gobinda.service.sampleproject3

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button

const val LOG_TAG = "MyTestApp"

class MainActivity : AppCompatActivity() {

    private val serviceConnector = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.d(LOG_TAG, "onServiceConnected: invoked")
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(LOG_TAG, "onServiceDisconnected: invoked")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.start_service_button).setOnClickListener {
            val intent = Intent(this, MyCustomService::class.java)
            bindService(intent, serviceConnector, Context.BIND_AUTO_CREATE)
        }
        findViewById<Button>(R.id.stop_service_button).setOnClickListener {
            unbindService(serviceConnector)
        }
    }
}