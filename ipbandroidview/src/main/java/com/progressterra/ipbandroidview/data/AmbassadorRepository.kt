package com.progressterra.ipbandroidview.data


import com.progressterra.ipbandroidapi.api.ipbAmbassador.IPBAmbassador
import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.ambassador_status.AmbassadorStatusResponse
import com.progressterra.ipbandroidapi.api.ipbMediaDataCore.IpbMediaDataCore
import com.progressterra.ipbandroidapi.api.scrmApiQwerty.SCRMApiQwerty
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ClientInfo
import com.progressterra.ipbandroidview.ui.set_personal_info.models.ImageUpload
import com.progressterra.ipbandroidview.ui.set_personal_info.models.UserBankData
import com.progressterra.ipbandroidview.utils.ISResult
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.safeApiCall
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.ResponseBody
import java.io.File

internal class AmbassadorRepository : BaseRepository(), IRepository.AmbassadorInfo {

    private val keyPharmApi = IPBAmbassador.Ambassador()
    private val mediaDataApi = IpbMediaDataCore.EntityMobile()
    private val ipbApi = SCRMApiQwerty.ClientsV3()

    // получение информации о пользователе: имя,емейл,дата рождения и пр
    override suspend fun getClientInfo(accessToken: String): ISResult<ClientInfo> = safeApiCall {
        val response = ipbApi.getClientInfo(accessToken)
        if (response.result?.status == 0) {
            SResult.Success(ClientInfo.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    override suspend fun uploadImage(
        accessToken: String,
        alias: String,
        tag: String,
        imageData: File
    ): ISResult<ImageUpload> = safeApiCall {
        val filePart = MultipartBody.Part.createFormData(
            "file",
            imageData.path,
            imageData.asRequestBody("image/*".toMediaTypeOrNull())
        )

        val response = mediaDataApi.uploadImage(accessToken, alias, tag, filePart)
        if (response.result?.status == 0) {
            SResult.Success(ImageUpload.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    override suspend fun updateClientInfo(
        accessToken: String,
        name: String,
        soname: String,
        patronymic: String
    ): ISResult<ClientInfo> = safeApiCall {
        val response = ipbApi.updateClientInfo(accessToken, name, soname, patronymic, "", 0)
        if (response.result?.status == 0) {
            SResult.Success(ClientInfo.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    override suspend fun getBankClientInfo(accessToken: String): ISResult<UserBankData> =
        safeApiCall {
            val response = keyPharmApi.getUserBankData(accessToken)
            if (response.result?.status == 0) {
                SResult.Success(UserBankData.convertToUiModel(response))
            } else if (response.result?.status == 1) {
                SResult.Success(UserBankData("", "", "", "", "", "", ""))
            } else {
                SResult.Failed(response.result?.message)
            }
        }

    override suspend fun updateBankClientInfo(
        accessToken: String,
        bankName: String,
        numberAccount: String,
        bik: String,
        correspondentAccount: String,
        tinOfBank: String,
        kppBank: String,
        clientInn: String
    ): ISResult<UserBankData> = safeApiCall {
        val response = keyPharmApi.updateUserBankData(
            accessToken, bankName = bankName,
            numberAccount = numberAccount,
            bik = bik,
            correspondentAccount = correspondentAccount,
            tinOfBank = tinOfBank,
            kppBank = kppBank,
            tinOfClient = clientInn
        )

        if (response.result?.status == 0) {
            SResult.Success(UserBankData.convertToUiModel(response))
        } else {
            SResult.Failed(response.result?.message)
        }
    }

    override suspend fun uploadSnilsPhotoUrl(
        snilsPhotoUrl: String,
        accessToken: String
    ): ISResult<Any> = safeApiCall {
        val response =
            keyPharmApi.uploadSnilsPhotoUrl(snilsPhotoUrl, accessToken)

        if (response.status == 0) {
            SResult.Completed
        } else {
            SResult.Failed(response.message)
        }
    }

    override suspend fun uploadPassportPhotoUrl(
        passportPhotoUrl: String,
        accessToken: String
    ): ISResult<Any> = safeApiCall {
        val response = keyPharmApi.uploadPassportPhotoUrl(
            passportPhotoUrl,
            accessToken
        )

        if (response.status == 0) {
            SResult.Completed
        } else {
            SResult.Failed(response.message)
        }
    }

    override suspend fun getAmbassadorStatus(accessToken: String): SResult<AmbassadorStatusResponse> =
        safeApiCall {
            val response = keyPharmApi.getAmbassadorStatus(accessToken)

            if (response.result?.status == 0) {
                SResult.Success(response)
            } else {
                SResult.Failed(response.result?.message)
            }
        }

    override suspend fun getContractOfAmbassador(accessToken: String): ISResult<ResponseBody> =
        safeApiCall {
            val response = keyPharmApi.getContractOfAmbassador(accessToken)
            if (response.isSuccessful && response.body() != null) {
                SResult.Success(response.body()!!)
            } else {
                SResult.Failed()
            }
        }

    override suspend fun uploadAmbassadorContractPhotoUrl(
        accessToken: String,
        urlImage: String
    ): ISResult<Any> = safeApiCall {
        val response = keyPharmApi.uploadAmbassadorContractPhotoUrl(
            accessToken, urlImage
        )

        if (response.status == 0) {
            SResult.Completed
        } else {
            SResult.Failed(response.message)
        }
    }

    override suspend fun becomeSelfEmployed(accessToken: String): ISResult<AmbassadorStatusResponse> =
        safeApiCall {
            val response = keyPharmApi.becomeSelfEmployed(accessToken)

            if (response.result?.status == 0) {
                SResult.Success(response)
            } else {
                SResult.Failed(response.result?.message)
            }
        }
}