package com.progressterra.ipbandroidview.di

import android.media.MediaPlayer
import android.media.MediaRecorder
import com.google.android.gms.location.LocationServices
import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.StartActivity
import com.progressterra.ipbandroidview.core.StartActivityCache
import com.progressterra.ipbandroidview.core.permission.ManagePermission
import com.progressterra.ipbandroidview.core.permission.PermissionCache
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.domain.filter.SuggestionFilter
import com.progressterra.ipbandroidview.domain.mapper.AddressGuesserMapper
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

@Suppress("unused")
val iPBAndroidViewModule = module {

    includes(iPBAndroidAPIModule, useCasesModule, viewModelsModule)


    factory<ManageResources> {
        ManageResources.Base(androidContext())
    }

    factory<SuggestionMapper> {
        SuggestionMapper.Base()
    }

    factory<SuggestionFilter> { SuggestionFilter.Base() }

    factory<AddressGuesserMapper> {
        AddressGuesserMapper.Base()
    }

    factory {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }

    single {
        PermissionCache.Base()
    }.binds(arrayOf(PermissionCache::class, ManagePermission::class))

    single {
        StartActivityCache.Base()
    }.binds(arrayOf(StartActivityCache::class, StartActivity::class))

    single<ProvideLocation> {
        ProvideLocation.Base(get())
    }

    single<FileExplorer> {
        FileExplorer.Base(
            androidContext(),
            get(qualifier = StringQualifier("authority"))
        )
    }

    single {
        MediaPlayer()
    }

    single {
        MediaRecorder(androidContext())
    }

    single<VoiceManager> {
        VoiceManager.Base(get(), get())
    }

    single<AudioManager> {
        AudioManager.Base(get(), get())
    }


}