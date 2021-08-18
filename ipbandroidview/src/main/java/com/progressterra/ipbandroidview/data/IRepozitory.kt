package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.utils.SResult

interface IRepozitory {
    suspend fun getAccessToken(): SResult<String>

    interface Chat {
        suspend fun getAccessToken(): SResult<String>

        suspend fun getMessagesList(dialogId: String, page: String): SResult<List<Message>>

        suspend fun sendMessage(
            message: String,
            token: String,
            IDRGDialog: String
        ): SResult<List<Message>>

        suspend fun getDialogInfo(
            clientId: String,
            partnerId: String,
            descriptionDialog: String = "",
            dialogImage: String = ""
        ): SResult<String>
    }
}