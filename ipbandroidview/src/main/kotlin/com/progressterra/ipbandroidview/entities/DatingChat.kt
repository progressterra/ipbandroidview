package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable

@Immutable
data class DatingChat(
    val id: String = "",
    val avatar: String = "",
    val name: String = "",
    val sex: Sex = Sex.MALE,
    val previewMessage: String = "",
    val lastTime: String = ""
)
