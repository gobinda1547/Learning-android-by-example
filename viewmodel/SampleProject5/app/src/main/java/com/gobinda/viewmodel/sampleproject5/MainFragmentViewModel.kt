package com.gobinda.viewmodel.sampleproject5

import androidx.lifecycle.ViewModel
import java.util.Random

class MainFragmentViewModel: ViewModel() {

    var actualNumber: Int? = null
        private set

    fun generateNewNumber() {
        actualNumber = Random().nextInt(1000)
    }
}