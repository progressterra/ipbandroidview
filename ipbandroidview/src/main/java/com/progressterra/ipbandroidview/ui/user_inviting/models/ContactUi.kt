package com.progressterra.ipbandroidview.ui.user_inviting.models

import java.util.*

data class ContactUi(
    var name: String?,
    var number: String?,
    var isSelected: Boolean = false,
    var id: String = UUID.randomUUID().toString()
)