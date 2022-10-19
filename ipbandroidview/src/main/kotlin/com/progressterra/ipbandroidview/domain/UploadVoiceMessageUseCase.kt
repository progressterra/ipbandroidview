package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.data.ProvideLocation
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

interface UploadVoiceMessageUseCase {

    suspend fun uploadVoiceMessage(
        file: File,
        entityId: String,
        entityTypeName: String
    ): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        val repo: IPBMediaDataRepository
    ) : UploadVoiceMessageUseCase, AbstractUseCaseWithToken(scrmRepository, provideLocation) {

        override suspend fun uploadVoiceMessage(
            file: File,
            entityId: String,
            entityTypeName: String
        ): Result<Unit> = handle {
            repo.attachToEntity(
                entityId, typeContent = 6, entityTypeName, "", 0, MultipartBody.Part.createFormData(
                    "file",
                    file.path,
                    file.asRequestBody("audio/*".toMediaTypeOrNull())
                )
            )
        }
    }

}