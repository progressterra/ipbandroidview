package com.progressterra.ipbandroidview.di

import com.progressterra.ipbandroidview.domain.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.AllOrganizationsUseCase
import com.progressterra.ipbandroidview.domain.ChecklistUseCase
import com.progressterra.ipbandroidview.domain.CreateDocumentUseCase
import com.progressterra.ipbandroidview.domain.CurrentLocationMarkerUseCase
import com.progressterra.ipbandroidview.domain.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.domain.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.domain.EndVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.FinishDocumentUseCase
import com.progressterra.ipbandroidview.domain.GuessLocationUseCase
import com.progressterra.ipbandroidview.domain.OrganizationAuditsUseCase
import com.progressterra.ipbandroidview.domain.StartVerificationChannelUseCase
import com.progressterra.ipbandroidview.domain.SuggestionUseCase
import com.progressterra.ipbandroidview.domain.UpdateAnswerUseCase
import com.progressterra.ipbandroidview.domain.UpdateFirebaseCloudMessagingTokenUseCase
import com.progressterra.ipbandroidview.domain.UpdatePersonalInfoUseCase
import com.progressterra.ipbandroidview.domain.fetchexisting.FetchExistingAuditUseCase
import org.koin.dsl.module

val useCasesModule = module {

    single<AllDocumentsUseCase> { AllDocumentsUseCase.Base(get(), get(), get(), get()) }

    single<DocumentChecklistUseCase> {
        DocumentChecklistUseCase.Base(
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

    single<FetchExistingAuditUseCase> { FetchExistingAuditUseCase.Base(get(), get(), get(), get()) }

    single<EndVerificationChannelUseCase> {
        EndVerificationChannelUseCase.Base(get())
    }

    single<StartVerificationChannelUseCase> {
        StartVerificationChannelUseCase.Base(get())
    }

    single<UpdatePersonalInfoUseCase> {
        UpdatePersonalInfoUseCase.Base(get(), get())
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
}