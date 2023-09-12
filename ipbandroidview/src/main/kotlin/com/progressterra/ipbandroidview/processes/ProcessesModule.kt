package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartInstallmentUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository
import com.progressterra.ipbandroidview.processes.docs.CreateAndSaveDocUseCase
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.docs.FetchDocTemplateUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.goods.FetchSingleGoodsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.location.OpenMapUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.processes.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.payments.FetchWithdrawalUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.processes.user.FetchAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserProfileUseCase
import com.progressterra.ipbandroidview.processes.user.FetchUserUseCase
import com.progressterra.ipbandroidview.processes.user.LogoutUseCase
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAddressUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.SaveCitizenshipUseCase
import com.progressterra.ipbandroidview.processes.user.SaveDataUseCase
import com.progressterra.ipbandroidview.processes.utils.CopyTextUseCase
import com.progressterra.ipbandroidview.processes.utils.FetchVersionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenPhoneUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.processes.utils.ShareTextUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val processesModule = module {

    single<CopyTextUseCase> { CopyTextUseCase.Base(get()) }

    single<ObtainAccessToken> { ObtainAccessToken.Base(get(), get()) }

    single<FetchVersionUseCase> { FetchVersionUseCase.Base(get()) }

    single<ShareTextUseCase> { ShareTextUseCase.Base(get()) }

    single<OpenPhoneUseCase> { OpenPhoneUseCase.Base(get()) }

    single<OpenUrlUseCase> { OpenUrlUseCase.Base(get()) }

    single<SaveAddressUseCase> { SaveAddressUseCase.Base(get(), get()) }

    single<FetchFavoriteIds> { FetchFavoriteIds.Base(get()) }

    single<CheckPermissionUseCase> { CheckPermissionUseCase.Base(get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<StopRecordingUseCase> { StopRecordingUseCase.Base(get()) }

    single<StartRecordingUseCase> { StartRecordingUseCase.Base(get(), get(), get()) }

    single<StartAudioUseCase> { StartAudioUseCase.Base(get(), get()) }

    single<PauseAudioUseCase> { PauseAudioUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<ProvideLocation> { ProvideLocation.Base(get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<GuessLocationUseCase> { GuessLocationUseCase.Base(get()) }

    single<GoodsUseCase> {
        GoodsUseCase.Base(get(), get())
    }

    single<FetchGoodsPage> {
        FetchGoodsPage.Base(get(), get())
    }

    single<AddToCartUseCase> {
        AddToCartUseCase.Base(get(), get(), get())
    }

    single<RemoveFromCartUseCase> {
        RemoveFromCartUseCase.Base(get(), get(), get())
    }

    single<StartVerificationChannelUseCase> {
        StartVerificationChannelUseCase.Base(get())
    }

    single<SaveDataUseCase> {
        SaveDataUseCase.Base(get(), get())
    }

    single<FetchUserUseCase> {
        FetchUserUseCase.Base()
    }

    single<FetchUserProfileUseCase> {
        FetchUserProfileUseCase.Base()
    }

    single<CitizenshipRepository> {
        CitizenshipRepository.Base()
    }

    single<LogoutUseCase> {
        LogoutUseCase.Base()
    }

    single<DocumentValidationUseCase> { DocumentValidationUseCase.Base() }

    single<SaveCitizenshipUseCase> {
        SaveCitizenshipUseCase.Base()
    }

    single<AddToCartInstallmentUseCase> {
        AddToCartInstallmentUseCase.Base(get(), get(), get())
    }

    single<FetchDocTemplateUseCase> { FetchDocTemplateUseCase.Base(get(), get(), get(), get()) }

    single<CreateAndSaveDocUseCase> {
        CreateAndSaveDocUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<FetchWithdrawalUseCase> { FetchWithdrawalUseCase.Base(get(), get()) }

    single<FetchSingleGoodsUseCase> { FetchSingleGoodsUseCase.Base(get(), get()) }

    single<MakeToastUseCase> { MakeToastUseCase.Base(androidContext()) }

    single<PickPhotoUseCase> { PickPhotoUseCase.Base(get()) }

    single<SaveAvatarUseCase> { SaveAvatarUseCase.Base(get(), get(), get()) }

    single<FetchAvatarUseCase> { FetchAvatarUseCase.Base(get(), get()) }
}
