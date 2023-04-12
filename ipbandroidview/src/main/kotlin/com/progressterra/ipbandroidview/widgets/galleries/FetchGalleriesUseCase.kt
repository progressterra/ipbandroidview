package com.progressterra.ipbandroidview.widgets.galleries

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.processes.goods.GoodsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

interface FetchGalleriesUseCase {

    suspend operator fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>>

    class Base(
        private val goodsUseCase: GoodsUseCase
    ) : FetchGalleriesUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>> =
            goodsUseCase(categoryId)
    }

    class Test : FetchGalleriesUseCase {

        override suspend fun invoke(categoryId: String): Result<Flow<PagingData<StoreCardState>>> =
            Result.success(
                flowOf(
                    PagingData.from(
                        listOf(
                            StoreCardState(
                                id = "1",
                                name = "Товар 1",
                                company = "Компания 1",
                                price = SimplePrice(1000),
                                imageUrl = "https://hips.hearstapps.com/hmg-prod/images/sunflower-1508785046.jpg?resize=480:*",
                                loan = "Рассрочка: 2 платежа по 55 ₽"
                            ),
                            StoreCardState(
                                id = "2",
                                name = "Товар 2",
                                company = "Компания 2",
                                price = SimplePrice(150),
                                imageUrl = "https://images.unsplash.com/photo-1578185708140-55c94bb9426f?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmVhdXRpZnVsJTIwZmxvd2Vyc3xlbnwwfHwwfHw%3D&w=1000&q=80",
                                loan = "Рассрочка: 2 платежа по 55 ₽"
                            ),
                            StoreCardState(
                                id = "3",
                                name = "Товар 3",
                                company = "Компания 3",
                                price = SimplePrice(450),
                                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVk0d_WhOQxU1z0Ay85XcZff9Q6JFvQjER2g&usqp=CAU",
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