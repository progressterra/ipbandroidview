package com.progressterra.ipbandroidview.widgets

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerValidUseCase
import com.progressterra.ipbandroidview.widgets.documents.DocumentsUseCase
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.widgets.offers.FetchOffersUseCase
import com.progressterra.ipbandroidview.widgets.offers.OfferMapper
import org.koin.dsl.module

val widgetsModule = module {

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            FetchBonusesTransactionsUseCase.Test()
        } else {
            FetchBonusesTransactionsUseCase.Base(
                get(), get(), get(), get()
            )
        }
    }

    single<OfferMapper> {
        OfferMapper.Base(get())
    }

    single<FetchGalleriesUseCase> {
        FetchGalleriesUseCase.Base(get(), get())
    }

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            FetchOffersUseCase.Test()
        } else {
            FetchOffersUseCase.Base(get(), get(), get(), get())
        }
    }

    single<DeliveryPickerValidUseCase> {
        DeliveryPickerValidUseCase.Base()
    }

    single<DocumentsUseCase> { DocumentsUseCase.Base(get(), get(), get(), get(), get()) }
}