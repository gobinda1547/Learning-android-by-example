package com.gobinda.retrofit.sampleproject1

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivityViewModel : ViewModel() {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    private val _outputText: MutableStateFlow<String> = MutableStateFlow("")
    val outputText: StateFlow<String> = _outputText

    private val retroFitApi = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RemoteApi::class.java)

    fun downloadAndShowResult() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _outputText.tryEmit("Started..")
                val outputFromServer = retroFitApi.loadNewData()
                _outputText.tryEmit(outputFromServer.toString())
            } catch (e: Exception) {
                _outputText.tryEmit(e.toString())
                e.printStackTrace()
            }
        }
    }
}

interface RemoteApi {
    @GET("todos")
    suspend fun loadNewData(): List<UserInfo>
}

data class UserInfo(
    @SerializedName("userId")
    val userId: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("completed")
    val completed: Boolean
)