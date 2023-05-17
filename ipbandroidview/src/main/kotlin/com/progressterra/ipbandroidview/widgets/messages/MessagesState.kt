package com.progressterra.ipbandroidview.widgets.messages

import androidx.compose.runtime.Immutable

@Immutable
data class MessagesState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val user: Boolean,
        val content: String,
        val date: String
    )
}