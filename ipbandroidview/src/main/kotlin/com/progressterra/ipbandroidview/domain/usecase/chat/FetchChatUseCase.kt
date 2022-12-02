package com.progressterra.ipbandroidview.domain.usecase.chat

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeDataGetOrCreateDialog
import com.progressterra.ipbandroidview.domain.AppSettings
import com.progressterra.ipbandroidview.domain.mapper.MessageMapper
import com.progressterra.ipbandroidview.domain.usecase.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.model.Message

interface FetchChatUseCase {

    suspend operator fun invoke(): Result<List<Message>>

    class Base(
        private val fetchUserIdUseCase: FetchUserIdUseCase,
        private val repo: IMessengerRepository,
        private val messageMapper: MessageMapper
    ) : FetchChatUseCase {

        override suspend fun invoke(): Result<List<Message>> = runCatching {
            val userId = fetchUserIdUseCase().getOrThrow()
            val chatId = repo.dialogInfo(
                IncomeDataGetOrCreateDialog(
                    listID = listOf(userId, AppSettings.idEnterprise)
                )
            ).getOrThrow()?.idUnique!!
            repo.messagesList(chatId, "0").getOrThrow().orEmpty()
                .map { messageMapper.map(it, userId) }
        }
    }
}