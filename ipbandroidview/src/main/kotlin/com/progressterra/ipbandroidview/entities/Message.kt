package com.progressterra.ipbandroidview.entities

import androidx.compose.runtime.Immutable

@Immutable
data class Message(
    override val id: String,
    val user: Boolean,
    val content: String,
    val date: String
) : Id