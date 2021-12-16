package com.progressterra.ipbandroidview.usecases.client

import com.progressterra.ipbandroidapi.localdata.shared_pref.UserData
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidapi.utils.extentions.parseToDate
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.MediaDataRepository
import com.progressterra.ipbandroidview.data.prefs.UserDataLocal
import com.progressterra.ipbandroidview.utils.IUseCase
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult

internal class GetClientAvatarUseCase : IGetClientAvatarUseCase {
    private val imageRepo: IRepository.MediaData = MediaDataRepository()

    override suspend fun invoke(param: String): SResult<String> {
        val result = imageRepo.getMediaDataByEntity(UserData.clientInfo.idUnique)

        val avatarUrl = if (result is SResult.Success) {
            result.data.filter { it.contentType == 0 && it.alias == param }
                .sortedByDescending { it.dateEvent.parseToDate() }
                .firstOrNull()?.urlData.takeIf { !it.isNullOrBlank() }
        } else {
            null
        }

        if (avatarUrl != null && avatarUrl != UserDataLocal.avatarUrl) {
            UserDataLocal.avatarUrl = avatarUrl
        }

        return avatarUrl?.toSuccessResult().orIfNull { emptyFailed() }
    }
}

interface IGetClientAvatarUseCase : IUseCase.InOut<String, SResult<String>> {

    companion object {
        fun IGetClientAvatarUseCase(): IGetClientAvatarUseCase = GetClientAvatarUseCase()
    }
}

