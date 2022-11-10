package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.domain.usecase.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.usecase.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.usecase.CatalogUseCase
import com.progressterra.ipbandroidview.domain.usecase.CheckMediaDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.usecase.CurrentLocationMarkerUseCase
import com.progressterra.ipbandroidview.domain.usecase.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.domain.usecase.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.usecase.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.usecase.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.FetchExistingAuditUseCase
import com.progressterra.ipbandroidview.domain.usecase.FilteredGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.FinishDocumentUseCase
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

    single<CurrentLocationMarkerUseCase> {
        CurrentLocationMarkerUseCase.Base(get())
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get(), get())
    }

    single<SuggestionUseCase> {
        SuggestionUseCase.Base(get(), get(), get())
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
        GoodsPageUseCase.Base(get(), get(), get(), get(), get())
    }

    single<GoodsUseCase> {
        GoodsUseCase.Base(get())
    }

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
}