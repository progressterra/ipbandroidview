package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.features.cartcard.CartCardState
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterState

@Immutable
data class GoodsItem(
    override val id: String = "",
    val categoryId: String = "",
    val name: String = "",
    val oldPrice: SimplePrice = SimplePrice(),
    val price: SimplePrice = SimplePrice(),
    val image: String = "",
    val images: List<String> = emptyList(),
    val properties: List<Pair<String, String>> = emptyList(),
    val count: Int = 0,
    val description: String = "",
    val installment: Installment = Installment()
) : Id {

    fun toWantThisCardState() = WantThisCardState(
        id = id,
        image = image,
        price = price,
        oldPrice = oldPrice,
        installment = installment,
        counter = CounterState(
            id = id, count = count
        ),
        status = TypeStatusDoc.CONFIRMED,
        name = name
    )

    fun toOrderCardState(): OrderCardState = OrderCardState(
        id = id,
        name = name,
        oldPrice = oldPrice * count,
        price = price * count,
        image = image,
        installment = installment,
        properties = properties
    )

    fun toStoreCardState(): StoreCardState = StoreCardState(
        id = id,
        name = name,
        oldPrice = oldPrice,
        price = price,
        image = image,
        installment = installment,
        counter = CounterState(
            id = id, count = count
        )
    )

    fun toCartCardState(): CartCardState = CartCardState(
        id = id,
        name = name,
        oldPrice = oldPrice,
        price = price,
        image = image,
        installment = installment,
        counter = CounterState(
            id = id, count = count
        ),
        properties = properties
    )
}