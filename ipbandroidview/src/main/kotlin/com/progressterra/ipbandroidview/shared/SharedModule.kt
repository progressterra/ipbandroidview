package com.progressterra.ipbandroidview.shared

import android.content.ClipboardManager
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.work.WorkManager
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val sharedModule = module {

    single { WorkManager.getInstance(androidContext()) }

    single { androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    single { MediaPlayer() }

    @Suppress("DEPRECATION")
    single {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            MediaRecorder(androidContext())
        else
            MediaRecorder()
    }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    single { Gson() }
}