package com.progressterra.ipbandroidview.data

import com.progressterra.ipbandroidapi.api.ipbmediadatacore.IpbMediaDataCore
import com.progressterra.ipbandroidapi.api.ipbmediadatacore.models.MediaData
import com.progressterra.ipbandroidapi.api.ipbmediadatacore.models.UploadImageData
import com.progressterra.ipbandroidapi.utils.extentions.orIfNull
import com.progressterra.ipbandroidview.ui.media_data.models.MediaDataUi
import com.progressterra.ipbandroidview.ui.media_data.models.toUiModel
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody

internal class MediaDataRepository : BaseRepository(), IRepository.MediaData {

    private val mediaDataApi = IpbMediaDataCore.EntityMobile()

    override suspend fun getMediaDataList(idEntity: String): SResult<List<MediaDataUi>> =
        safeApiCall {
            val response = mediaDataApi.getMediaDataListByEntity(idEntity)

            response.mediaDataList?.toUiModel()?.toSuccessResult()
                ?: response.responseToFailedResult()
        }

    override suspend fun getMediaData(idMediaData: String): SResult<MediaDataUi> = safeApiCall {
        val response = mediaDataApi.getMediaDataById(idMediaData)

        response.mediaData?.toUiModel()?.toSuccessResult()
            ?: response.responseToFailedResult()
    }

    override suspend fun downloadFile(fileUrl: String): SResult<ResponseBody> = safeApiCall {
        val response = mediaDataApi.downloadFile(fileUrl)

        response.body()?.toSuccessResult() ?: emptyFailed()
    }

    override suspend fun uploadImage(
        image: MultipartBody.Part,
        alias: String?
    ): SResult<UploadImageData> =
        safeApiCall {
            val token = getAccessToken().dataOrFailed { return@safeApiCall it.toFailedResult() }

            val response = mediaDataApi.uploadImage(
                token, alias ?: "0", "0", image
            )

            response.uploadImageData?.toSuccessResult()
                .orIfNull { response.responseToFailedResult() }
        }

    override suspend fun getMediaDataByEntity(idEntity: String): SResult<List<MediaData>> =
        safeApiCall {
            val response = mediaDataApi.getMediaDataListByEntity(idEntity)

            response.mediaDataList?.toSuccessResult().orIfNull { response.responseToFailedResult() }
        }
}