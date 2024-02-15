package com.gobinda.lifecycle.sampleproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log

const val LOG_TAG = "MyTestApp"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(LOG_TAG, "onCreate: invoked")
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.d(LOG_TAG, "onStart: invoked")
    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "onResume: invoked")
    }

    override fun onPause() {
        super.onPause()
        Log.d(LOG_TAG, "onPause: invoked")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(LOG_TAG, "onRestart: invoked")
    }

    override fun onStop() {
        super.onStop()
        Log.d(LOG_TAG, "onStop: invoked")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(LOG_TAG, "onDestroy: invoked")
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        Log.d(LOG_TAG, "onSaveInstanceState: invoked [persistent state]")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(LOG_TAG, "onSaveInstanceState: invoked")
    }
}