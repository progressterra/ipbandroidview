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
                        title = "Акция 1",
                        image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                    ),
                    ProshkaOfferState(
                        title = "Акция 2",
                        image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                    ),
                    ProshkaOfferState(
                        title = "Акция 3",
                        image = "https://i.pinimg.com/736x/2a/5b/66/2a5b664425808595ba6eab3c9726573f.jpg"
                    )
                )
            )
        )
    }
}