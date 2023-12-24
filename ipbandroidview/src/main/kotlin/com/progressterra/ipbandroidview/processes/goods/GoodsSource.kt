package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractSource

class GoodsSource(
    private val obtainAccessToken: ObtainAccessToken,
    private val productRepo: ProductRepository
) : AbstractSource<GoodsFilter, StoreCardState>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<Pair<Int, List<StoreCardState>>> =
        runCatching {
            val response = productRepo.productList(
                obtainAccessToken().getOrThrow(), filter!!.toFilterAndSort().copy(
                    skip = skip,
                    take = take
                )
            ).getOrThrow() ?: emptyList()
            response.size to response.map { it.toGoodsItem().toStoreCardState() }
        }
}