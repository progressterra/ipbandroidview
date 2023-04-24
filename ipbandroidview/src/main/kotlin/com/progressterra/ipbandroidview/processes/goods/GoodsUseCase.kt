package com.progressterra.ipbandroidview.processes.goods

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface GoodsUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>>

    class Base(
        private val fetchGoodsPage: FetchGoodsPage
    ) : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>> =
            runCatching {
                Pager(PagingConfig(10)) {
                    GoodsSource(
                        categoryId = categoryId,
                        fetchGoodsPage = fetchGoodsPage
                    )
                }.flow
            }
    }

    class Test : GoodsUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>> =
            Result.success(
                flowOf(
                    PagingData.from(
                        listOf(
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
                    )
                )
            )
    }
}