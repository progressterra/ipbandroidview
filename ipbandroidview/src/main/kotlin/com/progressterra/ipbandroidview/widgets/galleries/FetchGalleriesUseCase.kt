package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidapi.api.catalog.CatalogRepository
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchGalleriesUseCase {

    suspend operator fun invoke(): Result<List<GalleriesState>>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val goodsUseCase: GoodsUseCase,
        private val productRepository: CatalogRepository
    ) : FetchGalleriesUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<List<GalleriesState>> = withToken { token ->
            IpbAndroidViewSettings.MAIN_SCREEN_CATEGORIES.map {
                val goods = goodsUseCase(GoodsFilter(categoryId = it)).getOrThrow()
                val category = productRepository.category(token, it).getOrThrow()
                val result = GalleriesState(
                    items = goods,
                    title = category?.name ?: ""
                )
                log(result)
                result
            }
        }
    }
}