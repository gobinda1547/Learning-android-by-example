package com.gobinda.glide.sampleproject1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.gobinda.glide.sampleproject1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val ONLINE_IMAGE_LINK =
            "https://miro.medium.com/v2/resize:fit:1187/1*0FqDC0_r1f5xFz3IywLYRA.jpeg"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        initializeUI()
    }

    private fun initializeUI() {
        binding.apply {
            loadFromOnlineDefaultCache.setOnClickListener { invokeLoadFromOnlineDefaultCache() }
            loadFromOnlineNoCache.setOnClickListener { invokeLoadFromOnlineNoCache() }
            loadFromOnlineCustomCache.setOnClickListener { invokeLoadFromOnlineCustomCache() }
        }
    }

    private fun invokeLoadFromOnlineCustomCache() {
        Glide.with(this)
            .load(ONLINE_IMAGE_LINK)
            .placeholder(R.drawable.place_holder_image)
            .error(R.drawable.error_image)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.imageView)
    }

    private fun invokeLoadFromOnlineNoCache() {
        Glide.with(this)
            .load(ONLINE_IMAGE_LINK)
            .placeholder(R.drawable.place_holder_image)
            .error(R.drawable.error_image)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imageView)
    }

    private fun invokeLoadFromOnlineDefaultCache() {
        Glide.with(this)
            .load(ONLINE_IMAGE_LINK)
            .placeholder(R.drawable.place_holder_image)
            .error(R.drawable.error_image)
            .into(binding.imageView)
    }
}