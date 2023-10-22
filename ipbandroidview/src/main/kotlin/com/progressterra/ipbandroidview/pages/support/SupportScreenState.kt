package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState


data class SupportScreenState(
    val screen: StateColumnState = StateColumnState(),
    val current: SupportChatState = SupportChatState(),
    val trace: List<SupportChatState> = emptyList()
)