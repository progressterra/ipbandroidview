package com.progressterra.ipbandroidview.ui.contacts.models

import java.util.*

data class ContactUi(
    var name: String?,
    var number: String?,
    var isSelected: Boolean = false,
    var id: String = UUID.randomUUID().toString()
)