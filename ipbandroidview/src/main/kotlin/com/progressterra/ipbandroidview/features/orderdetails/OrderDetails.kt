package com.progressterra.ipbandroidview.features.orderdetails

import androidx.compose.runtime.Immutable

@Immutable
data class OrderDetails(
    val id: String = "",
    val number: String = "",
    val status: String = "",
    val goods: List<OrderGoods> = emptyList()
) {

    data class OrderGoods(
        val id: String = "",
        val image: String = "",
        val inCartCounter: Int = 0
    )
}
