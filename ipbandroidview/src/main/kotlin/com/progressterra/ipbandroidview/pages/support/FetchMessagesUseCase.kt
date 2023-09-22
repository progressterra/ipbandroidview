package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidview.entities.Message
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.PagingUseCase

interface FetchMessagesUseCase : PagingUseCase<String, Message> {

    class Base(
        private val obtainAccessToken: ObtainAccessToken,
        private val messengerRepository: MessengerService
    ) : PagingUseCase.Abstract<String, Message>(), FetchMessagesUseCase {

        override fun createSource() = MessageSource(messengerRepository, obtainAccessToken)
    }
}