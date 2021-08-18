package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.models.ambassador_status.AmbassadorStatusResponse
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ClientInfo
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ImageUpload
import com.progressterra.ipbandroidview.ui.set_personal_info.models.UserBankData
import com.progressterra.ipbandroidview.utils.ISResult
import com.progressterra.ipbandroidview.utils.SResult
import okhttp3.ResponseBody
import java.io.File

internal interface IRepository {
    // получение информации о пользователе: имя,емейл,дата рождения и пр
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