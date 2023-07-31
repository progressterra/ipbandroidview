package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.entities.GoodsFilter
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource

class GoodsSource(
    private val obtainAccessToken: ObtainAccessToken,
    private val productRepo: ProductRepository
) : AbstractSource<GoodsFilter, StoreCardState>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<StoreCardState>> =
        runCatching {
            productRepo.productList(
                obtainAccessToken().getOrThrow(), filter!!.toFilterAndSort().copy(
                    skip = skip,
                    take = take
                )
            ).getOrThrow()
                ?.map { it.toGoodsItem().toStoreCardState() }
                ?: emptyList()
        }
}