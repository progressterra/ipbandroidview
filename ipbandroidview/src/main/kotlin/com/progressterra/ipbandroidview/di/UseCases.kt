package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.domain.usecase.AskPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CopyTextUseCase
import com.progressterra.ipbandroidview.domain.usecase.OpenPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.domain.usecase.ShareTextUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.domain.usecase.ambassador.InviteUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.chat.FetchChatUseCase
import com.progressterra.ipbandroidview.domain.usecase.chat.SendMessageUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationsOverviewUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.AvailableDeliveryUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.PaymentMethodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.usecase.location.OpenMapUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartAudioUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.CreateDeliveryOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.domain.usecase.qr.CreateQr
import com.progressterra.ipbandroidview.domain.usecase.store.CartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.CatalogUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.CreateQrUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastAddToCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.domain.usecase.store.FetchGoodsPage
import com.progressterra.ipbandroidview.domain.usecase.store.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.OrdersUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.PromoGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.SizeTableUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.TransactionsUseCase
import com.progressterra.ipbandroidview.domain.usecase.suggestion.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.suggestion.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.domain.usecase.suggestion.SuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.LogoutUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistsUseCase
import org.koin.dsl.module

val useCasesModule = module {

    single<AllDocumentsUseCase> { AllDocumentsUseCase.Base(get(), get(), get(), get()) }

    single<DocumentChecklistUseCase> {
        DocumentChecklistUseCase.Base(
            get(),
            get(),
            get(),
            get()
        )
    }

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

    single<ChecklistUseCase> { ChecklistUseCase.Base(get(), get(), get(), get()) }

    single<UpdateAnswerUseCase> {
        UpdateAnswerUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<FinishDocumentUseCase> { FinishDocumentUseCase.Base(get(), get(), get(), get()) }

    single<CreateDocumentUseCase> { CreateDocumentUseCase.Base(get(), get(), get()) }

    single<FetchExistingAuditUseCase> { FetchExistingAuditUseCase.Base(get(), get(), get()) }

    single<EndVerificationChannelUseCase> { EndVerificationChannelUseCase.Base(get()) }

    single<StartVerificationChannelUseCase> { StartVerificationChannelUseCase.Base(get()) }

    single<UpdatePersonalInfoUseCase> { UpdatePersonalInfoUseCase.Base(get(), get(), get()) }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(get(), get())
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get(), get())
    }

    single<SuggestionUseCase> { SuggestionUseCase.Base(get(), get()) }

    single<GuessLocationUseCase> { GuessLocationUseCase.Base(get(), get()) }

    single<AllOrganizationsUseCase> {
        AllOrganizationsUseCase.Base(get(), get(), get(), get())
    }

    single<OrganizationAuditsUseCase> {
        OrganizationAuditsUseCase.Base(get(), get(), get(), get())
    }

    single<CheckMediaDetailsUseCase> {
        CheckMediaDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FetchGoodsPage> { FetchGoodsPage.Base(get(), get(), get(), get()) }

    single<GoodsUseCase> { GoodsUseCase.Base(get(), get()) }

    single<FetchFavoriteIds> { FetchFavoriteIds.Base(get(), get(), get()) }

    single<FilteredGoodsUseCase> {
        FilteredGoodsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    single<CatalogUseCase> { CatalogUseCase.Base(get(), get(), get(), get()) }

    single<FavoriteGoodsUseCase> { FavoriteGoodsUseCase.Base(get(), get(), get(), get(), get()) }

    single<CartUseCase> { CartUseCase.Base(get(), get(), get(), get(), get(), get(), get()) }

    single<FastAddToCartUseCase> { FastAddToCartUseCase.Base(get(), get(), get()) }

    single<FastRemoveFromCartUseCase> { FastRemoveFromCartUseCase.Base(get(), get(), get()) }

    single<UserExistsUseCase> { UserExistsUseCase.Base() }

    single<AvailableBonusesUseCase> { AvailableBonusesUseCase.Base(get(), get(), get(), get()) }

    single<TransactionsUseCase> { TransactionsUseCase.Base(get(), get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get()) }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get()) }

    single<FetchUserUseCase> { FetchUserUseCase.Base(get(), get()) }

    single<FetchUserNameUseCase> { FetchUserNameUseCase.Base() }

    single<FetchUserEmailUseCase> { FetchUserEmailUseCase.Base() }

    single<FetchUserPhoneUseCase> { FetchUserPhoneUseCase.Base() }

    single<FetchUserBirthdayUseCase> { FetchUserBirthdayUseCase.Base() }

    single<NeedDetailsUseCase> { NeedDetailsUseCase.Base() }

    single<NeedAddressUseCase> { NeedAddressUseCase.Base() }

    single<ConfirmOrderUseCase> { ConfirmOrderUseCase.Base(get(), get(), get()) }

    single<CreateDeliveryOrderUseCase> { CreateDeliveryOrderUseCase.Base(get(), get(), get()) }

    single<AvailableDeliveryUseCase> { AvailableDeliveryUseCase.Base(get(), get(), get(), get()) }

    single<OrdersUseCase> {
        OrdersUseCase.Base(get(), get(), get(), get(), get(), get(), get(), get())
    }

    single<SaveUserAddressUseCase> { SaveUserAddressUseCase.Base(get(), get(), get(), get()) }

    single<PaymentMethodsUseCase> { PaymentMethodsUseCase.Base() }

    single<FetchUserAddressUseCase> { FetchUserAddressUseCase.Base() }

    single<SetDeliveryAddressUseCase> { SetDeliveryAddressUseCase.Base(get(), get(), get()) }

    single<ChooseSuggestionUseCase> { ChooseSuggestionUseCase.Base(get()) }

    single<CreateQr> { CreateQr.Base() }

    single<CreateQrUseCase> { CreateQrUseCase.Base(get(), get(), get()) }

    single<SendResultOnEmailUseCase> { SendResultOnEmailUseCase.Base(get(), get(), get()) }

    single<FetchChatUseCase> { FetchChatUseCase.Base(get(), get(), get()) }

    single<FetchUserIdUseCase> { FetchUserIdUseCase.Base() }

    single<SendMessageUseCase> { SendMessageUseCase.Base(get(), get(), get(), get(), get()) }

    single<InviteUseCase> { InviteUseCase.Test() }

    single<FetchPartnerUseCase> { FetchPartnerUseCase.Base(get(), get(), get(), get(), get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get()) }

    single<StartAudioUseCase> { StartAudioUseCase.Base(get(), get()) }

    single<PauseAudioUseCase> { PauseAudioUseCase.Base(get()) }

    single<CheckPermissionUseCase> { CheckPermissionUseCase.Base(get()) }

    single<StopRecordingUseCase> { StopRecordingUseCase.Base(get()) }

    single<StartRecordingUseCase> { StartRecordingUseCase.Base(get(), get(), get()) }

    single<OpenMapUseCase> { OpenMapUseCase.Base(get()) }

    single<CopyTextUseCase> { CopyTextUseCase.Base(get()) }

    single<OpenPhoneUseCase> { OpenPhoneUseCase.Base(get()) }

    single<OpenUrlUseCase> { OpenUrlUseCase.Base(get()) }

    single<ShareTextUseCase> { ShareTextUseCase.Base(get()) }

    single<SizeTableUseCase> { SizeTableUseCase.Base(get(), get(), get()) }

    single<PromoGoodsUseCase> {
        PromoGoodsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    single<OrganizationsOverviewUseCase> {
        OrganizationsOverviewUseCase.Base(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<LogoutUseCase> {
        LogoutUseCase.Base()
    }
}