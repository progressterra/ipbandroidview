package com.progressterra.ipbandroidview.usecases.client


import com.progressterra.ipbandroidapi.api.ipbMediaDataCore.models.UploadImageData
import com.progressterra.ipbandroidview.data.IRepository
import com.progressterra.ipbandroidview.data.MediaDataRepository
import com.progressterra.ipbandroidview.data.prefs.UserDataLocal
import com.progressterra.ipbandroidview.usecases.client.models.UploadClientAvatarParams
import com.progressterra.ipbandroidview.utils.IUseCase
import com.progressterra.ipbandroidview.utils.SResult

internal class UploadClientAvatarUseCase : IUploadClientAvatarUseCase {

    private val imageRepo: IRepository.MediaData = MediaDataRepository()

    override suspend fun invoke(param: UploadClientAvatarParams): SResult<UploadImageData> {
        val result = imageRepo.uploadImage(param.image, param.avatarAlias)

        if (result is SResult.Success) {
            UserDataLocal.avatarUrl = result.data.urlData ?: ""
        }

        return result
    }
}

interface IUploadClientAvatarUseCase :
    IUseCase.InOut<UploadClientAvatarParams, SResult<UploadImageData>> {

    companion object {
        fun IUploadClientAvatarUseCase(): IUploadClientAvatarUseCase = UploadClientAvatarUseCase()
    }
}