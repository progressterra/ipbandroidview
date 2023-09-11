package com.progressterra.ipbandroidview.shared

import android.content.ClipboardManager
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.progressterra.ipbandroidview.shared.activity.MakePhotoContract
import com.progressterra.ipbandroidview.shared.activity.MakeToastContract
import com.progressterra.ipbandroidview.shared.activity.ManagePermissionContract
import com.progressterra.ipbandroidview.shared.activity.StartActivityContract
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

val sharedModule = module {

    single {
        ManagePermissionContract.Base()
    }.binds(
        arrayOf(
            ManagePermissionContract.Client::class,
            ManagePermissionContract.Activity::class
        )
    )

    single {
        StartActivityContract.Base()
    }.binds(arrayOf(StartActivityContract.Client::class, StartActivityContract.Activity::class))

    single {
        MakePhotoContract.Base()
    }.binds(arrayOf(MakePhotoContract.Activity::class, MakePhotoContract.Client::class))

    single {
        MakeToastContract.Base()
    }.binds(arrayOf(MakeToastContract.Activity::class, MakeToastContract.Client::class))

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

    single<ManageResources> { ManageResources.Base(androidContext()) }

    single { Gson() }

    single<FileExplorer> {
        FileExplorer.Redi(androidContext(), get(qualifier = StringQualifier("authority")))
    }

    single<CreateId> {
        CreateId.Base()
    }
}