package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.processes.auth.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.auth.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.bankcards.FetchConfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.bankcards.FetchMainCardIdUseCase
import com.progressterra.ipbandroidview.processes.bankcards.FetchUnconfirmedBankCardsUseCase
import com.progressterra.ipbandroidview.processes.bankcards.HasCardsUseCase
import com.progressterra.ipbandroidview.processes.bonuses.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.processes.bonuses.FetchBonusSwitchUseCase
import com.progressterra.ipbandroidview.processes.bonuses.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.processes.bonuses.FetchBonusesUseCase
import com.progressterra.ipbandroidview.processes.bonuses.UseBonusesUseCase
import com.progressterra.ipbandroidview.processes.cart.AddDeliveryToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartInstallmentUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.FetchCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.chat.ChatsUseCase
import com.progressterra.ipbandroidview.processes.chat.CreateChatWithUserUseCase
import com.progressterra.ipbandroidview.processes.chat.DatingChatsPagingUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchChatsUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchDatingChatsUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchDocumentChatUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchMessagesUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchOrderChatUseCase
import com.progressterra.ipbandroidview.processes.chat.FetchWantThisDetailsChatUseCase
import com.progressterra.ipbandroidview.processes.chat.SendMessageUseCase
import com.progressterra.ipbandroidview.processes.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.processes.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.processes.checklist.ChecklistNonPagingUseCase
import com.progressterra.ipbandroidview.processes.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.processes.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.processes.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.processes.checklist.FetchArchivedAuditsUseCase
import com.progressterra.ipbandroidview.processes.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.processes.checklist.FetchOngoingAuditsUseCase
import com.progressterra.ipbandroidview.processes.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.processes.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.processes.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.processes.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.processes.connection.AcceptConnectUseCase
import com.progressterra.ipbandroidview.processes.connection.ConnectUseCase
import com.progressterra.ipbandroidview.processes.connection.ConnectionsUseCase
import com.progressterra.ipbandroidview.processes.connection.IncomingConnectionsUseCase
import com.progressterra.ipbandroidview.processes.connection.PendingConnectionsUseCase
import com.progressterra.ipbandroidview.processes.connection.SuccessInConnectionsUseCase
import com.progressterra.ipbandroidview.processes.connection.SuccessOutConnectionsUseCase
import com.progressterra.ipbandroidview.processes.connection.UserConnectionStatusUseCase
import com.progressterra.ipbandroidview.processes.dating.AvailableTargetsUseCase
import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.dating.ReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.UpdateDatingLocationUseCase
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.processes.docs.CitizenshipRepository
import com.progressterra.ipbandroidview.processes.docs.CreateAndSaveDocUseCase
import com.progressterra.ipbandroidview.processes.docs.DocumentValidationUseCase
import com.progressterra.ipbandroidview.processes.docs.DocumentsNotificationUseCase
import com.progressterra.ipbandroidview.processes.docs.DocumentsUseCase
import com.progressterra.ipbandroidview.processes.docs.FetchCardTemplateUseCase
import com.progressterra.ipbandroidview.processes.docs.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.processes.docs.FetchDocTemplateUseCase
import com.progressterra.ipbandroidview.processes.docs.FetchWantThisTemplateUseCase
import com.progressterra.ipbandroidview.processes.docs.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.goods.CatalogUseCase
import com.progressterra.ipbandroidview.processes.goods.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchFavoriteIds
import com.progressterra.ipbandroidview.processes.goods.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.goods.FetchSingleGoodsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.goods.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.processes.interests.ChangeInterestsUseCase
import com.progressterra.ipbandroidview.processes.interests.FetchInterestsUseCase
import com.progressterra.ipbandroidview.processes.location.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.processes.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.location.LocationToLocationPointUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocationUseCase
import com.progressterra.ipbandroidview.processes.location.SetupGeofencesUseCase
import com.progressterra.ipbandroidview.processes.location.SuggestionsUseCase
import com.progressterra.ipbandroidview.processes.media.AudioManager
import com.progressterra.ipbandroidview.processes.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.media.BitmapImageUseCase
import com.progressterra.ipbandroidview.processes.media.MakePhotoContract
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.media.PickPhotoContract
import com.progressterra.ipbandroidview.processes.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.media.VoiceManager
import com.progressterra.ipbandroidview.processes.occupation.FetchOccupationsUseCase
import com.progressterra.ipbandroidview.processes.occupation.SaveOccupationUseCase
import com.progressterra.ipbandroidview.processes.order.CommentUseCase
import com.progressterra.ipbandroidview.processes.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.processes.order.FetchReceiptUseCase
import com.progressterra.ipbandroidview.processes.order.FetchShippingAddressUseCase
import com.progressterra.ipbandroidview.processes.order.OrderDetailsUseCase
import com.progressterra.ipbandroidview.processes.order.OrdersUseCase
import com.progressterra.ipbandroidview.processes.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
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
import com.progressterra.ipbandroidview.processes.utils.CreateId
import com.progressterra.ipbandroidview.processes.utils.FetchVersionUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeDialogContract
import com.progressterra.ipbandroidview.processes.utils.MakeDialogUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeNotificationUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManagePermissionContract
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.OpenMapUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenPhoneUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.processes.utils.ShareTextUseCase
import com.progressterra.ipbandroidview.processes.utils.StartActivityContract
import com.progressterra.ipbandroidview.processes.utils.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.processes.wantthis.WantThisRequestsUseCase
import com.progressterra.ipbandroidview.processes.withdrawal.CreateNewWithdrawalUseCase
import com.progressterra.ipbandroidview.processes.withdrawal.FetchWithdrawalTransactionsUseCase
import com.progressterra.ipbandroidview.processes.withdrawal.FetchWithdrawalUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.binds
import org.koin.dsl.module

