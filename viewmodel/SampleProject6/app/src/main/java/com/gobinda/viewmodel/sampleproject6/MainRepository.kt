package com.gobinda.viewmodel.sampleproject6

import kotlinx.coroutines.delay
import java.util.Random
import javax.inject.Inject

/**
 * By adding @Inject constructor(), we let hilt library knew that
 * how to make an instance of this class
 */
class MainRepository @Inject constructor() {

    /**
     * Mocking internet call, in real case this function will fetch a new integer
     * from internet. And here we just mocked it by delaying 500 milli-seconds and
     * then returning a new random number.
     */
    suspend fun fetchNewNumber(): Int {
        delay(500)
        return Random().nextInt(1000)
    }
}