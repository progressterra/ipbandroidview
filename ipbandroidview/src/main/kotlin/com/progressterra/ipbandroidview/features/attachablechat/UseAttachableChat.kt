package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.UseStateColumn
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseAttachableChat : UseTextField, UseStateColumn {

    fun handle(event: AttachableChatEvent)

    class Empty : UseAttachableChat {

        override fun handle(event: AttachableChatEvent) = Unit

        override fun handle(event: StateColumnEvent) = Unit

        override fun handle(event: TextFieldEvent) = Unit
    }
}