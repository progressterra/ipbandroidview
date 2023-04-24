package com.progressterra.ipbandroidview.processes.goods

import com.progressterra.ipbandroidapi.api.iecommerce.core.IECommerceCoreRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.storecard.StoreCardMapper
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

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


    class Test : FetchGoodsPage {

        override suspend fun invoke(
            id: String,
            pageNumber: Int,
            favorites: List<String>
        ): Result<Pair<Int, List<StoreCardState>>> = runCatching {
            if (pageNumber == 0) {
                0 to listOf(
                    StoreCardState(
                        id = "Kotek",
                        name = "Weston",
                        company = "Convallis",
                        price = SimplePrice(1000),
                        imageUrl = "https://placekitten.com/200/300",
                        loan = "Рассрочка: 2 платежа по 150 ₽"
                    ),
                    StoreCardState(
                        id = "Kotek 2",
                        name = "Weston",
                        company = "Convallis",
                        price = SimplePrice(2000),
                        imageUrl = "https://placekitten.com/200/300",
                        loan = "Рассрочка: 2 платежа по 150 ₽"
                    ),
                    StoreCardState(
                        id = "Kotek 3",
                        name = "Nombre",
                        company = "Convallis",
                        price = SimplePrice(5000),
                        imageUrl = "https://placekitten.com/200/300",
                        loan = "Рассрочка: 2 платежа по 150 ₽"
                    )
                )
            } else {
                pageNumber to emptyList()
            }
        }
    }
}