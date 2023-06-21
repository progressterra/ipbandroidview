package com.progressterra.ipbandroidview.features.orderdetails

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.widgets.orderitems.OrderItemsState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class OrderDetailsState(
    val id: String = "",
    val number: String = "",
    val status: String = "",
    val date: String = "",
    val count: String = "",
    val totalPrice: SimplePrice = SimplePrice(),
    val goods: OrderItemsState = OrderItemsState()
) : Parcelable
