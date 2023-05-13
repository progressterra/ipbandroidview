package com.progressterra.ipbandroidview.widgets.similargoods

import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.features.storecard.StoreCardState

interface FetchSimilarGoodsUseCase {

    suspend operator fun invoke(goodsId: String): Result<List<StoreCardState>>

    class Test : FetchSimilarGoodsUseCase {

        override suspend fun invoke(goodsId: String): Result<List<StoreCardState>> = runCatching {
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
        }
    }
}