val processesModule = module {

    single<CopyTextUseCase> { CopyTextUseCase.Base(get()) }

    single<ObtainAccessToken> { ObtainAccessToken.Base(get(), get()) }

    single<FetchVersionUseCase> { FetchVersionUseCase.Base(get()) }

    single<ShareTextUseCase> { ShareTextUseCase.Base(get()) }

    single<OpenPhoneUseCase> { OpenPhoneUseCase.Base(get()) }

    single<OpenUrlUseCase> { OpenUrlUseCase.Base(get()) }

    single<SaveAddressUseCase> { SaveAddressUseCase.Base(get(), get(), get(), get()) }

    single<FetchFavoriteIds> { FetchFavoriteIds.Base(get(), get(), get()) }

    single<CheckPermissionUseCase> { CheckPermissionUseCase.Base(get(), get(), get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<StopRecordingUseCase> { StopRecordingUseCase.Base(get()) }

    single<StartRecordingUseCase> { StartRecordingUseCase.Base(get(), get(), get()) }

    single<StartAudioUseCase> { StartAudioUseCase.Base(get(), get()) }

    single<PauseAudioUseCase> { PauseAudioUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get(), get(), get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<ProvideLocationUseCase> { ProvideLocationUseCase.Base(get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<GuessLocationUseCase> { GuessLocationUseCase.Base(get()) }

    single<SaveDatingInfoUseCase> { SaveDatingInfoUseCase.Base(get(), get(), get(), get()) }

    single<ChangeInterestsUseCase> { ChangeInterestsUseCase.Base(get(), get(), get(), get()) }

    single<GoodsUseCase> {
        GoodsUseCase.Base(get(), get())
    }

    single<FetchGoodsPage> {
        FetchGoodsPage.Base(get(), get(), get(), get())
    }

    single<AddToCartUseCase> {
        AddToCartUseCase.Base(get(), get(), get(), get(), get(), get())
    }

    single<RemoveFromCartUseCase> {
        RemoveFromCartUseCase.Base(get(), get(), get(), get(), get())
    }

    single<StartVerificationChannelUseCase> {
        StartVerificationChannelUseCase.Base(get(), get(), get())
    }

    single<SaveDataUseCase> {
        SaveDataUseCase.Base(get(), get(), get(), get())
    }

    single<FetchUserUseCase> {
        FetchUserUseCase.Base(get(), get())
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
        AddToCartInstallmentUseCase.Base(get(), get(), get(), get(), get(), get())
    }

    single {
        StartActivityContract.Base()
    }.binds(arrayOf(StartActivityContract.Client::class, StartActivityContract.Activity::class))

    single {
        MakePhotoContract.Base()
    }.binds(arrayOf(MakePhotoContract.Activity::class, MakePhotoContract.Client::class))

    single {
        PickPhotoContract.Base()
    }.binds(arrayOf(PickPhotoContract.Activity::class, PickPhotoContract.Client::class))

    single {
        MakeDialogContract.Base()
    }.binds(arrayOf(MakeDialogContract.Activity::class, MakeDialogContract.Client::class))

    single<MakeDialogUseCase> { MakeDialogUseCase.Base(get(), get()) }

    single<FetchDocTemplateUseCase> {
        FetchDocTemplateUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<CreateAndSaveDocUseCase> {
        CreateAndSaveDocUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(), get(), get()
        )
    }

    single<FetchWithdrawalUseCase> { FetchWithdrawalUseCase.Base(get(), get(), get(), get()) }

    single<FetchSingleGoodsUseCase> { FetchSingleGoodsUseCase.Base(get(), get(), get(), get()) }

    single<MakeToastUseCase> { MakeToastUseCase.Base(androidContext()) }

    single<PickPhotoUseCase> { PickPhotoUseCase.Base(get(), get(), get()) }

    single<SaveAvatarUseCase> { SaveAvatarUseCase.Base(get(), get(), get(), get(), get()) }

    single<FetchAvatarUseCase> { FetchAvatarUseCase.Base(get(), get(), get(), get()) }

    single<HasCardsUseCase> { HasCardsUseCase.Base(get(), get(), get(), get(), get()) }

    single<FetchInterestsUseCase> { FetchInterestsUseCase.Base(get(), get(), get(), get()) }

    single<LocationToLocationPointUseCase> { LocationToLocationPointUseCase.Base(get(), get()) }

    single<ReadyToMeetUseCase> { ReadyToMeetUseCase.Base(get(), get(), get(), get(), get(), get()) }

    single<DeleteReadyToMeetUseCase> { DeleteReadyToMeetUseCase.Base(get(), get(), get(), get()) }

    single<SuccessOutConnectionsUseCase> { SuccessOutConnectionsUseCase.Base(get(), get()) }

    single<UserConnectionStatusUseCase> {
        UserConnectionStatusUseCase.Base(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<UsersAroundUseCase> {
        UsersAroundUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<UpdateDatingLocationUseCase> {
        UpdateDatingLocationUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<AvailableTargetsUseCase> { AvailableTargetsUseCase.Base(get(), get(), get(), get()) }

    single<FetchOccupationsUseCase> { FetchOccupationsUseCase.Base(get(), get(), get(), get()) }

    single<SaveOccupationUseCase> { SaveOccupationUseCase.Base(get(), get(), get(), get()) }

    single<FetchDatingUserUseCase> {
        FetchDatingUserUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<BitmapImageUseCase> { BitmapImageUseCase.Base(androidContext()) }

    single<ConnectionsUseCase> {
        ConnectionsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<IncomingConnectionsUseCase> { IncomingConnectionsUseCase.Base(get(), get()) }

    single<PendingConnectionsUseCase> { PendingConnectionsUseCase.Base(get(), get()) }

    single<SuccessInConnectionsUseCase> { SuccessInConnectionsUseCase.Base(get(), get()) }

    single<DatingChatsPagingUseCase> { DatingChatsPagingUseCase.Base(get(), get()) }

    single<FetchDatingChatsUseCase> { FetchDatingChatsUseCase.Base(get(), get(), get(), get()) }

    single<AcceptConnectUseCase> { AcceptConnectUseCase.Base(get(), get(), get(), get()) }

    single<ConnectUseCase> { ConnectUseCase.Base(get(), get(), get(), get()) }

    single<CreateChatWithUserUseCase> { CreateChatWithUserUseCase.Base(get(), get(), get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(
            get(),
            get(), get(), get()
        )
    }

    single<FetchGalleriesUseCase> {
        FetchGalleriesUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FetchBonusesTransactionsUseCase> {
        FetchBonusesTransactionsUseCase.Base(
            get(), get(), get()
        )
    }

    single<DocumentsUseCase> { DocumentsUseCase.Base(get(), get(), get(), get(), get(), get()) }

    single {
        ManagePermissionContract.Base()
    }.binds(
        arrayOf(
            ManagePermissionContract.Client::class,
            ManagePermissionContract.Activity::class
        )
    )

    single<OrdersUseCase> { OrdersUseCase.Base(get(), get()) }

    single<OrderDetailsUseCase> {
        OrderDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get(), get(), get(), get(), get())
    }

    single<CatalogUseCase> {
        CatalogUseCase.Base(get(), get(), get(), get())
    }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get(), get()) }

    single<FetchCartUseCase> {
        FetchCartUseCase.Base(get(), get(), get(), get(), get())
    }

    single<GoodsDetailsUseCase> {
        GoodsDetailsUseCase.Base(get(), get(), get(), get(), get(), get())
    }

    single<WantThisRequestsUseCase> {
        WantThisRequestsUseCase.Base(
            get(), get(), get(), get(), get()
        )
    }

    single<SuggestionsUseCase> {
        SuggestionsUseCase.Base(get())
    }

    single<FetchShippingAddressUseCase> {
        FetchShippingAddressUseCase.Base(get(), get(), get(), get())
    }

    single<AddDeliveryToCartUseCase> {
        AddDeliveryToCartUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FetchConfirmedBankCardsUseCase> {
        FetchConfirmedBankCardsUseCase.Base(
            get(),
            get(),
            get()
        )
    }

    single<FetchUnconfirmedBankCardsUseCase> {
        FetchUnconfirmedBankCardsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<CreateNewWithdrawalUseCase> {
        CreateNewWithdrawalUseCase.Base(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<FetchWithdrawalTransactionsUseCase> {
        FetchWithdrawalTransactionsUseCase.Base(
            get(),
            get()
        )
    }

    single<FetchCardTemplateUseCase> { FetchCardTemplateUseCase.Base(get(), get(), get()) }

    single<FetchMainCardIdUseCase> { FetchMainCardIdUseCase.Base(get(), get(), get(), get()) }

    single<FetchDocumentChatUseCase> { FetchDocumentChatUseCase.Base(get(), get(), get(), get()) }

    single<ChatsUseCase> {
        ChatsUseCase.Base(get(), get())
    }

    single<SendMessageUseCase> { SendMessageUseCase.Base(get(), get(), get(), get()) }

    single<FetchChatsUseCase> { FetchChatsUseCase.Base(get(), get(), get(), get(), get()) }

    single<FetchMessagesUseCase> { FetchMessagesUseCase.Base(get(), get()) }

    single<FetchOrderChatUseCase> { FetchOrderChatUseCase.Base(get(), get(), get(), get()) }

    single<FetchWantThisDetailsChatUseCase> {
        FetchWantThisDetailsChatUseCase.Base(
            get(),
            get(),
            get(), get()
        )
    }

    single<FetchBonusSwitchUseCase> {
        FetchBonusSwitchUseCase.Base(get(), get())
    }

    single<FetchReceiptUseCase> {
        FetchReceiptUseCase.Base(get(), get(), get(), get())
    }

    single<DocumentsNotificationUseCase> {
        DocumentsNotificationUseCase.Base(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<CommentUseCase> {
        CommentUseCase.Base(get(), get(), get(), get())
    }

    single<FetchWantThisTemplateUseCase> { FetchWantThisTemplateUseCase.Base(get(), get(), get()) }

    single<FavoriteGoodsUseCase> {
        FavoriteGoodsUseCase.Base()
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base() }

    single<ConfirmOrderUseCase> {
        ConfirmOrderUseCase.Base(get(), get(), get(), get(), get())
    }

    single<SaveDocumentsUseCase> {
        SaveDocumentsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<CreateId> {
        CreateId.Base()
    }

    single<ManageResources> { ManageResources.Base(androidContext()) }


    single<FetchBonusesUseCase> {
        FetchBonusesUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FetchPaymentMethods> {
        FetchPaymentMethods.Base()
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get())
    }

    single<FetchCitizenshipsUseCase> { FetchCitizenshipsUseCase.Base(get(), get(), get()) }

    single<SetupGeofencesUseCase> {
        SetupGeofencesUseCase.Base(
            get(),
            get(),
            androidContext(),
            get(qualifier = StringQualifier("geofencing")),
            get(),
            get(),
            get()
        )
    }

    single<MakeNotificationUseCase> {
        MakeNotificationUseCase.Base(
            androidContext(),
            get(qualifier = StringQualifier("iconId")),
            get(qualifier = StringQualifier("channelId")),
            get(qualifier = StringQualifier("channelName")),
            get(qualifier = StringQualifier("activity"))
        )
    }

    single<AllOrganizationsUseCase> { AllOrganizationsUseCase.Base(get(), get(), get(), get()) }

    single<FetchPartnerUseCase> { FetchPartnerUseCase.Base(get(), get(), get(), get(), get()) }

    single<OrganizationAuditsUseCase> { OrganizationAuditsUseCase.Base(get(), get(), get(), get()) }

    single<ChecklistNonPagingUseCase> { ChecklistNonPagingUseCase.Base(get(), get(), get(), get()) }

    single<ChecklistUseCase> { ChecklistUseCase.Base(get(), get()) }

    single<CreateDocumentUseCase> { CreateDocumentUseCase.Base(get(), get(), get(), get()) }

    single<CheckMediaDetailsUseCase> {
        CheckMediaDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<DocumentChecklistUseCase> {
        DocumentChecklistUseCase.Base(get(), get(), get(), get())
    }

    single<FetchExistingAuditUseCase> {
        FetchExistingAuditUseCase.Base(get(), get(), get(), get())
    }

    single<SendResultOnEmailUseCase> {
        SendResultOnEmailUseCase.Base(get(), get(), get(), get())
    }

    single<UpdateAnswerUseCase> {
        UpdateAnswerUseCase.Base(get(), get(), get(), get(), get(), get())
    }

    single<FinishDocumentUseCase> {
        FinishDocumentUseCase.Base(get(), get(), get(), get())
    }

    single<VoiceManager> { VoiceManager.Base(get()) }

    single<AudioManager> { AudioManager.Base(get()) }

    single<FetchOngoingAuditsUseCase> { FetchOngoingAuditsUseCase.Base(get(), get()) }

    single<FetchArchivedAuditsUseCase> { FetchArchivedAuditsUseCase.Base(get(), get()) }
}
