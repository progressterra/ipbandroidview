package com.progressterra.ipbandroidview.ui.support

import com.progressterra.ipbandroidview.model.Message

data class SupportState(
    val message: String = "",
    val messages: List<Message> = emptyList()
)
