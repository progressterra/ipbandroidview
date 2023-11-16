package com.progressterra.ipbandroidview.processes.chat

import com.progressterra.ipbandroidapi.api.messenger.MessengerService
import com.progressterra.ipbandroidapi.api.messenger.models.RGMessagesEntityCreate
import com.progressterra.ipbandroidapi.api.messenger.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface SendMessageUseCase {

    suspend operator fun invoke(dialogId: String, message: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val messengerRepository: MessengerService,
        makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : SendMessageUseCase, AbstractTokenUseCase(
        obtainAccessToken,
        makeToastUseCase,
        manageResources
    ) {

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
                throw ToastedException(R.string.failure)
            }
        }
    }
}