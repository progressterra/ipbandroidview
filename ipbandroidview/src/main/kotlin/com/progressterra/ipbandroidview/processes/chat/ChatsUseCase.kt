package com.progressterra.ipbandroidview.processes.chat

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface ChatsUseCase : PagingUseCase<String, SupportChatState> {

    class Base(
        private val messengerRepository: MessengerService,
        private val obtainAccessToken: ObtainAccessToken
    ) : PagingUseCase.Abstract<String, SupportChatState>(), ChatsUseCase {

        override fun createSource() = ChatsSource(messengerRepository, obtainAccessToken)
    }
}