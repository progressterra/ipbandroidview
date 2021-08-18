package com.progressterra.ipbandroidview.ui.set_personal_info.models

import com.progressterra.ipbandroidapi.remoteData.ipbMediaDataCore.models.UploadImageResponse

class ImageUpload(val uploadedImageUrl: String) {
    companion object {
        fun convertToUiModel(response: UploadImageResponse): ImageUpload {
            return ImageUpload(response.uploadImageData?.urlData ?: "")
        }
    }
}