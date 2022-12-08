package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.domain.usecase.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.CreateQrUseCase
import com.progressterra.ipbandroidview.domain.usecase.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.domain.usecase.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.usecase.NotificationUseCase
import com.progressterra.ipbandroidview.domain.usecase.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.SuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.AvailableBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.CancelUseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.bonus.UseBonusesUseCase
import com.progressterra.ipbandroidview.domain.usecase.chat.FetchChatUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.SendResultOnEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.AvailableDeliveryUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.PaymentMethodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.delivery.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.order.CreateDeliveryOrderUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.CartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.CatalogUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastAddToCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FavoriteIdsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsPageUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.OrdersUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.TransactionsUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserBirthdayUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserEmailUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserNameUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserPhoneUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.NeedDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.SaveUserAddressUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.domain.usecase.user.UserExistUseCase
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

    single<FinishDocumentUseCase> { FinishDocumentUseCase.Base(get(), get(), get()) }

    single<CreateDocumentUseCase> { CreateDocumentUseCase.Base(get(), get(), get()) }

    single<FetchExistingAuditUseCase> { FetchExistingAuditUseCase.Base(get(), get(), get()) }

    single<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get())
    }

    single<StartVerificationChannelUseCase> {
        StartVerificationChannelUseCase.Base(get())
    }

    single<UpdatePersonalInfoUseCase> {
        UpdatePersonalInfoUseCase.Base(get(), get(), get())
    }

    single<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(get(), get())
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get(), get())
    }

    single<SuggestionUseCase> {
        SuggestionUseCase.Base(get(), get())
    }

    single<GuessLocationUseCase> {
        GuessLocationUseCase.Base(get(), get())
    }

    single<AllOrganizationsUseCase> {
        AllOrganizationsUseCase.Base(get(), get(), get(), get())
    }

    single<OrganizationAuditsUseCase> {
        OrganizationAuditsUseCase.Base(get(), get(), get(), get())
    }

    single<CheckMediaDetailsUseCase> {
        CheckMediaDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    single<GoodsPageUseCase> {
        GoodsPageUseCase.Base(get(), get(), get(), get())
    }

    single<GoodsUseCase> {
        GoodsUseCase.Base(get(), get())
    }

    single<FavoriteIdsUseCase> { FavoriteIdsUseCase.Base(get(), get(), get()) }

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

    single<UserExistUseCase> {
        UserExistUseCase.Base()
    }

    single<AvailableBonusesUseCase> {
        AvailableBonusesUseCase.Base(get(), get(), get(), get())
    }

    single<TransactionsUseCase> {
        TransactionsUseCase.Base(get(), get(), get(), get())
    }

    single<CancelUseBonusesUseCase> {
        CancelUseBonusesUseCase.Base(get(), get(), get())
    }

    single<UseBonusesUseCase> {
        UseBonusesUseCase.Base(get(), get(), get())
    }

    single<FetchUserUseCase> {
        FetchUserUseCase.Base(get(), get())
    }

    single<FetchUserNameUseCase> {
        FetchUserNameUseCase.Base()
    }

    single<FetchUserEmailUseCase> {
        FetchUserEmailUseCase.Base()
    }

    single<FetchUserPhoneUseCase> {
        FetchUserPhoneUseCase.Base()
    }

    single<FetchUserBirthdayUseCase> {
        FetchUserBirthdayUseCase.Base()
    }

    single<NeedDetailsUseCase> {
        NeedDetailsUseCase.Base()
    }

    single<NeedAddressUseCase> {
        NeedAddressUseCase.Base()
    }

    single<ConfirmOrderUseCase> {
        ConfirmOrderUseCase.Base(get(), get(), get())
    }

    single<CreateDeliveryOrderUseCase> {
        CreateDeliveryOrderUseCase.Base(get(), get(), get())
    }

    single<AvailableDeliveryUseCase> { AvailableDeliveryUseCase.Base(get(), get(), get(), get()) }

    single<OrdersUseCase> {
        OrdersUseCase.Base(get(), get(), get(), get(), get(), get(), get(), get())
    }

    single<SaveUserAddressUseCase> { SaveUserAddressUseCase.Base(get(), get(), get(), get()) }

    single<PaymentMethodsUseCase> { PaymentMethodsUseCase.Base() }

    single<FetchUserAddressUseCase> { FetchUserAddressUseCase.Base() }

    single<SetDeliveryAddressUseCase> { SetDeliveryAddressUseCase.Base(get(), get(), get()) }

    single<ChooseSuggestionUseCase> { ChooseSuggestionUseCase.Base(get()) }

    single<CreateQrUseCase> { CreateQrUseCase.Base() }

    single<NotificationUseCase> { NotificationUseCase.Base(get(), get(), get(), get(), get()) }

    single<SendResultOnEmailUseCase> { SendResultOnEmailUseCase.Base(get(), get(), get()) }

    single<FetchChatUseCase> { FetchChatUseCase.Base(get(), get(), get()) }

    single<FetchUserIdUseCase> { FetchUserIdUseCase.Base() }
}