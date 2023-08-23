package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseAttachableChat : UseTextField, UseStateColumn {

    fun handle(event: AttachableChatEvent)
}