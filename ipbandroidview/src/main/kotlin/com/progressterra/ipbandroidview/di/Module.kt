package com.progressterra.ipbandroidview.di

import com.google.android.gms.location.LocationServices
import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.core.ManagePermission
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.PermissionCache
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.domain.*
import com.progressterra.ipbandroidview.domain.filter.SuggestionFilter
import com.progressterra.ipbandroidview.domain.mapper.AddressGuesserMapper
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.auditchecks.AuditChecksViewModel
import com.progressterra.ipbandroidview.ui.audits.AuditsViewModel
import com.progressterra.ipbandroidview.ui.audits.Document
import com.progressterra.ipbandroidview.ui.city.CityViewModel
import com.progressterra.ipbandroidview.ui.confirmationcode.ConfirmationCodeViewModel
import com.progressterra.ipbandroidview.ui.organizationaudits.OrganizationAuditsViewModel
import com.progressterra.ipbandroidview.ui.organizations.Organization
import com.progressterra.ipbandroidview.ui.organizations.OrganizationsViewModel
import com.progressterra.ipbandroidview.ui.signin.SignInViewModel
import com.progressterra.ipbandroidview.ui.signup.SignUpViewModel
import com.progressterra.ipbandroidview.ui.splash.SplashViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.binds
import org.koin.dsl.module

@Suppress("unused")
val iPBAndroidViewModule = module {

    includes(iPBAndroidAPIModule)

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

    factory<ManageResources> {
        ManageResources.Base(androidContext())
    }

    factory<SuggestionMapper> {
        SuggestionMapper.Base()
    }

    factory<SuggestionFilter> { SuggestionFilter.Base() }

    factory<AddressGuesserMapper> {
        AddressGuesserMapper.Base()
    }

    factory {
        LocationServices.getFusedLocationProviderClient(androidContext())
    }

    single {
        PermissionCache.Base()
    }.binds(arrayOf(PermissionCache::class, ManagePermission::class))

    single<ProvideLocation> {
        ProvideLocation.Base(get())
    }

    single<OrganizationAuditsUseCase> {
        OrganizationAuditsUseCase.Base(get(), get(), get(), get())
    }

    single<AllDocumentsUseCase> { AllDocumentsUseCase.Base(get(), get(), get(), get()) }

    single<DocumentChecklistUseCase> { DocumentChecklistUseCase.Base(get(), get(), get(), get()) }

    viewModel {
        AuditsViewModel(get())
    }

    viewModel {
        SplashViewModel()
    }

    viewModel {
        OrganizationsViewModel(get())
    }

    viewModel {
        SignInViewModel(get())
    }

    viewModel {
        SignUpViewModel(get())
    }

    viewModel {
        ConfirmationCodeViewModel(get(), get())
    }

    viewModel { (organization: Organization) ->
        OrganizationAuditsViewModel(organization, get())
    }

    viewModel {
        CityViewModel(get(), get(), get(), get())
    }

    viewModel { (document: Document) ->
        AuditChecksViewModel(document, get())
    }
}