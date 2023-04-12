package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.goods.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsSource
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.location.OpenMapUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.processes.mapper.CatalogMapper
import com.progressterra.ipbandroidview.processes.mapper.GoodsDetailsMapper
import com.progressterra.ipbandroidview.processes.mapper.GoodsFilterMapper
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.processes.mapper.StatusOrderMapper
import com.progressterra.ipbandroidview.processes.mapper.SubCatalogMapper
import com.progressterra.ipbandroidview.processes.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.order.CreateDeliveryOrderUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.store.CatalogUseCase
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.processes.store.OrdersUseCase
import com.progressterra.ipbandroidview.processes.store.SizeTableUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.processes.user.LogoutUseCase
import com.progressterra.ipbandroidview.processes.user.NeedAddressUseCase
import com.progressterra.ipbandroidview.processes.user.NeedDetailsUseCase
import com.progressterra.ipbandroidview.processes.user.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.processes.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.processes.user.UserExistsUseCase
import com.progressterra.ipbandroidview.processes.utils.CopyTextUseCase
import com.progressterra.ipbandroidview.processes.utils.FetchVersionUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenPhoneUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.processes.utils.ShareTextUseCase
import org.koin.dsl.module

val processesModule = module {

    single<CopyTextUseCase> { CopyTextUseCase.Base(get()) }

    single { GoodsSource(get(), get(), get()) }

    single<FetchVersionUseCase> { FetchVersionUseCase.Base(get()) }

    single<ShareTextUseCase> { ShareTextUseCase.Base(get()) }

    single<OpenPhoneUseCase> { OpenPhoneUseCase.Base(get()) }

    single<OpenUrlUseCase> { OpenUrlUseCase.Base(get()) }

    single<UserExistsUseCase> { UserExistsUseCase.Base() }

    single<UpdatePersonalInfoUseCase> { UpdatePersonalInfoUseCase.Base(get(), get()) }

    single<NeedDetailsUseCase> { NeedDetailsUseCase.Base() }

    single<NeedAddressUseCase> { NeedAddressUseCase.Base() }

    single<LogoutUseCase> { LogoutUseCase.Base() }

    single<FetchUserPhoneUseCase> { FetchUserPhoneUseCase.Base() }

    single<FetchUserNameUseCase> { FetchUserNameUseCase.Base() }

    single<FetchUserIdUseCase> { FetchUserIdUseCase.Base() }

    single<FetchUserEmailUseCase> { FetchUserEmailUseCase.Base() }

    single<FetchUserBirthdayUseCase> { FetchUserBirthdayUseCase.Base() }

    single<SaveUserAddressUseCase> { SaveUserAddressUseCase.Base(get(), get(), get(), get()) }

    single<FetchUserAddressUseCase> { FetchUserAddressUseCase.Base() }

    single<SizeTableUseCase> { SizeTableUseCase.Base(get(), get(), get()) }

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get(), get(), get(), get(), get(), get()) }

    single<FetchFavoriteIds> { FetchFavoriteIds.Base(get(), get(), get()) }

    single<CatalogUseCase> { CatalogUseCase.Base(get(), get(), get(), get()) }

    single<CheckPermissionUseCase> { CheckPermissionUseCase.Base(get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<CreateDeliveryOrderUseCase> { CreateDeliveryOrderUseCase.Base(get(), get(), get()) }

    single<StopRecordingUseCase> { StopRecordingUseCase.Base(get()) }

    single<StartRecordingUseCase> { StartRecordingUseCase.Base(get(), get(), get()) }

    single<StartAudioUseCase> { StartAudioUseCase.Base(get(), get()) }

    single<PauseAudioUseCase> { PauseAudioUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get(), get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<SubCatalogMapper> { SubCatalogMapper.Base(get()) }

    single<StatusOrderMapper> { StatusOrderMapper.Base(get()) }

    single<PriceMapper> { PriceMapper.Russia() }

    single<GoodsFilterMapper> { GoodsFilterMapper.Base() }

    single<GoodsDetailsMapper> { GoodsDetailsMapper.Base(get(), get(), get()) }

    single<CatalogMapper> { CatalogMapper.Base(get(), get()) }

    single<AddressesMapper> { AddressesMapper.Base() }

    single<ProvideLocation> { ProvideLocation.Base(get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<GuessLocationUseCase> { GuessLocationUseCase.Base(get(), get()) }

    single<GoodsUseCase> { GoodsUseCase.Base(get(), get()) }

    single<GoodsDetailsUseCase> {
        GoodsDetailsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<FetchGoodsPage> { FetchGoodsPage.Base(get(), get(), get(), get()) }

    single<AddToCartUseCase> { AddToCartUseCase.Base(get(), get(), get()) }

    single<RemoveFromCartUseCase> { RemoveFromCartUseCase.Base(get(), get(), get()) }

    single<StartVerificationChannelUseCase> {
//        StartVerificationChannelUseCase.Base(get())
        StartVerificationChannelUseCase.Test()
    }
}
