package com.gobinda.hilt.sampleproject1.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gobinda.hilt.sampleproject1.R
import com.gobinda.hilt.sampleproject1.app.LOG_TAG
import com.gobinda.hilt.sampleproject1.app.PASS_KEY_ACTIVITY_SIZE
import com.gobinda.hilt.sampleproject1.services.DataSource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainFragmentInterface {

    companion object {
        private const val TAG = "$LOG_TAG -> MainActivity"
    }

    private var stackSize: Int = 0

    @Inject
    lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: invoked [$dataSource]")
        Log.d(TAG, "onCreate: invoked [${dataSource.fetchData()}]")

        stackSize = intent.getIntExtra(PASS_KEY_ACTIVITY_SIZE, 0)
        replaceWithNewFragment()
    }

    private fun replaceWithNewFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        val fragmentObj = MainFragment.getNewObject(stackSize)
        transaction.replace(R.id.fragment_container, fragmentObj, null)
        transaction.commitNow()
    }

    override fun startNewActivity() {
        startActivity(
            Intent(this, MainActivity::class.java).apply {
                putExtra(PASS_KEY_ACTIVITY_SIZE, stackSize + 1)
            }
        )
    }

    override fun startNewFragment() {
        replaceWithNewFragment()
    }
}