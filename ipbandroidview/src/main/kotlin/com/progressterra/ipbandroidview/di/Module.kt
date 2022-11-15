package com.progressterra.ipbandroidview.di

import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.MakePhotoContract
import com.progressterra.ipbandroidview.core.ManagePermissionContract
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.core.SplitName
import com.progressterra.ipbandroidview.core.StartActivityContract
import com.progressterra.ipbandroidview.core.voice.AudioManager
import com.progressterra.ipbandroidview.core.voice.VoiceManager
import com.progressterra.ipbandroidview.domain.filter.SuggestionFilter
import com.progressterra.ipbandroidview.domain.mapper.AddressGuesserMapper
import com.progressterra.ipbandroidview.domain.mapper.CartGoodsMapper
import com.progressterra.ipbandroidview.domain.mapper.CatalogMapper
import com.progressterra.ipbandroidview.domain.mapper.GoodsDetailsMapper
import com.progressterra.ipbandroidview.domain.mapper.GoodsFilterMapper
import com.progressterra.ipbandroidview.domain.mapper.PriceMapper
import com.progressterra.ipbandroidview.domain.mapper.StoreGoodsMapper
import com.progressterra.ipbandroidview.domain.mapper.SubCatalogMapper
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

@Suppress("unused")
val iPBAndroidViewModule = module {

    includes(iPBAndroidAPIModule, useCasesModule, viewModelsModule)

    single<StoreGoodsMapper> { StoreGoodsMapper.Base(get(), get(), get()) }

    single {
        Gson()
    }

    single<SplitName> {
        SplitName.Base()
    }

    single<ManageResources> {
        ManageResources.Base(androidContext())
    }

    single<SuggestionMapper> {
        SuggestionMapper.Base()
    }

    single<SuggestionFilter> { SuggestionFilter.Base() }

    single<AddressGuesserMapper> {
        AddressGuesserMapper.Base()
    }

    single<CartGoodsMapper> {
        CartGoodsMapper.Base(
            get(), get(), get()
        )
    }

    single {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }

    single<PriceMapper> {
        PriceMapper.Russia()
    }

    single<GoodsDetailsMapper> { GoodsDetailsMapper.Base(get(), get(), get()) }

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

    single<GoodsFilterMapper> { GoodsFilterMapper.Base() }

    single<CatalogMapper> { CatalogMapper.Base(get(), get()) }

    single<SubCatalogMapper> { SubCatalogMapper.Base(get()) }
}