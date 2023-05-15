package com.progressterra.ipbandroidview.di

import android.content.ClipboardManager
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.core.CreateId
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.MakePhotoContract
import com.progressterra.ipbandroidview.core.ManagePermissionContract
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.core.SplitName
import com.progressterra.ipbandroidview.core.StartActivityContract
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.mapper.OfferMapper
import com.progressterra.ipbandroidview.domain.mapper.PartnerMapper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

@Suppress("unused")
val iPBAndroidViewModule = module {

    includes(iPBAndroidAPIModule, useCasesModule, viewModelsModule)


    single { Gson() }

    single<SplitName> { SplitName.Base() }

    single<ManageResources> { ManageResources.Base(androidContext()) }


    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

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

    single<ProvideLocation> { ProvideLocation.Base(get()) }

    single<FileExplorer> {
        FileExplorer.Haccp(androidContext(), get(qualifier = StringQualifier("authority")))
    }

    single { MediaPlayer() }

    @Suppress("DEPRECATION")
    single {
        if (VERSION.SDK_INT >= VERSION_CODES.S)
            MediaRecorder(androidContext())
        else
            MediaRecorder()
    }

    single<VoiceManager> { VoiceManager.Base(get()) }

    single<AudioManager> { AudioManager.Base(get()) }

    single { androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    single<OfferMapper> { OfferMapper.Base(get()) }

    single<PartnerMapper> { PartnerMapper.Base(get()) }

    single<CreateId> { CreateId.Base() }
}