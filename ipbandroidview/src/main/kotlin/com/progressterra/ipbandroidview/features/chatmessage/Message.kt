package com.progressterra.ipbandroidview.features.chatmessage

import androidx.compose.runtime.Immutable

@Immutable
data class Message(
    val user: Boolean,
    val content: String,
    val date: String
)