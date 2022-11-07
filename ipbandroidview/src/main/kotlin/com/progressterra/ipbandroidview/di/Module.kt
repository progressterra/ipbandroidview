package com.progressterra.ipbandroidview.di

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.core.SplitName
import com.progressterra.ipbandroidview.core.permission.ManagePermission
import com.progressterra.ipbandroidview.core.permission.PermissionCache
import com.progressterra.ipbandroidview.core.picture.PictureCache
import com.progressterra.ipbandroidview.core.startactivity.StartActivity
import com.progressterra.ipbandroidview.core.startactivity.StartActivityCache
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.filter.SuggestionFilter
import com.progressterra.ipbandroidview.domain.mapper.AddressGuesserMapper
import com.progressterra.ipbandroidview.domain.mapper.GoodsMapper
import com.progressterra.ipbandroidview.domain.mapper.PriceMapper
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.domain.GoodsSource
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

@Suppress("unused")
val iPBAndroidViewModule = module {

    includes(iPBAndroidAPIModule, useCasesModule, viewModelsModule)

    factory {
        Gson()
    }

    factory<SplitName> {
        SplitName.Base()
    }

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
        GoodsSource(get())
    }

    single<PriceMapper> {
        PriceMapper.Russia()
    }

    single<GoodsMapper> { GoodsMapper.Base(get(), get(), get()) }

    single {
        PermissionCache.Base()
    }.binds(arrayOf(PermissionCache::class, ManagePermission::class))

    single {
        StartActivityCache.Base()
    }.binds(arrayOf(StartActivityCache::class, StartActivity::class))

    single {
        PictureCache.Base()
    }.binds(arrayOf(PictureCache.Client::class, PictureCache.Activity::class))

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

    @Suppress("DEPRECATION")
    single {
        if (VERSION.SDK_INT >= VERSION_CODES.S)
            MediaRecorder(androidContext())
        else
            MediaRecorder()
    }

    single<VoiceManager> {
        VoiceManager.Base(get())
    }

    single<AudioManager> {
        AudioManager.Base(get(), get())
    }


}