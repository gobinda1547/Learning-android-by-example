package com.gobinda.viewmodel.sampleproject3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: Button
    private lateinit var numberTextView: TextView

    private val viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_button)
        numberTextView = findViewById(R.id.number_textview)

        nextButton.setOnClickListener { handleNextButtonClicked() }

        // trying to display the number if exist, there can be 2 cases
        // 1. When activity will be created first time then the actualNumber will be null,
        // so this function call won't have any effect.
        // 2. When the activity will be restored then if will have the previous view
        // model's reference, so actual Number may not be null then. In that case we have
        // to show it to the user.
        displayTheNumber()
    }

    /**
     * Since user clicked on the next button, so according to our requirement,
     * 1. We will first generate a new number,
     * 2. then we will show it to the user.
     */
    private fun handleNextButtonClicked() {
        viewModel.generateNewNumber()
        displayTheNumber()
    }

    private fun displayTheNumber() {
        viewModel.actualNumber?.let { numberTextView.text = it.toString() }
    }
}