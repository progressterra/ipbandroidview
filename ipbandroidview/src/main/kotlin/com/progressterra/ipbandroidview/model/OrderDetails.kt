package com.progressterra.ipbandroidview.model

import androidx.compose.runtime.Immutable

@Immutable
data class OrderDetails(
    val id: String = "",
    val number: String = "",
    val status: String = "",
    val goods: List<OrderGoods> = emptyList()
)
