package com.progressterra.ipbandroidview.pages.orderdetails

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.features.orderdetails.OrderDetailsState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class OrderDetailsScreenState(
    val details: OrderDetailsState = OrderDetailsState(),
    override val id: String = "",
    val screenState: ScreenState = ScreenState.LOADING
) : Id {

    fun uId(newId: String) = copy(id = newId)

    fun uScreenState(newScreenState: ScreenState) = copy(screenState = newScreenState)

    fun uDetails(newDetails: OrderDetailsState) = copy(details = newDetails)
}