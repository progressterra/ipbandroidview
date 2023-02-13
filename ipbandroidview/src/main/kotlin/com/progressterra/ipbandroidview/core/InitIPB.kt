package com.progressterra.ipbandroidview.core

import android.content.Context
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.imagepipeline.backends.okhttp3.OkHttpImagePipelineConfigFactory
import com.google.firebase.FirebaseApp
import com.progressterra.ipbandroidapi.api.IPBAndroidAPI
import com.progressterra.ipbandroidview.di.iPBAndroidViewModule
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

@Suppress("unused")
object InitIPB {

    operator fun invoke(context: Context, accessKey: String) {
        IPBAndroidAPI.initialize(accessKey)
        FirebaseApp.initializeApp(context)
        Fresco.initialize(
            context, OkHttpImagePipelineConfigFactory
                .newBuilder(context, OkHttpClient.Builder().build())
                .setDiskCacheEnabled(true)
                .setDownsampleEnabled(true)
                .setResizeAndRotateEnabledForNetwork(true)
                .build()
        )
        startKoin {
            androidLogger()
            androidContext(context)
            modules(iPBAndroidViewModule)
        }
    }
}