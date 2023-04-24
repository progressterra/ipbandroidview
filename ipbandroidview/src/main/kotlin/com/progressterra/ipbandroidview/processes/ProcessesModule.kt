package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.pages.delivery.CreateDeliveryOrderUseCase
import com.progressterra.ipbandroidview.pages.goodsdetails.GoodsDetailsMapper
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.goods.GoodsSource
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.location.OpenMapUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.processes.mapper.GoodsFilterMapper
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.processes.mapper.StatusOrderMapper
import com.progressterra.ipbandroidview.processes.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.processes.store.OrdersUseCase
import com.progressterra.ipbandroidview.processes.store.SizeTableUseCase
import com.progressterra.ipbandroidview.processes.user.FetchPhoneUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserProfileUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAddressUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.processes.user.UserExistsUseCase
import com.progressterra.ipbandroidview.processes.utils.CopyTextUseCase
import com.progressterra.ipbandroidview.processes.utils.FetchVersionUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenPhoneUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.processes.utils.ShareTextUseCase
import org.koin.dsl.module

val processesModule = module {

    single<CopyTextUseCase> { CopyTextUseCase.Base(get()) }

    single { GoodsSource(get(), get()) }

    single<FetchVersionUseCase> { FetchVersionUseCase.Base(get()) }

    single<ShareTextUseCase> { ShareTextUseCase.Base(get()) }

    single<OpenPhoneUseCase> { OpenPhoneUseCase.Base(get()) }

    single<OpenUrlUseCase> { OpenUrlUseCase.Base(get()) }

    single<SaveAddressUseCase> { SaveAddressUseCase.Base(get(), get(), get(), get()) }

    single<SizeTableUseCase> { SizeTableUseCase.Base(get(), get(), get()) }

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get(), get(), get(), get(), get(), get()) }

    single<FetchFavoriteIds> { FetchFavoriteIds.Base(get(), get(), get()) }

    single<CheckPermissionUseCase> { CheckPermissionUseCase.Base(get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<CreateDeliveryOrderUseCase> {
//        CreateDeliveryOrderUseCase.Base(get(), get(), get())
        CreateDeliveryOrderUseCase.Test()
    }

    single<StopRecordingUseCase> { StopRecordingUseCase.Base(get()) }

    single<StartRecordingUseCase> { StartRecordingUseCase.Base(get(), get(), get()) }

    single<StartAudioUseCase> { StartAudioUseCase.Base(get(), get()) }

    single<PauseAudioUseCase> { PauseAudioUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get(), get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<StatusOrderMapper> { StatusOrderMapper.Base(get()) }

    single<PriceMapper> { PriceMapper.Russia() }

    single<GoodsFilterMapper> { GoodsFilterMapper.Base() }

    single<GoodsDetailsMapper> { GoodsDetailsMapper.Base(get(), get(), get()) }

    single<CatalogMapper> { CatalogMapper.Base(get()) }

    single<AddressesMapper> { AddressesMapper.Base() }

    single<ProvideLocation> { ProvideLocation.Base(get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<GuessLocationUseCase> { GuessLocationUseCase.Base(get(), get()) }

    single<GoodsUseCase> {
//        GoodsUseCase.Base(get())
        GoodsUseCase.Test()
    }


    single<FetchGoodsPage> {
//        FetchGoodsPage.Base(get(), get(), get(), get())
        FetchGoodsPage.Test()
    }

    single<AddToCartUseCase> {
        AddToCartUseCase.Test()
//        AddToCartUseCase.Base(get(), get(), get())
    }

    single<RemoveFromCartUseCase> {
        RemoveFromCartUseCase.Test()
//        RemoveFromCartUseCase.Base(get(), get(), get())
    }

    single<StartVerificationChannelUseCase> {
//        StartVerificationChannelUseCase.Base(get())
        StartVerificationChannelUseCase.Test()
    }

    single<SaveDataUseCase> {
        SaveDataUseCase.Test()
    }

    single<FetchUserUseCase> {
        FetchUserUseCase.Base()
    }

    single<UserExistsUseCase> {
        UserExistsUseCase.Base()
    }

    single<FetchPhoneUseCase> {
        FetchPhoneUseCase.Base()
    }

    single<FetchUserProfileUseCase> {
        FetchUserProfileUseCase.Base()
    }
}
