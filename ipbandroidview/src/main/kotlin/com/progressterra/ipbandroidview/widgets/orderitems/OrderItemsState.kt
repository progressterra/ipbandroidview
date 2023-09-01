package com.progressterra.ipbandroidview.widgets.orderitems

import android.os.Parcelable
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import kotlinx.parcelize.Parcelize


@Parcelize
data class OrderItemsState(
    val items: List<OrderCardState> = emptyList()
) : Parcelable