package com.gobinda.viewmodel.sampleproject5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * if saved instance state is null, that means the activity is newly created,
         * And when the activity is newly created then we will add InputFragment to the
         * activity, otherwise we can assume that the activity already has InputFragment.
         */
        if (savedInstanceState == null) {
            val fragment = MainFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}