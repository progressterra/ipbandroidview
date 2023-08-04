package com.progressterra.ipbandroidview.pages.support

import com.progressterra.ipbandroidapi.api.messenger.MessengerRepository
import com.progressterra.ipbandroidapi.api.messenger.models.RGMessagesEntityCreate
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface SendMessageUseCase {

    suspend operator fun invoke(dialogId: String, message: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val messengerRepository: MessengerRepository
    ) : SendMessageUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(
            dialogId: String, message: String
        ): Result<Unit> = withToken { token ->
            messengerRepository.clientAreaMessage(
                accessToken = token,
                body = RGMessagesEntityCreate(
                    idDialog = dialogId,
                    contentText = message,
                    idQuotedMessage = null
                )
            ).throwOnFailure()
        }
    }
}