package com.gobinda.retrofit.sampleproject2

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class MainActivityViewModel : ViewModel() {

    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        private const val TAG = "TestApk"
    }


    private val _outputText: MutableStateFlow<ServerOutput> = MutableStateFlow(ServerOutput.Idle)
    val outputText: StateFlow<ServerOutput> = _outputText

    private val retroFitApi = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(RemoteApi::class.java)

    fun downloadAndShowResult() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _outputText.tryEmit(ServerOutput.OnStarted)
                val serverResponse = retroFitApi.loadNewData()
                val serverOutput = convertServerDataToOutput(serverResponse)
                _outputText.tryEmit(serverOutput)
            } catch (e: Exception) {
                e.printStackTrace()
                _outputText.tryEmit(ServerOutput.OnError(-1, "in exception"))
            }
        }
    }

    private fun convertServerDataToOutput(response: Response<List<UserInfo>>): ServerOutput {
        Log.d(TAG, "convertServerDataToOutput: invoked with ${response.isSuccessful}")
        if (response.isSuccessful) {
            response.body()?.let {
                return ServerOutput.OnSuccessful(it.toString())
            }
        }
        return ServerOutput.OnError(response.code(), "Check error code")
    }
}

sealed class ServerOutput {
    object Idle : ServerOutput()
    object OnStarted : ServerOutput()
    class OnSuccessful(val actualData: String) : ServerOutput()
    class OnError(val errorCode: Int, val errorMessage: String) : ServerOutput()
}

interface RemoteApi {
    @GET("todos")
    suspend fun loadNewData(): Response<List<UserInfo>>
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