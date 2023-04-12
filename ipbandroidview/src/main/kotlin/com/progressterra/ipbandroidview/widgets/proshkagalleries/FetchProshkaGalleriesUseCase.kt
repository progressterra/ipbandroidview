package com.progressterra.ipbandroidview.widgets.proshkagalleries

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardState
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface FetchProshkaGalleriesUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>>

    class Base(
        private val goodsUseCase: GoodsUseCase
    ) : FetchProshkaGalleriesUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>> =
            goodsUseCase(categoryId)
    }

    class Test : FetchProshkaGalleriesUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<ProshkaStoreCardState>>> =
            Result.success(
                flowOf(
                    PagingData.from(
                        listOf(
                            ProshkaStoreCardState(
                                id = "1",
                                name = "Name 1",
                                company = "Company 1",
                                price = SimplePrice(123),
                                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                                loan = "Рассрочка: 2 платежа по 55 ₽"
                            ),
                            ProshkaStoreCardState(
                                id = "2",
                                name = "Name 2",
                                company = "Company 2",
                                price = SimplePrice(123),
                                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                                loan = "Рассрочка: 2 платежа по 55 ₽"
                            ),
                            ProshkaStoreCardState(
                                id = "3",
                                name = "Name 3",
                                company = "Company 3",
                                price = SimplePrice(123),
                                imageUrl = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                                loan = "Рассрочка: 2 платежа по 55 ₽"
                            )
                        )
                    )
                )
            )
    }

    companion object {

        const val HOT = "ef1f4abf-5060-4f19-be1c-4a3f56beb89d"

        const val NEW = "04d76bfe-3a6c-44fc-ab56-13b0fce61287"
    }
}