package com.progressterra.ipbandroidview.features

import com.progressterra.ipbandroidview.features.addresssuggestions.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.features.addresssuggestions.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUseCase
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesUseCase
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardMapper
import org.koin.dsl.module

val featuresModule = module {

    single<ProshkaStoreCardMapper> {
        ProshkaStoreCardMapper.Base(get())
    }

    single<ProshkaBonusesUseCase> {
        ProshkaBonusesUseCase.Base(get(), get(), get(), get())
//        ProshkaBonusesUseCase.Test()
    }

    single<FetchPaymentMethods> {
        FetchPaymentMethods.Base()
    }

    single<ChooseSuggestionUseCase> {
        ChooseSuggestionUseCase.Base(get())
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get(), get())
    }

    single<SuggestionUseCase> { SuggestionUseCase.Base(get(), get()) }
}