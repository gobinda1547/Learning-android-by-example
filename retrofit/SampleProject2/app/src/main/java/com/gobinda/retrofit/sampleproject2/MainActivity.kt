package com.gobinda.retrofit.sampleproject2

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.gobinda.retrofit.sampleproject2.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.button.setOnClickListener { viewModel.downloadAndShowResult() }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.outputText.collectLatest { serverOutput ->
                    when (serverOutput) {
                        is ServerOutput.Idle -> {
                            binding.textView.text = "Idle"
                        }

                        is ServerOutput.OnError -> {
                            val code = serverOutput.errorCode
                            val msg = serverOutput.errorMessage
                            binding.textView.text = "OnError -> $code -> $msg"
                        }

                        ServerOutput.OnStarted -> {
                            binding.textView.text = "Started"
                        }

                        is ServerOutput.OnSuccessful -> {
                            binding.textView.text = serverOutput.actualData
                        }
                    }
                }
            }
        }
    }
}