package com.progressterra.ipbandroidview.features

import com.progressterra.ipbandroidview.processes.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.processes.FetchBonusesUseCase
import com.progressterra.ipbandroidview.processes.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import org.koin.dsl.module

val featuresModule = module {

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
}