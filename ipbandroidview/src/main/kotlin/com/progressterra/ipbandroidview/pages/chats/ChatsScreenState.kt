package com.progressterra.ipbandroidview.pages.chats

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState
import com.progressterra.ipbandroidview.widgets.chats.ChatsState

@Immutable
data class ChatsScreenState(
    val chats: ChatsState = ChatsState(),
    val screen: StateColumnState = StateColumnState(),
    val search: SearchState = SearchState()
)
