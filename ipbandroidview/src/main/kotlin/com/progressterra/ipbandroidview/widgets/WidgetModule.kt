package com.progressterra.ipbandroidview.widgets

import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.widgets.documents.DocumentsUseCase
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.widgets.offers.FetchOffersUseCase
import com.progressterra.ipbandroidview.widgets.offers.OfferMapper
import org.koin.dsl.module

val widgetsModule = module {

    single<FetchBonusesTransactionsUseCase> {
        FetchBonusesTransactionsUseCase.Base(
            get(), get(), get()
        )
    }

    single<OfferMapper> {
        OfferMapper.Base(get())
    }

    single<FetchGalleriesUseCase> {
        FetchGalleriesUseCase.Base(get(), get(), get())
    }

    single<FetchOffersUseCase> {
        FetchOffersUseCase.Base(get(), get(), get(), get())
    }

    single<DocumentsUseCase> { DocumentsUseCase.Base(get(), get(), get(), get()) }
}