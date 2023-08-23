package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.shared.ui.statebox.UseStateBox
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseAttachableChat : UseTextField, UseStateBox {

    fun handle(event: AttachableChatEvent)
}