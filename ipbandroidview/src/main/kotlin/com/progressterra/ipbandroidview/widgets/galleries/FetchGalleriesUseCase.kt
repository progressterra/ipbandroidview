package com.progressterra.ipbandroidview.widgets.galleries

import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchGalleriesUseCase {

    suspend operator fun invoke(): Result<List<GalleriesState>>

    class Base(
        private val goodsUseCase: GoodsUseCase,
        private val manageResources: ManageResources
    ) : FetchGalleriesUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(): Result<List<GalleriesState>> = handle {
            IpbAndroidViewSettings.MAIN_SCREEN_CATEGORIES.map {
                val goods = goodsUseCase(GoodsFilter(categoryId = it)).getOrThrow()
                GalleriesState(
                    items = goods,
                    title = manageResources.string(R.string.recommended_goods)
                )
            }
        }
    }
}