package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.domain.usecase.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.usecase.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.usecase.CartUseCase
import com.progressterra.ipbandroidview.domain.usecase.CatalogUseCase
import com.progressterra.ipbandroidview.domain.usecase.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.CurrentLocationMarkerUseCase
import com.progressterra.ipbandroidview.domain.usecase.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.domain.usecase.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.FastAddToCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.FavoriteIdsUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.GoodsPageUseCase
import com.progressterra.ipbandroidview.domain.usecase.GoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.usecase.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.domain.usecase.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.SuggestionUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.domain.usecase.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.ui.goodsdetails.GoodsDetailsViewModel
import org.koin.dsl.module

val useCasesModule = module {

    factory<AllDocumentsUseCase> { AllDocumentsUseCase.Base(get(), get(), get(), get()) }

    factory<DocumentChecklistUseCase> {
        DocumentChecklistUseCase.Base(
            get(),
            get(),
            get(),
            get()
        )
    }

    factory<GoodsDetailsUseCase> { GoodsDetailsUseCase.Base(get(), get(), get()) }

    factory<ChecklistUseCase> { ChecklistUseCase.Base(get(), get(), get(), get()) }

    factory<UpdateAnswerUseCase> {
        UpdateAnswerUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    factory<FinishDocumentUseCase> { FinishDocumentUseCase.Base(get(), get(), get()) }

    factory<CreateDocumentUseCase> { CreateDocumentUseCase.Base(get(), get(), get()) }

    factory<FetchExistingAuditUseCase> { FetchExistingAuditUseCase.Base(get(), get(), get()) }

    factory<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get())
    }

    factory<StartVerificationChannelUseCase> {
        StartVerificationChannelUseCase.Base(get())
    }

    factory<UpdatePersonalInfoUseCase> {
        UpdatePersonalInfoUseCase.Base(get(), get(), get())
    }

    factory<UpdateFirebaseCloudMessagingTokenUseCase> {
        UpdateFirebaseCloudMessagingTokenUseCase.Base(get(), get())
    }

    factory<CurrentLocationMarkerUseCase> {
        CurrentLocationMarkerUseCase.Base(get())
    }

    factory<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get(), get())
    }

    factory<SuggestionUseCase> {
        SuggestionUseCase.Base(get(), get(), get())
    }

    factory<GuessLocationUseCase> {
        GuessLocationUseCase.Base(get(), get())
    }

    factory<AllOrganizationsUseCase> {
        AllOrganizationsUseCase.Base(get(), get(), get(), get())
    }

    factory<OrganizationAuditsUseCase> {
        OrganizationAuditsUseCase.Base(get(), get(), get(), get())
    }

    factory<CheckMediaDetailsUseCase> {
        CheckMediaDetailsUseCase.Base(get(), get(), get(), get(), get())
    }

    factory<GoodsPageUseCase> {
        GoodsPageUseCase.Base(get(), get(), get(), get())
    }

    factory<GoodsUseCase> {
        GoodsUseCase.Base(get(), get())
    }

    factory<FavoriteIdsUseCase> { FavoriteIdsUseCase.Base(get(), get(), get()) }

    factory<FilteredGoodsUseCase> {
        FilteredGoodsUseCase.Base(
            get(),
            get(),
            get(),
            get(),
            get(),
            get()
        )
    }

    factory<ModifyFavoriteUseCase> { ModifyFavoriteUseCase.Base(get(), get(), get()) }

    factory<CatalogUseCase> { CatalogUseCase.Base(get(), get(), get(), get()) }

    factory<FavoriteGoodsUseCase> { FavoriteGoodsUseCase.Base(get(), get(), get(), get(), get()) }

    factory<CartUseCase> { CartUseCase.Base(get(), get(), get(), get(), get(), get(), get()) }

    factory<FastAddToCartUseCase> { FastAddToCartUseCase.Base(get(), get(), get()) }

    factory<FastRemoveFromCartUseCase> { FastRemoveFromCartUseCase.Base(get(), get(), get()) }
}