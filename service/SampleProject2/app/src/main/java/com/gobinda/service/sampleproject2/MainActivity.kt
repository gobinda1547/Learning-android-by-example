package com.gobinda.service.sampleproject2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

const val LOG_TAG = "MyTestApp"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.start_service_button).setOnClickListener {
            startService(Intent(this, MyCustomService::class.java))
        }
        findViewById<Button>(R.id.stop_service_button).setOnClickListener {
            stopService(Intent(this, MyCustomService::class.java))
        }
    }
}