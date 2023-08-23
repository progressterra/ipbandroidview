package com.progressterra.ipbandroidview.pages.documentdetails

import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.attachablechat.AttachableChatState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.shared.ui.statebox.StateColumnState

data class DocumentDetailsState(
    val document: Document = Document(),
    val apply: ButtonState = ButtonState(id = "apply"),
    val chat: AttachableChatState = AttachableChatState(isVisible = true),
    val screen: StateColumnState = StateColumnState(),
    val dialogId: String = ""
)