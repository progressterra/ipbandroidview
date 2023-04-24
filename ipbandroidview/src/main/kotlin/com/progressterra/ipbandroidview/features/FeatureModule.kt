package com.progressterra.ipbandroidview.features

import com.progressterra.ipbandroidview.features.addresssuggestions.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.features.addresssuggestions.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUseCase
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.storecard.StoreCardMapper
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase
import org.koin.dsl.module

val featuresModule = module {

    single<StoreCardMapper> {
        StoreCardMapper.Base(get())
    }

    single<BonusesUseCase> {
        BonusesUseCase.Base(get(), get(), get(), get())
//        BonusesUseCase.Test()
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

    single<EditUserValidUseCase> {
        EditUserValidUseCase.Base()
    }
}