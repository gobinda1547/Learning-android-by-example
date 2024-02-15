package com.gobinda.viewmodel.sampleproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: Button
    private lateinit var numberTextView: TextView

    private var actualNumber: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_button)
        numberTextView = findViewById(R.id.number_textview)

        nextButton.setOnClickListener { handleNextButtonClicked() }

        // this function call won't have any effect when the activity starts for the
        // first time, cause the actualNumber will be null then, but when the activity
        // will restart then we have to display the previous number on the textview (cause
        // initially the textview will be newly created since the activity restarts), that's
        // why we have to call this function.
        //
        // Though we are expecting that it will show the last random number. But it actually
        // won't show the number. cause when the activity restarts it will also create a new
        // actualNumber object with initial value null
        displayTheNumber()
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