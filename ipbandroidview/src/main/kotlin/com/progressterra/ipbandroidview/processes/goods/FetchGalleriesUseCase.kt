package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractCacheTokenUseCase
import com.progressterra.ipbandroidview.shared.mvi.CacheUseCase
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState

interface FetchGalleriesUseCase : CacheUseCase<GalleriesState> {

    suspend operator fun invoke(id: String)

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val goodsUseCase: GoodsUseCase,
        private val productRepository: CatalogRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchGalleriesUseCase, AbstractCacheTokenUseCase<GalleriesState>(obtainAccessToken,
        makeToastUseCase, manageResources
    ) {

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
                result
            }
        }
    }
}