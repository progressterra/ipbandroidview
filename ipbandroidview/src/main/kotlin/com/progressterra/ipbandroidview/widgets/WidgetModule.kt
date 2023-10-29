package com.progressterra.ipbandroidview.widgets

import com.progressterra.ipbandroidview.processes.DocumentsUseCase
import com.progressterra.ipbandroidview.processes.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.processes.FetchGalleriesUseCase
import org.koin.dsl.module

val widgetsModule = module {

    single<FetchGalleriesUseCase> {
        FetchGalleriesUseCase.Base(get(), get(), get(), get(), get())
    }

    single<FetchBonusesTransactionsUseCase> {
        FetchBonusesTransactionsUseCase.Base(
            get(), get(), get()
        )
    }

    single<DocumentsUseCase> { DocumentsUseCase.Base(get(), get(), get(), get(), get(), get()) }
}