package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.models.ambassador_status.AmbassadorStatusResponse
import com.progressterra.ipbandroidview.ui.chat.utils.Message
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ClientInfo
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ImageUpload
import com.progressterra.ipbandroidview.ui.set_personal_info.models.UserBankData
import com.progressterra.ipbandroidview.utils.ISResult
import com.progressterra.ipbandroidview.utils.SResult
import okhttp3.ResponseBody
import java.io.File

internal interface IRepository {
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

    interface AmbassadorInfo {
        suspend fun getClientInfo(accessToken: String): ISResult<ClientInfo>

        suspend fun uploadImage(
            accessToken: String,
            alias: String = "userdata",
            tag: String = "0",
            imageData: File
        ): ISResult<ImageUpload>

        suspend fun updateClientInfo(
            accessToken: String,
            name: String,
            soname: String,
            patronymic: String
        ): ISResult<ClientInfo>

        suspend fun getBankClientInfo(accessToken: String): ISResult<UserBankData>

        suspend fun updateBankClientInfo(
            accessToken: String,
            bankName: String,
            numberAccount: String,
            bik: String,
            correspondentAccount: String,
            tinOfBank: String,
            kppBank: String
        ): ISResult<UserBankData>

        suspend fun uploadSnilsPhotoUrl(
            snilsPhotoUrl: String,
            accessToken: String
        ): ISResult<Any>

        suspend fun uploadPassportPhotoUrl(
            passportPhotoUrl: String,
            accessToken: String
        ): ISResult<Any>

        suspend fun getAmbassadorStatus(accessToken: String): SResult<AmbassadorStatusResponse>

        suspend fun getAccessToken(): SResult<String>

        suspend fun getContractOfAmbassador(accessToken: String): ISResult<ResponseBody>

        suspend fun uploadAmbassadorContractPhotoUrl(
            accessToken: String,
            urlImage: String
        ): ISResult<Any>

        suspend fun becomeSelfEmployed(accessToken: String): ISResult<AmbassadorStatusResponse>
    }
}