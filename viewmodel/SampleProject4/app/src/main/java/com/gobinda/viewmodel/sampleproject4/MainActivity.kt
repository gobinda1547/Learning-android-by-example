package com.gobinda.viewmodel.sampleproject4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * if saved instance state is null, that means the activity is newly created,
         * so we can add a new fragment, on the other hand, when saved instance state
         * is not null that means the activity has restored and it should already have
         * a fragment. So in that case we will not add new fragment to it.
         */
        if (savedInstanceState == null) {
            val fragment = MainFragment()
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.add(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }
}