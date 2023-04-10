package com.progressterra.ipbandroidview.integration

import android.content.ClipboardManager
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import com.google.android.gms.location.LocationServices
import com.google.gson.Gson
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.activity.MakePhotoContract
import com.progressterra.ipbandroidview.shared.activity.ManagePermissionContract
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.activity.StartActivityContract
import com.progressterra.ipbandroidview.shared.voice.AudioManager
import com.progressterra.ipbandroidview.shared.voice.VoiceManager
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.processes.mapper.CartGoodsMapper
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.processes.mapper.GoodsDetailsMapper
import com.progressterra.ipbandroidview.processes.mapper.GoodsFilterMapper
import com.progressterra.ipbandroidview.processes.mapper.MessageMapper
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.processes.mapper.StatusOrderMapper
import com.progressterra.ipbandroidview.processes.mapper.SubCatalogMapper
import com.progressterra.ipbandroidview.widgets.proshkaoffers.OfferMapper
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module


@Suppress("unused")
val iPBAndroidViewModule = module {

    single { Gson() }

    single<SplitName> { SplitName.Base() }

    single<ManageResources> { ManageResources.Base(androidContext()) }

    single<CartGoodsMapper> { CartGoodsMapper.Base(get(), get(), get()) }

    single { LocationServices.getFusedLocationProviderClient(androidContext()) }

    single<PriceMapper> { PriceMapper.Russia() }

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

    single<ProvideLocation> { ProvideLocation.Base(get()) }

    single<FileExplorer> {
        FileExplorer.Haccp(androidContext(), get(qualifier = StringQualifier("authority")))
    }

    single { MediaPlayer() }

    @Suppress("DEPRECATION")
    single {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
            MediaRecorder(androidContext())
        else
            MediaRecorder()
    }

    single<VoiceManager> { VoiceManager.Base(get()) }

    single<AudioManager> { AudioManager.Base(get()) }

    single<GoodsFilterMapper> { GoodsFilterMapper.Base() }

    single<CatalogMapper> { CatalogMapper.Base(get(), get()) }

    single<SubCatalogMapper> { SubCatalogMapper.Base(get()) }

    single<ImageMapper> { ImageMapper.Base(get()) }

    single<StatusOrderMapper> { StatusOrderMapper.Base(get()) }

    single<AddressesMapper> { AddressesMapper.Base() }

    single<MessageMapper> { MessageMapper.Base(get()) }

    single { androidContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }

    single<OfferMapper> { OfferMapper.Base(get()) }

    single<PartnerMapper> { PartnerMapper.Base(get()) }

    single<CreateId> { CreateId.Base() }

    single<PromoCategoryMapper> { PromoCategoryMapper.Base(get()) }
}