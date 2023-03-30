package com.progressterra.ipbandroidview.widgets.proshkaoffers

import com.progressterra.ipbandroidview.processes.partner.FetchOffersUseCase

interface FetchProshkaOffersUseCase {

    suspend operator fun invoke(): Result<ProshkaOffersState>

    class Base(
        private val fetchOffersUseCase: FetchOffersUseCase
    ) : FetchProshkaOffersUseCase {

        override suspend fun invoke(): Result<ProshkaOffersState> =
            fetchOffersUseCase().map { ProshkaOffersState(it) }
    }
}