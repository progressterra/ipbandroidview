package com.progressterra.ipbandroidview.widgets

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.widgets.bonusestransactions.FetchBonusesTransactionsUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryMethodMapper
import com.progressterra.ipbandroidview.widgets.deliverypicker.DeliveryPickerValidUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.FetchAvailableDeliveryUseCase
import com.progressterra.ipbandroidview.widgets.deliverypicker.SetDeliveryAddressUseCase
import com.progressterra.ipbandroidview.widgets.edituser.CitizenshipSuggestionsUseCase
import com.progressterra.ipbandroidview.widgets.edituser.FetchAdaptiveEntriesUseCase
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

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            FetchAvailableDeliveryUseCase.Test()

        } else {
            FetchAvailableDeliveryUseCase.Base(
                get(), get(), get(), get()
            )
        }
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

    single {
        if (IpbAndroidViewSettings.TEST_MODE) {
            FetchGalleriesUseCase.Test()
        } else {
            FetchGalleriesUseCase.Base(get())
        }
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

    single<FetchAdaptiveEntriesUseCase> {
        FetchAdaptiveEntriesUseCase.Base(get(), get(), get())
    }

    single<CitizenshipSuggestionsUseCase> {
        CitizenshipSuggestionsUseCase.Base(get())
    }
}