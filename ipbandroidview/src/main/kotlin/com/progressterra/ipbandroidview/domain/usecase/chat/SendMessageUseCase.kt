package com.progressterra.ipbandroidview.domain.usecase.chat

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeMessagesTextData
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.domain.exception.ChatIdNotObtainedException
import com.progressterra.ipbandroidview.domain.mapper.MessageMapper
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.model.Message

interface SendMessageUseCase {

    suspend operator fun invoke(dialogId: String, message: String): Result<List<Message>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val fetchUserIdUseCase: FetchUserIdUseCase,
        private val iMessengerRepository: IMessengerRepository,
        private val messageMapper: MessageMapper
    ) : SendMessageUseCase, AbstractUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(
            dialogId: String, message: String
        ): Result<List<Message>> =
            withToken { token ->
                if (UserData.supportChatId.isEmpty())
                    throw ChatIdNotObtainedException()
                val userId = fetchUserIdUseCase().getOrThrow()
                iMessengerRepository.sendMessage(
                    IncomeMessagesTextData(
                        idrgDialog = UserData.supportChatId,
                        accessToken = token,
                        contentText = message
                    )
                ).getOrThrow().orEmpty().map { messageMapper.map(it, userId) }.reversed()
            }
    }
}