package com.progressterra.ipbandroidview.processes.chat

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidview.entities.Message
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface FetchMessagesUseCase : PagingUseCase<String, Message> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val messengerRepository: MessengerService
    ) : PagingUseCase.Abstract<String, Message>(), FetchMessagesUseCase {

        override fun createSource() = MessageSource(messengerRepository, obtainAccessToken)
    }
}