package com.progressterra.ipbandroidview.pages.chats

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.widgets.chats.ChatsState

@Immutable
data class ChatsScreenState(
    val chats: ChatsState = ChatsState(),
    val screen: ScreenState = ScreenState.LOADING,
    val search: SearchState = SearchState()
)
