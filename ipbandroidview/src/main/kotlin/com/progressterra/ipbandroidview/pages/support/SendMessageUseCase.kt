package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeMessagesTextData
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

interface SendMessageUseCase {

    suspend operator fun invoke(dialogId: String, message: String): Result<MessagesState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val iMessengerRepository: IMessengerRepository,
        private val messageMapper: MessageMapper
    ) : SendMessageUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            dialogId: String, message: String
        ): Result<MessagesState> = withToken { token ->
            if (UserData.supportChatId.isEmpty()) throw Exception("Set correct chat id!")
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