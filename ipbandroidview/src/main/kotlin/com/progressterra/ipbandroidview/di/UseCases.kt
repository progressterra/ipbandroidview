package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.processes.usecase.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.usecase.CheckPermissionUseCase
import com.progressterra.ipbandroidview.processes.usecase.CopyTextUseCase
import com.progressterra.ipbandroidview.processes.usecase.FetchVersionUseCase
import com.progressterra.ipbandroidview.processes.usecase.OpenPhoneUseCase
import com.progressterra.ipbandroidview.processes.usecase.OpenUrlUseCase
import com.progressterra.ipbandroidview.processes.usecase.ShareTextUseCase
import com.progressterra.ipbandroidview.processes.usecase.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.processes.usecase.ambassador.InviteUseCase
import com.progressterra.ipbandroidview.processes.usecase.bonus.ProshkaBonusesUseCase
import com.progressterra.ipbandroidview.processes.usecase.bonus.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.processes.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.processes.usecase.chat.FetchChatUseCase
import com.progressterra.ipbandroidview.processes.usecase.chat.SendMessageUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.AllDocumentsUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.OrganizationsOverviewUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.processes.usecase.checklist.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.FetchAvailableDeliveryUseCase
import com.progressterra.ipbandroidview.processes.usecase.delivery.PaymentMethodsUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.processes.usecase.location.GuessLocationUseCase
import com.progressterra.ipbandroidview.processes.usecase.location.OpenMapUseCase
import com.progressterra.ipbandroidview.processes.usecase.media.AudioProgressUseCase
import com.progressterra.ipbandroidview.processes.usecase.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.usecase.media.PauseAudioUseCase
import com.progressterra.ipbandroidview.processes.usecase.media.StartAudioUseCase
import com.progressterra.ipbandroidview.processes.usecase.media.StartRecordingUseCase
import com.progressterra.ipbandroidview.processes.usecase.media.StopRecordingUseCase
import com.progressterra.ipbandroidview.processes.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.processes.usecase.order.CreateDeliveryOrderUseCase
import com.progressterra.ipbandroidview.processes.partner.FetchPartnerUseCase
import com.progressterra.ipbandroidview.processes.usecase.qr.CreateQr
import com.progressterra.ipbandroidview.processes.cart.CartUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.CatalogUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.CreateQrUseCase
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.favorites.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.FetchFavoriteIds
import com.progressterra.ipbandroidview.processes.goods.FetchGoodsPage
import com.progressterra.ipbandroidview.processes.goods.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.OrdersUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.SizeTableUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.TransactionsUseCase
import com.progressterra.ipbandroidview.processes.usecase.suggestion.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.processes.usecase.suggestion.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.processes.usecase.suggestion.SuggestionUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.LogoutUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.NeedAddressUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.NeedDetailsUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.processes.usecase.user.UserExistsUseCase
import org.koin.dsl.module

val useCasesModule = module {

    single<AllDocumentsUseCase> { AllDocumentsUseCase.Base(get(), get(), get(), get()) }

    single<DocumentChecklistUseCase> {
        DocumentChecklistUseCase.Base(
            get(), get(), get(), get()
        )
    }

    single<GoodsDetailsUseCase> {
        GoodsDetailsUseCase.Base(
            get(), get(), get(), get(), get(), get()
        )
    }

    single<ChecklistUseCase> { ChecklistUseCase.Base(get(), get(), get(), get()) }

    single<UpdateAnswerUseCase> {
        UpdateAnswerUseCase.Base(
            get(), get(), get(), get(), get(), get()
        )
    }

    single<FetchVersionUseCase> {
        FetchVersionUseCase.Base(
            get()
        )
    }

    single<FinishDocumentUseCase> { FinishDocumentUseCase.Base(get(), get(), get()) }

    single<CreateDocumentUseCase> { CreateDocumentUseCase.Base(get(), get(), get()) }

    single<FetchExistingAuditUseCase> { FetchExistingAuditUseCase.Base(get(), get(), get()) }

    single<EndVerificationChannelUseCase> { EndVerificationChannelUseCase.Base(get(), get()) }

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
            get(), get(), get(), get(), get(), get()
        )
    }

    single<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    single<CatalogUseCase> { CatalogUseCase.Base(get(), get(), get(), get()) }

    single<FavoriteGoodsUseCase> { FavoriteGoodsUseCase.Base(get(), get(), get(), get(), get()) }

    single<CartUseCase> { CartUseCase.Base(get(), get(), get(), get(), get(), get(), get()) }

    single<AddToCartUseCase> { AddToCartUseCase.Base(get(), get(), get()) }

    single<RemoveFromCartUseCase> { RemoveFromCartUseCase.Base(get(), get(), get()) }

    single<UserExistsUseCase> { UserExistsUseCase.Base() }

    single<ProshkaBonusesUseCase> { ProshkaBonusesUseCase.Base(get(), get(), get(), get()) }

    single<TransactionsUseCase> { TransactionsUseCase.Base(get(), get(), get(), get()) }

    single<CancelUseBonusesUseCase> { CancelUseBonusesUseCase.Base(get(), get(), get()) }

    single<UseBonusesUseCase> { UseBonusesUseCase.Base(get(), get(), get()) }

    single<FetchUserNameUseCase> { FetchUserNameUseCase.Base() }

    single<FetchUserEmailUseCase> { FetchUserEmailUseCase.Base() }

    single<FetchUserPhoneUseCase> { FetchUserPhoneUseCase.Base() }

    single<FetchUserBirthdayUseCase> { FetchUserBirthdayUseCase.Base() }

    single<NeedDetailsUseCase> { NeedDetailsUseCase.Base() }

    single<NeedAddressUseCase> { NeedAddressUseCase.Base() }

    single<ConfirmOrderUseCase> { ConfirmOrderUseCase.Base(get(), get(), get()) }

    single<CreateDeliveryOrderUseCase> { CreateDeliveryOrderUseCase.Base(get(), get(), get()) }

    single<FetchAvailableDeliveryUseCase> { FetchAvailableDeliveryUseCase.Base(get(), get(), get(), get()) }

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

    single<SendResultOnEmailUseCase> { SendResultOnEmailUseCase.Base(get(), get(), get(), get()) }

    single<FetchChatUseCase> { FetchChatUseCase.Base(get(), get(), get()) }

    single<FetchUserIdUseCase> { FetchUserIdUseCase.Base() }

    single<SendMessageUseCase> { SendMessageUseCase.Base(get(), get(), get(), get(), get()) }

    single<InviteUseCase> { InviteUseCase.Test() }

    single<FetchPartnerUseCase> { FetchPartnerUseCase.Base(get(), get(), get(), get(), get()) }

    single<AskPermissionUseCase> { AskPermissionUseCase.Base(get()) }

    single<AudioProgressUseCase> { AudioProgressUseCase.Base(get()) }

    single<MakePhotoUseCase> { MakePhotoUseCase.Base(get(), get(), get(), get()) }

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
            get(), get(), get(), get(), get(), get(), get()
        )
    }

    single<OrganizationsOverviewUseCase> {
        OrganizationsOverviewUseCase.Base(
            get(), get(), get(), get()
        )
    }

    single<LogoutUseCase> {
        LogoutUseCase.Base()
    }
}