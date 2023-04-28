package com.progressterra.ipbandroidview.pages.orderstatus

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.mainorreceipt.MainOrReceiptState
import com.progressterra.ipbandroidview.features.orderid.OrderIdState
import com.progressterra.ipbandroidview.features.orderoverview.OrderOverviewState
import kotlinx.parcelize.Parcelize

@Parcelize
@Immutable
data class OrderStatusState(
    val orderId: OrderIdState = OrderIdState(),
    val orderOverview: OrderOverviewState = OrderOverviewState(),
    val mainOrReceipt: MainOrReceiptState = MainOrReceiptState()
) : Parcelable