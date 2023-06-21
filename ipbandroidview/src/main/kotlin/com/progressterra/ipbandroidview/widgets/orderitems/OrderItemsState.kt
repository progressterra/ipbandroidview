package com.progressterra.ipbandroidview.widgets.orderitems

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.ordercard.OrderCardState
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class OrderItemsState(
    val items: List<OrderCardState> = emptyList()
) : Parcelable