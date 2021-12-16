package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.interfaces.client.bonuses.BonusesApi
import com.progressterra.ipbandroidapi.remoteData.models.base.BaseResponse
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal open class BaseRepository : IRepository {
    private val bonusesApi = BonusesApi.getInstance()

    override suspend fun getAccessToken(): SResult<String> {
        val response = bonusesApi.getAccessToken()
        val token = response.responseBody?.accessToken
        return token?.toSuccessResult()
            .orIfNull { response.errorString.toFailedResult() }
    }

    fun <T : Any> BaseResponse.responseToFailedResult(): SResult<T> {
        return message.orIfNull { result?.message }.toFailedResult()
    }
}