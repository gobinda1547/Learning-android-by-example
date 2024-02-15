package com.gobinda.viewmodel.sampleproject3

import androidx.lifecycle.ViewModel
import java.util.Random

class MainActivityViewModel: ViewModel() {

    var actualNumber: Int? = null
        private set

    fun generateNewNumber() {
        actualNumber = Random().nextInt(1000)
    }
}