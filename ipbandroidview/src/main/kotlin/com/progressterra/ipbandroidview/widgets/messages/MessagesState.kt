package com.progressterra.ipbandroidview.widgets.messages

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.Message
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class MessagesState(
    val items: Flow<PagingData<Message>> = emptyFlow()
)