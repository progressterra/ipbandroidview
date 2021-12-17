package com.progressterra.ipbandroidview.usecases.client.models

import okhttp3.MultipartBody

data class UploadClientAvatarParams(
    val image: MultipartBody.Part,
    val avatarAlias: String
)