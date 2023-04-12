package com.progressterra.ipbandroidview.widgets.proshkaoffers

import com.progressterra.ipbandroidview.features.proshkaoffer.ProshkaOfferState

interface FetchProshkaOffersUseCase {

    suspend operator fun invoke(): Result<ProshkaOffersState>

    class Base(
        private val fetchOffersUseCase: FetchOffersUseCase
    ) : FetchProshkaOffersUseCase {

        override suspend fun invoke(): Result<ProshkaOffersState> =
            fetchOffersUseCase().map { ProshkaOffersState(it.map { item -> ProshkaOfferState(item) }) }
    }

    class Test : FetchProshkaOffersUseCase {

        override suspend fun invoke(): Result<ProshkaOffersState> = Result.success(
            ProshkaOffersState(
                listOf(
                    ProshkaOfferState(
                        id = "1",
                        title = "Title 1",
                        image = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
                    ),
                    ProshkaOfferState(
                        id = "3",
                        title = "Title 2",
                        image = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
                    ),
                    ProshkaOfferState(
                        id = "2",
                        title = "Title 3",
                        image = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png"
                    )
                )
            )
        )
    }
}