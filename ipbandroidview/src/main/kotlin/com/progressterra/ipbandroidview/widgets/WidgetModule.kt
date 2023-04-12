package com.progressterra.ipbandroidview.widgets

import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryMethodMapper
import com.progressterra.ipbandroidview.widgets.deliverypicker.FetchAvailableDeliveryUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.widgets.proshkagalleries.FetchProshkaGalleriesUseCase
import com.progressterra.ipbandroidview.widgets.proshkaoffers.FetchOffersUseCase
import com.progressterra.ipbandroidview.widgets.proshkaoffers.FetchProshkaOffersUseCase
import com.progressterra.ipbandroidview.widgets.proshkaoffers.OfferMapper
import org.koin.dsl.module

val widgetsModule = module {

    single<FetchBonusesTransactionsUseCase> {
        FetchBonusesTransactionsUseCase.Base(
            get(), get(), get(), get()
        )
    }

    single<FetchAvailableDeliveryUseCase> {
        FetchAvailableDeliveryUseCase.Base(
            get(), get(), get(), get(), get()
        )
    }

    single<OfferMapper> {
        OfferMapper.Base(get())
    }

    single<DeliveryMethodMapper> {
        DeliveryMethodMapper.Base(get(), get())
    }

    single<SetDeliveryAddressUseCase> {
        SetDeliveryAddressUseCase.Base(get(), get(), get())
    }

    single<FetchProshkaGalleriesUseCase> {
        FetchProshkaGalleriesUseCase.Base(get())
//        FetchProshkaGalleriesUseCase.Test()
    }

    single<FetchOffersUseCase> {
        FetchOffersUseCase.Base(get(), get(), get(), get())
    }

    single<FetchProshkaOffersUseCase> {
        FetchProshkaOffersUseCase.Base(get())
//        FetchProshkaOffersUseCase.Test()
    }
}