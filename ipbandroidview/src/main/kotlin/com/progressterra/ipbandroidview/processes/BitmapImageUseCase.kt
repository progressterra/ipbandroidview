package com.progressterra.ipbandroidview.processes

import android.content.Context
import android.graphics.Bitmap
import com.bumptech.glide.Glide

interface BitmapImageUseCase {

    suspend operator fun invoke(image: String): Result<Bitmap?>

    class Base(
        private val context: Context
    ) : BitmapImageUseCase {

        override suspend fun invoke(image: String): Result<Bitmap?> = runCatching {
            if (image.isNotEmpty()) {
                Glide.with(context)
                    .asBitmap()
                    .load(image).submit().get()
            } else {
                null
            }
        }
    }
}