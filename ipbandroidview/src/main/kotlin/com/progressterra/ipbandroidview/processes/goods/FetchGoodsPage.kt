package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardMapper
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface FetchGoodsPage {

    suspend operator fun invoke(
        id: String, pageNumber: Int, favorites: List<String>
    ): Result<Pair<Int, List<ProshkaStoreCardState>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val mapper: ProshkaStoreCardMapper,
        private val eCommerceRepo: IECommerceCoreRepository,
    ) : AbstractUseCase(scrmRepository, provideLocation), FetchGoodsPage {

        override suspend fun invoke(
            id: String, pageNumber: Int, favorites: List<String>
        ): Result<Pair<Int, List<ProshkaStoreCardState>>> = withToken { token ->
            val result = eCommerceRepo.getProductsByCategory(
                token, id, pageNumber, 10, 0, 0
            ).getOrThrow()
            result?.numberCurrentPage!! to result.listProducts!!.map { mapper.map(it) }
        }
    }
}