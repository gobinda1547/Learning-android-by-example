package com.gobinda.viewmodel.sampleproject6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MainRepository) : ViewModel() {

    private val _newNumber = MutableLiveData<Int?>()
    val newNumber: LiveData<Int?> get() = _newNumber

    fun generateNewNumber() {
        viewModelScope.launch {
            val fetchedValue = repository.fetchNewNumber()
            _newNumber.postValue(fetchedValue)
        }
    }
}