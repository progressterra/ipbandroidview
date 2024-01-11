package com.progressterra.ipbandroidview.features.supportchat

import androidx.paging.PagingData
import com.progressterra.ipbandroidview.entities.Id
import com.progressterra.ipbandroidview.entities.IsEmpty
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow


data class SupportChatState(
    override val id: String = "",
    val title: String = "",
    val lastMessage: String = "",
    val lastTime: String = "",
    val iconRes: Int = 0,
    val subCategories: Flow<PagingData<SupportChatState>> = emptyFlow(),
    val finite: Boolean = false
) : Id, IsEmpty {

    override fun isEmpty() = this == SupportChatState()
}