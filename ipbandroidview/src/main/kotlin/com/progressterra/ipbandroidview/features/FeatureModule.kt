package com.progressterra.ipbandroidview.features

import com.progressterra.ipbandroidview.features.addresssuggestions.CurrentLocationSuggestionsUseCase
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.currentcitizenship.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.widgets.edituser.EditUserValidUseCase
import org.koin.dsl.module

val featuresModule = module {

    single<BonusesUseCase> {
        BonusesUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FetchPaymentMethods> {
        FetchPaymentMethods.Base()
    }

    single<CurrentLocationSuggestionsUseCase> {
        CurrentLocationSuggestionsUseCase.Base(get(), get())
    }

    single<EditUserValidUseCase> {
        EditUserValidUseCase.Base()
    }

    single<FetchCitizenshipsUseCase> { FetchCitizenshipsUseCase.Base(get()) }
}