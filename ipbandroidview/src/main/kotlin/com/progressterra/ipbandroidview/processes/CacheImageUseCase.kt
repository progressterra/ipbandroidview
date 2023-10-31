package com.progressterra.ipbandroidview.processes

import android.net.Uri
import android.util.Log
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.listener.BaseRequestListener
import com.facebook.imagepipeline.request.ImageRequest
import com.progressterra.ipbandroidview.R

interface CacheImageUseCase {

    suspend operator fun invoke(image: String): Result<Unit>

    class Base : CacheImageUseCase {

        override suspend fun invoke(image: String): Result<Unit> = runCatching {
            val pipeline = Fresco.getImagePipeline()
            val uri = Uri.parse(image)
            if (!pipeline.isInBitmapMemoryCache(uri) && uri != Uri.EMPTY) {
                pipeline.prefetchToBitmapCache(
                    ImageRequest.fromUri(uri),
                    null,
                    object : BaseRequestListener() {

                        override fun onProducerFinishWithCancellation(
                            requestId: String,
                            producerName: String,
                            extraMap: Map<String, String>?
                        ) {
                            throw ToastedException(R.string.image_not_fetched)
                        }

                        override fun onProducerFinishWithFailure(
                            requestId: String,
                            producerName: String,
                            t: Throwable,
                            extraMap: Map<String, String>?
                        ) {
                            throw ToastedException(R.string.image_not_fetched)
                        }

                        override fun onProducerFinishWithSuccess(
                            requestId: String,
                            producerName: String,
                            extraMap: Map<String, String>?
                        ) {
                            Log.d(
                                "IMAGE",
                                "onProducerFinishWithSuccess: id: $requestId, name: $producerName"
                            )
                        }
                    })
            }
        }
    }
}