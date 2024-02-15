package com.gobinda.viewmodel.sampleproject2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ACTUAL_NUMBER_RESTORE_KEY = "actual_number_restore_key"
    }

    private lateinit var nextButton: Button
    private lateinit var numberTextView: TextView

    private var actualNumber: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_button)
        numberTextView = findViewById(R.id.number_textview)

        nextButton.setOnClickListener { handleNextButtonClicked() }

        /**
         * When the activity will be restored then savedInstanceState will not be null,
         * and since we put our actual number inside savedInstanceState, so now we can
         * retrieve it back. and show it to the user.
         */
        savedInstanceState?.let { lastState ->
            if (lastState.containsKey(ACTUAL_NUMBER_RESTORE_KEY)) {
                actualNumber = lastState.getInt(ACTUAL_NUMBER_RESTORE_KEY)
                displayTheNumber()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        /**
         * We must have to check if the actualNumber is null or not, cause configuration change
         * can be performed even before clicking the next button. In that case the actualNumber
         * will have null in it. and we can't put null inside the outState Bundle.
         */
        actualNumber?.let { number ->
            outState.putInt(ACTUAL_NUMBER_RESTORE_KEY, number)
        }
    }

    /**
     * Since user clicked on the next button, so according to our requirement,
     * 1. We will first generate a new number,
     * 2. then we will show it to the user.
     */
    private fun handleNextButtonClicked() {
        generateNewNumber()
        displayTheNumber()
    }

    private fun displayTheNumber() {
        actualNumber?.let { numberTextView.text = it.toString() }
    }

    private fun generateNewNumber() {
        actualNumber = Random().nextInt(1000)
    }
}