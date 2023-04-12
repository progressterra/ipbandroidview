package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.features.storecard.StoreCardMapper
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase

interface FetchGoodsPage {

    suspend operator fun invoke(
        id: String, pageNumber: Int, favorites: List<String>
    ): Result<Pair<Int, List<StoreCardState>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val mapper: StoreCardMapper,
        private val eCommerceRepo: IECommerceCoreRepository,
    ) : AbstractUseCase(scrmRepository, provideLocation), FetchGoodsPage {

        override suspend fun invoke(
            id: String, pageNumber: Int, favorites: List<String>
        ): Result<Pair<Int, List<StoreCardState>>> = withToken { token ->
            val result = eCommerceRepo.getProductsByCategory(
                token, id, pageNumber, 10, 0, 0
            ).getOrThrow()
            result?.numberCurrentPage!! to result.listProducts!!.map { mapper.map(it) }
        }
    }
}