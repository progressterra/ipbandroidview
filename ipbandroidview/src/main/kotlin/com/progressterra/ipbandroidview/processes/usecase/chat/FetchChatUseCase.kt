package com.progressterra.ipbandroidview.processes.usecase.chat

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeDataGetOrCreateDialog
import com.progressterra.ipbandroidview.data.UserData
import com.progressterra.ipbandroidview.processes.AppSettings.ID_ENTERPRISE
import com.progressterra.ipbandroidview.processes.mapper.MessageMapper
import com.progressterra.ipbandroidview.processes.usecase.user.FetchUserIdUseCase
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
                    listID = listOf(userId, ID_ENTERPRISE)
                )
            ).getOrThrow()?.idUnique!!
            UserData.supportChatId = chatId
            repo.messagesList(chatId, "0").getOrThrow().orEmpty()
                .map { messageMapper.map(it, userId) }.reversed()
        }
    }
}