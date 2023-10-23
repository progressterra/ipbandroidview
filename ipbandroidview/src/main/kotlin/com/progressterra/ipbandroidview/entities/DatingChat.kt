package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable

@Immutable
data class DatingChat(
    val id: String = "",
    val user: DatingUser = DatingUser(),
    val previewMessage: String = "",
    val lastTime: String = ""
)
