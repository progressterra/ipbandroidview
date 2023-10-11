package com.progressterra.ipbandroidview.features

import com.progressterra.ipbandroidview.features.addresssuggestions.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.features.bonuses.FetchBonusesUseCase
import com.progressterra.ipbandroidview.features.currentcitizenship.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import org.koin.dsl.module

val featuresModule = module {

    factory<FetchBonusesUseCase> {
        FetchBonusesUseCase.Base(get(), get(), get())
    }

    single<FetchPaymentMethods> {
        FetchPaymentMethods.Base()
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get())
    }

    single<FetchCitizenshipsUseCase> { FetchCitizenshipsUseCase.Base(get()) }
}