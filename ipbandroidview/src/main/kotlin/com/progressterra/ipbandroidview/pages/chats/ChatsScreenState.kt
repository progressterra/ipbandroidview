package com.progressterra.ipbandroidview.pages.chats

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.DatingChat
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class ChatsScreenState(
    val items: Flow<PagingData<DatingChat>> = emptyFlow()
)