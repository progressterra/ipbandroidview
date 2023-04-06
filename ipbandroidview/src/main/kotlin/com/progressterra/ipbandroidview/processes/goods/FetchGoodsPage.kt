package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.entities.GoodsItem
import com.progressterra.ipbandroidview.processes.AppSettings

interface FetchGoodsPage {

    suspend operator fun invoke(
        id: String,
        pageNumber: Int,
        favorites: List<String>
    ): Result<Pair<Int, List<GoodsItem>>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val mapper: StoreGoodsMapper,
        private val eCommerceRepo: IECommerceCoreRepository,
    ) : AbstractUseCase(scrmRepository, provideLocation), FetchGoodsPage {

        override suspend fun invoke(
            id: String, pageNumber: Int, favorites: List<String>
        ): Result<Pair<Int, List<GoodsItem>>> = withToken { token ->
            val result = eCommerceRepo.getProductsByCategory(
                token,
                id,
                pageNumber,
                AppSettings.PAGE_SIZE,
                0,
                0
            ).getOrThrow()
            result?.numberCurrentPage!! to result.listProducts!!.map {
                mapper.map(it, favorites.contains(it.idUnique!!))
            }
        }
    }
}