package com.progressterra.ipbandroidview.pages.wantthisrequests

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.ScreenState

@Immutable
data class WantThisRequestsState(
    val screen: ScreenState = ScreenState.LOADING,
    val items: List<WantThisCardState> = emptyList()
) {

    fun uItems(newItems: List<WantThisCardState>) = copy(items = newItems)

    fun uScreenState(newScreenState: ScreenState) = copy(screen = newScreenState)
}