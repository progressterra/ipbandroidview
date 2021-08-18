package com.progressterra.ipbandroidview.data

import android.util.Log
import com.google.gson.Gson
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.IMessengerCore
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.models.AdditionalDataJSON
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.models.DialogInfoRequest
import com.progressterra.ipbandroidapi.remoteData.iMessengerCore.models.MessageSendingRequest
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.ui.chat.utils.convertToMessagesList
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.isSuccess
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

class ChatRepository : BaseRepository(), IRepozitory.Chat {
    private val messengerApi = IMessengerCore()

    override suspend fun getMessagesList(dialogId: String, page: String): SResult<List<Message>> {
        val response = messengerApi.getMessagesList(dialogId, page)
        return if (response.isSuccess()) {
            response.convertToMessagesList().toSuccessResult()
        } else {
            response.message.toFailedResult()
        }
    }

    override suspend fun sendMessage(
        message: String,
        token: String,
        IDRGDialog: String
    ): SResult<List<Message>> {
        val request = MessageSendingRequest(IDRGDialog, token, message)
        val response = messengerApi.sendMessage(request)
        return if (response.isSuccess()) {
            response.convertToMessagesList().toSuccessResult()
        } else {
            response.message.toFailedResult()
        }
    }

    override suspend fun getDialogInfo(
        clientId: String,
        partnerId: String,
        descriptionDialog: String,
        dialogImage: String
    ): SResult<String> {
        val request = DialogInfoRequest(
            listId = listOf(clientId, partnerId),
            descriptionDialog = descriptionDialog,
            additionalDataJSON = Gson().toJson(AdditionalDataJSON(partnerId, dialogImage))
        )
        val response = messengerApi.getDialogInfo(request)
        return response.dialogInfo?.idUnique?.toSuccessResult()
            .orIfNull { response.message.toFailedResult() }
    }
}