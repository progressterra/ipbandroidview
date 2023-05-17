package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeMessagesTextData
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

interface SendMessageUseCase {

    suspend operator fun invoke(dialogId: String, message: String): Result<MessagesState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val iMessengerRepository: IMessengerRepository,
        private val messageMapper: MessageMapper
    ) : SendMessageUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            dialogId: String, message: String
        ): Result<MessagesState> = withToken { token ->
            if (UserData.supportChatId.isEmpty()) throw ChatIdNotObtainedException()
            MessagesState(
                items = iMessengerRepository.sendMessage(
                    IncomeMessagesTextData(
                        idrgDialog = UserData.supportChatId,
                        accessToken = token,
                        contentText = message
                    )
                ).getOrThrow().orEmpty().map { messageMapper.map(it, UserData.idUnique) }.reversed()
            )
        }
    }
}