package com.progressterra.ipbandroidview.data

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.imessengercore.IMessengerCore
import com.progressterra.ipbandroidapi.api.imessengercore.models.AdditionalDataJSON
import com.progressterra.ipbandroidapi.api.imessengercore.models.DialogInfoRequest
import com.progressterra.ipbandroidapi.api.imessengercore.models.MessageSendingRequest
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.ui.chat.utils.convertToMessagesList
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.isSuccess
import com.progressterra.ipbandroidview.utils.extensions.safeApiCall
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal class ChatRepository : BaseRepository(), IRepository.Chat {
    private val messengerApi = IMessengerCore.Mobile()

    override suspend fun getMessagesList(dialogId: String, page: String): SResult<List<Message>> =
        safeApiCall {
            val response = messengerApi.getMessagesList(dialogId, page)
            if (response.isSuccess()) {
                response.convertToMessagesList().toSuccessResult()
            } else {
                response.responseToFailedResult()
            }
        }

    override suspend fun sendMessage(
        message: String,
        token: String,
        IDRGDialog: String
    ): SResult<List<Message>> = safeApiCall {
        val request = MessageSendingRequest(IDRGDialog, token, message)
        val response = messengerApi.sendMessage(request)

        if (response.isSuccess()) {
            response.convertToMessagesList().toSuccessResult()
        } else {
            response.responseToFailedResult()
        }
    }

    override suspend fun getDialogInfo(
        clientId: String,
        partnerId: String,
        descriptionDialog: String,
        dialogImage: String
    ): SResult<String> = safeApiCall {
        val request = DialogInfoRequest(
            listId = listOf(clientId, partnerId),
            descriptionDialog = descriptionDialog,
            additionalDataJSON = Gson().toJson(AdditionalDataJSON(partnerId, dialogImage))
        )
        val response = messengerApi.getDialogInfo(request)

        response.dialogInfo?.idUnique?.toSuccessResult()
            .orIfNull { response.responseToFailedResult() }
    }
}