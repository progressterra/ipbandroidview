package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ipbMediaDataCore.IpbMediaDataCore
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi
import com.progressterra.ipbandroidview.ui.media_data.models.toUiModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.emptyFailed
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult
import okhttp3.ResponseBody

class MediaDataRepository : IRepository.MediaData {

    private val mediaDataApi = IpbMediaDataCore.EntityMobile()

    override suspend fun getMediaDataList(idEntity: String): SResult<List<MediaDataUi>> {
        val response = mediaDataApi.getMediaDataListByEntity(idEntity)
        return response.mediaDataList?.toUiModel()?.toSuccessResult()
            ?: response.result?.message.toFailedResult()
    }

    override suspend fun getMediaData(idMediaData: String): SResult<MediaDataUi> {
        val response = mediaDataApi.getMediaDataById(idMediaData)
        return response.mediaData?.toUiModel()?.toSuccessResult()
            ?: response.result?.message.toFailedResult()
    }

    override suspend fun downloadFile(fileUrl: String): SResult<ResponseBody> {
        val response = mediaDataApi.downloadFile(fileUrl)
        return response.body()?.toSuccessResult() ?: emptyFailed()
    }
}