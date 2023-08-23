package com.progressterra.ipbandroidview.widgets

import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.widgets.documents.DocumentsUseCase
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase
import org.koin.dsl.module

val widgetsModule = module {

    single<FetchBonusesTransactionsUseCase> {
        FetchBonusesTransactionsUseCase.Base(
            get(), get(), get()
        )
    }

    single<FetchGalleriesUseCase> {
        FetchGalleriesUseCase.Base(get(), get(), get())
    }

    single<DocumentsUseCase> { DocumentsUseCase.Base(get(), get(), get(), get()) }
}