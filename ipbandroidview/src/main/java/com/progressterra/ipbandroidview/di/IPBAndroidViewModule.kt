package com.progressterra.ipbandroidview.di

import com.google.android.gms.location.LocationServices
import com.progressterra.ipbandroidapi.di.iPBAndroidAPIModule
import com.progressterra.ipbandroidview.base.ManagePermission
import com.progressterra.ipbandroidview.base.PermissionCache
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.domain.*
import com.progressterra.ipbandroidview.domain.filter.SuggestionFilter
import com.progressterra.ipbandroidview.domain.mapper.AddressGuesserMapper
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.CityViewModel
import com.progressterra.ipbandroidview.ui.confirmationcode.ConfirmationCodeViewModel
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

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get(), get())
    }

    single<SuggestionUseCase> {
        SuggestionUseCase.Base(get(), get(), get())
    }

    single<GuessLocationUseCase> {
        GuessLocationUseCase.Base(get(), get())
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

    viewModel {
        SplashViewModel()
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

    viewModel {
        CityViewModel(get(), get(), get(), get())
    }
}