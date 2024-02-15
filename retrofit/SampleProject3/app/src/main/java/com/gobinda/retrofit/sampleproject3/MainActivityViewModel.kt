package com.gobinda.retrofit.sampleproject3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
            _outputText.tryEmit("Started..")
            retroFitApi.loadNewData().enqueue(object : Callback<List<UserInfo>> {
                override fun onResponse(
                    call: Call<List<UserInfo>>,
                    response: Response<List<UserInfo>>
                ) {
                    _outputText.tryEmit(
                        when (response.isSuccessful) {
                            true -> response.body()?.toString() ?: "Null body"
                            else -> "response not successful ${response.code()}"
                        }
                    )
                }

                override fun onFailure(call: Call<List<UserInfo>>, t: Throwable) {
                    _outputText.tryEmit(t.message ?: "null throwable message")
                }
            })
        }
    }
}

interface RemoteApi {
    @GET("todos")
    fun loadNewData(): Call<List<UserInfo>>
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