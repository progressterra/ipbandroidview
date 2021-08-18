package com.progressterra.ipbandroidview.ui.set_personal_info

import com.progressterra.core.sresult.ISResult
import com.progressterra.core.sresult.SResult
import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.remoteData.iProBonusApi.IProBonus
import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.IPBAmbassadorAmbassador
import com.progressterra.ipbandroidapi.remoteData.ipbAmbassador.models.ambassador_status.AmbassadorStatusResponse
import com.progressterra.ipbandroidapi.remoteData.ipbMediaDataCore.IpbMediaDataCore
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ClientInfo
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ImageUpload
import com.progressterra.ipbandroidview.ui.set_personal_info.models.UserBankData
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import java.io.File

class RepositoryImpl {

    private val keyPharmApi = IPBAmbassadorAmbassador()
    private val mediaDataApi = IpbMediaDataCore()
    private val ipbApi = IProBonus()
    private val ipbRepository = BonusesApi.getInstance()


    // получение информации о пользователе: имя,емейл,дата рождения и пр
    suspend fun getClientInfo(accessToken: String): ISResult<ClientInfo> {
        val response = ipbApi.getClientInfo(accessToken)
        return if (response.result?.status == 0) {
            SResult.Success(ClientInfo.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    suspend fun uploadImage(
        accessToken: String,
        alias: String = "userdata",
        tag: String = "0",
        imageData: File
    ): ISResult<ImageUpload> {
        val filePart = MultipartBody.Part.createFormData(
            "file",
            imageData.path,
            imageData.asRequestBody("image/*".toMediaTypeOrNull())
        )

        val response = mediaDataApi.uploadImage(accessToken, alias, tag, filePart)
        return if (response.result?.status == 0) {
            SResult.Success(ImageUpload.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    suspend fun updateClientInfo(
        accessToken: String,
        name: String,
        soname: String,
        patronymic: String
    ): ISResult<ClientInfo> {
        val response = ipbApi.updateClientInfo(accessToken, name, soname, patronymic)
        return if (response.result?.status == 0) {
            SResult.Success(ClientInfo.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    suspend fun getBankClientInfo(accessToken: String): ISResult<UserBankData> {
        val response = keyPharmApi.getUserBankData(accessToken)
        return if (response.result?.status == 0) {
            SResult.Success(UserBankData.convertToUiModel(response))
        } else if (response.result?.status == 1) {
            return SResult.Success(UserBankData("", "", "", "", "", ""))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    suspend fun updateBankClientInfo(
        accessToken: String,
        bankName: String,
        numberAccount: String,
        bik: String,
        correspondentAccount: String,
        tinOfBank: String,
        kppBank: String
    ): ISResult<UserBankData> {
        val response = keyPharmApi.updateUserBankData(
            accessToken, bankName = bankName,
            numberAccount = numberAccount,
            bik = bik,
            correspondentAccount = correspondentAccount,
            tinOfBank = tinOfBank,
            kppBank = kppBank
        )
        return if (response.result?.status == 0) {
            SResult.Success(UserBankData.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    suspend fun uploadSnilsPhotoUrl(
        snilsPhotoUrl: String,
        accessToken: String
    ): ISResult<Any> {
        val response =
            keyPharmApi.uploadSnilsPhotoUrl(snilsPhotoUrl, accessToken)

        return if (response.status == 0) {
            SResult.Success(Any())
        } else {
            SResult.Failed(response.message)
        }
    }

    suspend fun uploadPassportPhotoUrl(
        passportPhotoUrl: String,
        accessToken: String
    ): ISResult<Any> {
        val response = keyPharmApi.uploadPassportPhotoUrl(
            passportPhotoUrl,
            accessToken
        )
        return if (response.status == 0) {
            SResult.Success(Any())
        } else {
            SResult.Failed(response.message)
        }
    }

    suspend fun getAmbassadorStatus(accessToken: String): SResult<AmbassadorStatusResponse> {
        val response = keyPharmApi.getAmbassadorStatus(accessToken)
        return if (response.result?.status == 0) {
            SResult.Success(response)
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    suspend fun getAccessToken(): SResult<String> {
        val response = ipbRepository.getAccessToken()
        return if (response.globalResponseStatus == GlobalResponseStatus.SUCCESS && response.responseBody?.accessToken != null) {
            SResult.Success(response.responseBody?.accessToken!!)
        } else {
            SResult.Failed(response.errorString)
        }
    }


    suspend fun getContractOfAmbassador(accessToken: String): ISResult<ResponseBody> {
        val response = keyPharmApi.getContractOfAmbassador(accessToken)
        return if (response.isSuccessful && response.body() != null) {
            SResult.Success(response.body()!!)
        } else {
            SResult.Failed()
        }
    }

    suspend fun uploadAmbassadorContractPhotoUrl(
        accessToken: String,
        urlImage: String
    ): ISResult<Any> {
        val response = keyPharmApi.uploadAmbassadorContractPhotoUrl(
            accessToken, urlImage
        )
        return if (response.status == 0) {
            SResult.Success(Any())
        } else {
            SResult.Failed(response.message)
        }
    }

    suspend fun becomeSelfEmployed(accessToken: String): ISResult<AmbassadorStatusResponse> {
        val response = keyPharmApi.becomeSelfEmployed(accessToken)
        return if (response.result?.status == 0) {
            SResult.Success(response)
        } else {
            SResult.Failed(response.result?.message)
        }
    }

}