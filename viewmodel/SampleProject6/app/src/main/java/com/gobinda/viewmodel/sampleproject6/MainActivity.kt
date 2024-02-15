package com.gobinda.viewmodel.sampleproject6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var nextButton: Button
    private lateinit var numberTextView: TextView

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nextButton = findViewById(R.id.next_button)
        numberTextView = findViewById(R.id.number_textview)

        nextButton.setOnClickListener { viewModel.generateNewNumber() }

        viewModel.newNumber.observe(this) {
            numberTextView.text = it?.toString() ?: "?"
        }
    }
}