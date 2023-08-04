package com.progressterra.ipbandroidview.features.orderchat

import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseOrderChat : UseTextField {

    fun handle(event: OrderChatEvent)
}