package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.RGMessagesEntityCreate
import com.progressterra.ipbandroidapi.api.messenger.models.StatusResult
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface SendMessageUseCase {

    suspend operator fun invoke(dialogId: String, message: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val messengerRepository: MessengerService
    ) : SendMessageUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            dialogId: String, message: String
        ): Result<Unit> = withToken { token ->
            if (messengerRepository.clientAreaMessage(
                    accessToken = token,
                    body = RGMessagesEntityCreate(
                        idDialog = dialogId,
                        contentText = message,
                        idQuotedMessage = null
                    )
                ).result?.status != StatusResult.SUCCESS
            ) {
                throw Exception("Message was not sent")
            }
        }
    }
}