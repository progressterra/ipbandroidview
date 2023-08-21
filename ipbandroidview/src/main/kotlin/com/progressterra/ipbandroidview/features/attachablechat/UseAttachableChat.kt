package com.progressterra.ipbandroidview.features.attachablechat

import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseAttachableChat : UseTextField {

    fun handle(event: AttachableChatEvent)
}