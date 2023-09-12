package com.progressterra.ipbandroidview.processes.user

import android.net.Uri
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.FileExplorer
import com.progressterra.ipbandroidview.shared.throwOnFailure
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface SaveAvatarUseCase {

    suspend operator fun invoke(uri: Uri): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val mediaDataRepository: IPBMediaDataRepository,
        private val fileExplorer: FileExplorer
    ) : SaveAvatarUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(uri: Uri): Result<Unit> = withToken {
            mediaDataRepository.attachToClient(
                accessToken = it,
                typeContent = "image",
                alias = "profilePicture",
                tag = 0,
                file = MultipartBody.Part.createFormData(
                    name = "profilePicture",
                    filename = "profilePicture",
                    body = fileExplorer.fileForUri(uri).asRequestBody("image/*".toMediaType())
                )
            ).throwOnFailure()
        }
    }
}

