package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.message.IMessengerRepository
import com.progressterra.ipbandroidapi.api.message.models.IncomeDataGetOrCreateDialog
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.messages.MessagesState

interface FetchChatUseCase {

    suspend operator fun invoke(): Result<MessagesState>

    class Base(
        private val repo: IMessengerRepository, private val messageMapper: MessageMapper
    ) : FetchChatUseCase {

        override suspend fun invoke(): Result<MessagesState> = runCatching {
            val chatId = repo.dialogInfo(
                IncomeDataGetOrCreateDialog(
                    listID = listOf(UserData.idUnique, "50881abb-d4cd-4781-8259-b617f3cd23a2")
                )
            ).getOrThrow()?.idUnique!!
            UserData.supportChatId = chatId
            MessagesState(
                items = repo.messagesList(chatId, "0").getOrThrow().orEmpty()
                    .map { messageMapper.map(it, UserData.idUnique) }.reversed()
            )
        }
    }
}