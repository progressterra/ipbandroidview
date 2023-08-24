package com.progressterra.ipbandroidview.pages.wantthisdetails

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.features.storecard.StoreCardState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnState

data class WantThisDetailsScreenState(
    val apply: ButtonState = ButtonState(id = "apply"),
    val document: Document = Document(),
    val chat: AttachableChatState = AttachableChatState(),
    val storeCard: StoreCardState = StoreCardState(),
    val screen: StateColumnState = StateColumnState(id = "main")
)