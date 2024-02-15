package com.gobinda.viewmodel.sampleproject4

import androidx.lifecycle.ViewModel
import java.util.Random

class MainFragmentViewModel: ViewModel() {

    var actualNumber: Int? = null
        private set

    fun generateNewNumber() {
        actualNumber = Random().nextInt(1000)
    }
}