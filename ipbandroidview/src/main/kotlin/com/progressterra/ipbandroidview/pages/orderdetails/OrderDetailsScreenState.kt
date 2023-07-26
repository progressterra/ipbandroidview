package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Immutable
import arrow.optics.optics
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
@optics
data class OrderDetailsScreenState(
    val details: OrderDetailsState = OrderDetailsState(),
    override val id: String = "",
    val screenState: ScreenState = ScreenState.LOADING
) : Id {

    companion object
}