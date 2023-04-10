package com.progressterra.ipbandroidview.pages.cart

import com.progressterra.ipbandroidapi.api.iecommerce.cart.CartRepository
import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.mapper.CartGoodsMapper
import com.progressterra.ipbandroidview.processes.mapper.PriceMapper
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState
import com.progressterra.ipbandroidview.widgets.proshkacartitems.ProshkaCartItemsState
import com.progressterra.ipbandroidview.widgets.proshkacartsummary.ProshkaCartSummaryState

interface CartUseCase {

    suspend operator fun invoke(): Result<CartState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepo: CartRepository,
        private val iECommerceCoreRepository: IECommerceCoreRepository,
        private val cartGoodsMapper: CartGoodsMapper,
        private val priceMapper: PriceMapper
    ) : CartUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<CartState> = withToken { token ->
            val cart = cartRepo.getProductsInCart(token).getOrThrow()
            val price = priceMapper.map(cart?.drSaleRow?.sumOf { it.endPrice ?: 0.0 } ?: 0.0)
            val goods = buildSet {
                cart?.drSaleRow?.map { saleRow ->
                    saleRow.idrgGoodsInventory?.let { id ->
                        iECommerceCoreRepository.getProductDetailByIDRG(
                            id
                        ).getOrThrow()?.listProducts?.firstOrNull()?.let { goodsDetails ->
                            val count =
                                cart.drSaleRow?.count { it.idrfNomnclatura == goodsDetails.idrfNomenclatura }
                                    ?: 0
                            add(
                                cartGoodsMapper.map(goodsDetails)
                                    .copy(counter = CounterState(id, count))
                            )
                        }
                    }
                }
            }.toList()
            CartState(
                goods = ProshkaCartItemsState(goods),
                summary = ProshkaCartSummaryState(
                    total = price
                )
            )
        }
    }
}