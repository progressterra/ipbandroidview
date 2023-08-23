package com.progressterra.ipbandroidview.pages.chats

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.search.SearchState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxState
import com.progressterra.ipbandroidview.widgets.chats.ChatsState

@Immutable
data class ChatsScreenState(
    val chats: ChatsState = ChatsState(),
    val screen: StateBoxState = StateBoxState(),
    val search: SearchState = SearchState()
)
