package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartInstallmentUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.data.CitizenshipRepository
import com.progressterra.ipbandroidview.processes.dating.AcceptConnectUseCase
import com.progressterra.ipbandroidview.processes.dating.AvailableTargetsUseCase
import com.progressterra.ipbandroidview.processes.dating.ConnectUseCase
import com.progressterra.ipbandroidview.processes.dating.ConnectionsUseCase
import com.progressterra.ipbandroidview.processes.dating.CreateChatWithUserUseCase
import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.dating.IncomingConnectionsUseCase
import com.progressterra.ipbandroidview.processes.dating.PendingConnectionsUseCase
import com.progressterra.ipbandroidview.processes.dating.ReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.SuccessConnectionsUseCase
import com.progressterra.ipbandroidview.processes.dating.UpdateDatingLocationUseCase
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.processes.docs.CreateAndSaveDocUseCase
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.docs.FetchDocTemplateUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.goods.FetchSingleGoodsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.processes.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.location.LocationToLocationPointUseCase
import com.progressterra.ipbandroidview.processes.location.OpenMapUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.occupacion.FetchOccupationsUseCase
import com.progressterra.ipbandroidview.processes.occupacion.FetchUserOccupationUseCase
import com.progressterra.ipbandroidview.processes.occupacion.SaveOccupationUseCase
import com.progressterra.ipbandroidview.processes.payments.FetchWithdrawalUseCase
import com.progressterra.ipbandroidview.processes.payments.HasCardsUseCase
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
import com.progressterra.ipbandroidview.processes.user.SaveDatingInfoUseCase
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

    single<ProvideLocationUseCase> { ProvideLocationUseCase.Base(get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<GuessLocationUseCase> { GuessLocationUseCase.Base(get()) }

    single<SaveDatingInfoUseCase> { SaveDatingInfoUseCase.Base(get(), get()) }

    single<ChangeInterestsUseCase> { ChangeInterestsUseCase.Base(get(), get()) }

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

    single<HasCardsUseCase> { HasCardsUseCase.Base(get(), get(), get()) }

    single<FetchInterestsUseCase> { FetchInterestsUseCase.Base(get(), get()) }

    single<LocationToLocationPointUseCase> { LocationToLocationPointUseCase.Base() }

    single<ReadyToMeetUseCase> { ReadyToMeetUseCase.Base(get(), get()) }

    single<DeleteReadyToMeetUseCase> { DeleteReadyToMeetUseCase.Base(get(), get()) }

    factory<UsersAroundUseCase> { UsersAroundUseCase.Base(get(), get()) }

    single<UpdateDatingLocationUseCase> { UpdateDatingLocationUseCase.Base(get(), get()) }

    single<AvailableTargetsUseCase> { AvailableTargetsUseCase.Base(get(), get()) }

    single<FetchUserOccupationUseCase> { FetchUserOccupationUseCase.Base(get(), get()) }

    single<FetchOccupationsUseCase> { FetchOccupationsUseCase.Base(get(), get()) }

    single<SaveOccupationUseCase> { SaveOccupationUseCase.Base(get(), get()) }

    single<FetchDatingUserUseCase> { FetchDatingUserUseCase.Base(get(), get(), get()) }

    factory<ConnectionsUseCase> { ConnectionsUseCase.Base(get(), get(), get(), get()) }

    single<IncomingConnectionsUseCase> { IncomingConnectionsUseCase.Base(get(), get()) }

    single<PendingConnectionsUseCase> { PendingConnectionsUseCase.Base(get(), get()) }

    single<SuccessConnectionsUseCase> { SuccessConnectionsUseCase.Base(get(), get()) }

    single<DatingChatsPagingUseCase> { DatingChatsPagingUseCase.Base(get(), get()) }

    single<FetchDatingChatsUseCase> { FetchDatingChatsUseCase.Base(get(), get()) }

    single<AcceptConnectUseCase> { AcceptConnectUseCase.Base(get(), get()) }

    single<ConnectUseCase> { ConnectUseCase.Base(get(), get()) }

    single<CreateChatWithUserUseCase> { CreateChatWithUserUseCase.Base(get(), get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(
            get(),
            get()
        )
    }
}
