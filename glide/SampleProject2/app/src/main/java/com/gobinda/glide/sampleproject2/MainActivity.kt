package com.gobinda.glide.sampleproject2

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gobinda.glide.sampleproject2.databinding.ActivityMainBinding
import java.io.ByteArrayOutputStream
import java.io.File


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "TestApk"
        const val ONLINE_IMAGE_LINK =
            "https://miro.medium.com/v2/resize:fit:1187/1*0FqDC0_r1f5xFz3IywLYRA.jpeg"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        Log.d(TAG, "onCreate: files directory [${filesDir.absolutePath}]")
        Log.d(TAG, "onCreate: cache directory [${cacheDir.absolutePath}]")

        initializeUI()
    }

    private fun initializeUI() {
        binding.apply {
            downloadAndSaveButton.setOnClickListener { downloadAndSaveAsImage() }
            deleteFromCacheDir.setOnClickListener { invokeDeleteFromCacheDir() }
            deleteFromFileDir.setOnClickListener { invokeDeleteFromFileDir() }
        }
    }

    private fun invokeDeleteFromFileDir() {
        for (singleFile in filesDir.listFiles() ?: emptyArray()) {
            singleFile.delete()
        }
    }

    private fun invokeDeleteFromCacheDir() {
        for (singleFile in cacheDir.listFiles() ?: emptyArray()) {
            singleFile.delete()
        }
    }

    private fun downloadAndSaveAsImage() {
        val customTarget = object : CustomTarget<Bitmap?>() {

            override fun onLoadStarted(placeholder: Drawable?) {
                Log.d(TAG, "onLoadStarted: invoked")
                placeholder?.let { binding.imageView.setImageDrawable(it) }
                super.onLoadStarted(placeholder)
            }

            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap?>?) {
                Log.d(TAG, "onResourceReady: trying to save the file")
                saveAsFile(filesDir.absolutePath, resource)
                saveAsFile(cacheDir.absolutePath, resource)
                Log.d(TAG, "onResourceReady: file saving done")
                binding.imageView.setImageBitmap(resource)
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                Log.d(TAG, "onLoadFailed: invoked")
                errorDrawable?.let { binding.imageView.setImageDrawable(it) }
                super.onLoadFailed(errorDrawable)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                Log.d(TAG, "onLoadCleared: invoked")
                placeholder?.let { binding.imageView.setImageDrawable(it) }
            }
        }
        Glide.with(this)
            .asBitmap()
            .load(ONLINE_IMAGE_LINK)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true) // mandatory otherwise won't make online request everytime
            .placeholder(R.drawable.place_holder_image)
            .error(R.drawable.error_image)
            .into(customTarget)
    }

    private fun saveAsFile(location: String, bitmap: Bitmap) {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        val byteArray = stream.toByteArray()
        stream.close()

        val file = File(location, "${System.currentTimeMillis()}.png")
        val outputStream = file.outputStream()
        outputStream.write(byteArray)
        outputStream.close()
    }
}