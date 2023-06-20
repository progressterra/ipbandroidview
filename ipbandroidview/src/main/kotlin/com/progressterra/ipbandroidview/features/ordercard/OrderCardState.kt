package com.progressterra.ipbandroidview.features.ordercard

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Installment
import com.progressterra.ipbandroidview.entities.SimplePrice

@Immutable
data class OrderCardState(
    val id: String = "",
    val name: String = "",
    val price: SimplePrice = SimplePrice(),
    val imageUrl: String = "",
    val installment: Installment = Installment(),
    val count: Int = 0,
    val oldPrice: SimplePrice = SimplePrice(),
    val properties: Map<String, String> = emptyMap()
)
