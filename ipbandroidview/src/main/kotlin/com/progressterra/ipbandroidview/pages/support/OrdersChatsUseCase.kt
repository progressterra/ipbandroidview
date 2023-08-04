package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.messenger.MessengerRepository
import com.progressterra.ipbandroidview.features.supportchat.SupportChatState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface OrdersChatsUseCase : PagingUseCase<Nothing, SupportChatState> {

    class Base(
        private val messengerRepository: MessengerRepository,
        private val obtainAccessToken: ObtainAccessToken
    ) : PagingUseCase.Abstract<Nothing, SupportChatState>(), OrdersChatsUseCase {

        override fun createSource() = OrdersChatsSource(messengerRepository, obtainAccessToken)
    }
}