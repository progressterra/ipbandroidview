package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.base.BaseResponse
import com.progressterra.ipbandroidapi.utils.orIfNull
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal open class BaseRepository(
    private val sCRMRepository: SCRMRepository
) : IRepository {

    override suspend fun getAccessToken(): SResult<String> {
        val response = sCRMRepository.getAccessToken().getOrNull() ?: ""
        return response.toSuccessResult()
    }

    fun <T : Any> BaseResponse.responseToFailedResult(): SResult<T> {
        return message.orIfNull { result?.message }.toFailedResult()
    }
}