package com.progressterra.ipbandroidview.processes.chat

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidview.entities.DatingChat
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface DatingChatsPagingUseCase : PagingUseCase<Nothing, DatingChat> {

    class Base(
        private val messengerService: MessengerService,
        private val obtainAccessToken: ObtainAccessToken
    ) : PagingUseCase.Abstract<Nothing, DatingChat>(), DatingChatsPagingUseCase {

        override fun createSource() = DatingChatsSource(messengerService, obtainAccessToken)
    }
}