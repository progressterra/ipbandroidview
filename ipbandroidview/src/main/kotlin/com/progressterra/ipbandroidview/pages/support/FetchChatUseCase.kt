package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeDataGetOrCreateDialog
import com.progressterra.ipbandroidview.features.chatmessage.Message
import com.progressterra.ipbandroidview.processes.user.FetchUserIdUseCase
import com.progressterra.ipbandroidview.shared.UserData

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
                    listID = listOf(userId, "50881abb-d4cd-4781-8259-b617f3cd23a2")
                )
            ).getOrThrow()?.idUnique!!
            UserData.supportChatId = chatId
            repo.messagesList(chatId, "0").getOrThrow().orEmpty()
                .map { messageMapper.map(it, userId) }.reversed()
        }
    }
}