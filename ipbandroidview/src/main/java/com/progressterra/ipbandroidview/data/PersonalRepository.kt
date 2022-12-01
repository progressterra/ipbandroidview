package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ipbAmbassador.models.client_info.ClientInfoResponse
import com.progressterra.ipbandroidapi.api.scrmApiQwerty.SCRMApiQwerty
import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.remoteData.models.base.GlobalResponseStatus
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.ui.personal_edit.models.ClientInfoUI
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*

internal class PersonalRepository(
    private val cityApi: SCRMApiQwerty.ClientCity,
    private val clientApi: SCRMApiQwerty.ClientsV3,
    private val loginApi: LoginApi
) : BaseRepository(), IRepository.Personal {

    override suspend fun updatePersonalInfoLocal(): SResult<ClientInfoUI> = safeApiCall {
        val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }

        val response = clientApi.getClientInfo(token)
        if (response.isSuccess()) {
            val client = response.client?.toPrefModel
            val additional = response.clientAdditionalInfo?.toPrefModel
            client?.let {
                UserData.clientInfo = it
            }
            additional?.let {
                UserData.clientAdditionalInfo = it
            }
            ClientInfoUI(
                UserData.fullName,
                UserData.email,
                UserData.clientInfo.name,
                UserData.clientInfo.soname
            ).toSuccessResult()
        } else {
            response.responseToFailedResult()
        }
    }

    override suspend fun updatePersonalInfo(
        name: String,
        soname: String
    ): SResult<ClientInfoResponse> =
        safeApiCall {
            val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }
            val response = clientApi.updateClientInfo(token, name, soname, "", "", 0)

            if (response.isSuccess()) {
                response.toSuccessResult()
            } else {
                response.responseToFailedResult()
            }
        }

    override suspend fun updateEmail(email: String): SResult<*> = safeApiCall {
        val response = loginApi.addEmail(email)
        return@safeApiCall if (response.globalResponseStatus == GlobalResponseStatus.SUCCESS)
            completedResult()
        else
            response.errorString.toFailedResult()
    }

    override suspend fun confirmEmail(email: String): SResult<*> = safeApiCall {
        val response = loginApi.confirmEmail(email)
        return@safeApiCall if (response.globalResponseStatus == GlobalResponseStatus.SUCCESS)
            completedResult()
        else
            response.errorString.toFailedResult()
    }

    override suspend fun getClientCity(): SResult<String> = safeApiCall {
        val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }

        val response = cityApi.getClientCity(token)

        response.city?.cityName?.toSuccessResult()
            .orIfNull { response.responseToFailedResult() }
    }
}