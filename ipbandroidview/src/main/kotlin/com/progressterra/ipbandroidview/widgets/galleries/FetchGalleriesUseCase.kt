package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.CacheUseCase
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

interface FetchGalleriesUseCase : CacheUseCase<GalleriesState> {

    suspend operator fun invoke(id: String)

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val goodsUseCase: GoodsUseCase,
        private val productRepository: CatalogRepository
    ) : FetchGalleriesUseCase, AbstractCacheTokenUseCase<GalleriesState>(obtainAccessToken) {

        override suspend fun invoke(id: String) {
            withCache { token ->
                val goods = goodsUseCase(GoodsFilter(categoryId = id)).getOrThrow()
                val category = productRepository.category(token, id).getOrThrow()
                val result = GalleriesState(
                    items = goods,
                    title = category?.name ?: "",
                    id = id,
                    state = StateColumnState(id = id, state = ScreenState.SUCCESS)
                )
                log(result)
                result
            }
        }
    }
}