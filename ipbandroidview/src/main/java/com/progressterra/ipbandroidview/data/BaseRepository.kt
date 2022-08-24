package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.interfaces.client.login.LoginApi
import com.progressterra.ipbandroidapi.remotedata.models.base.BaseResponse
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal open class BaseRepository : IRepository {
private val loginApi = LoginApi.newInstance()

    override suspend fun getAccessToken(): SResult<String> {
        val response = loginApi.accessToken()
        return response.toSuccessResult()
    }

    fun <T : Any> BaseResponse.responseToFailedResult(): SResult<T> {
        return message.orIfNull { result?.message }.toFailedResult()
    }
}