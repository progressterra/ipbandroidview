package com.progressterra.ipbandroidview.processes.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistService
import com.progressterra.ipbandroidapi.api.checklist.models.DRAnswerChekListItemEntity
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataService
import com.progressterra.ipbandroidapi.api.ipbmediadata.models.StatusResult
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.entities.Check
import com.progressterra.ipbandroidview.entities.CurrentCheckMedia
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.media.FileExplorer
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface UpdateAnswerUseCase {

    suspend operator fun invoke(check: Check, checkDetails: CurrentCheckMedia): Result<Check>

    class Base(
        manageResources: ManageResources,
        obtainAccessToken: ObtainAccessToken,
        makeToastUseCase: MakeToastUseCase,
        private val fileExplorer: FileExplorer,
        private val checklistService: ChecklistService,
        private val mediaDataService: IPBMediaDataService,
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        UpdateAnswerUseCase {

        override suspend fun invoke(
            check: Check,
            checkDetails: CurrentCheckMedia
        ): Result<Check> = withToken { token ->
            checkDetails.voices.forEach { voice ->
                if (voice.local) {
                    if (mediaDataService.attachToEntity(
                            accessToken = token,
                            idEntity = check.id,
                            typeContent = "voiceData",
                            entityTypeName = "DrCheckListItem",
                            alias = "voice ${voice.id}",
                            tag = 0,
                            file = MultipartBody.Part.createFormData(
                                name = "file",
                                filename = fileExplorer.audioFile(voice.id).name,
                                body = fileExplorer.audioFile(voice.id)
                                    .asRequestBody("audio/*".toMediaTypeOrNull())
                            )
                        ).result?.status != StatusResult.SUCCESS
                    ) {
                        throw ToastedException(R.string.failure)
                    }
                } else {
                    if (mediaDataService.deleteMediaData(
                            token,
                            voice.id
                        ).result?.status != StatusResult.SUCCESS
                    ) {
                        throw ToastedException(R.string.failure)
                    }
                }
            }
            checkDetails.pictures.forEach { picture ->
                if (picture.local) {
                    if (mediaDataService.attachToEntity(
                            accessToken = token,
                            idEntity = check.id,
                            typeContent = "image",
                            entityTypeName = "DrCheckListItem",
                            alias = "image ${picture.id}",
                            tag = 0,
                            file = MultipartBody.Part.createFormData(
                                name = "file",
                                filename = fileExplorer.pictureFile(picture.id).name,
                                body = fileExplorer.pictureFile(picture.id)
                                    .asRequestBody("image/*".toMediaTypeOrNull())
                            )
                        ).result?.status != StatusResult.SUCCESS
                    ) {
                        throw ToastedException(R.string.failure)
                    }
                } else {
                    if (mediaDataService.deleteMediaData(
                            token,
                            picture.id
                        ).result?.status != StatusResult.SUCCESS
                    ) {
                        throw ToastedException(R.string.failure)
                    }
                }
            }
            val result = checklistService.createOrUpdateAnswer(
                token, DRAnswerChekListItemEntity(
                    yesNo = check.yesNo,
                    comments = check.comment,
                    rangeValue = 0,
                    specificMeaning = 0.0,
                    specificFreeMeaning = "",
                    iddrCheckListItem = check.id
                )
            ).data!!
            Check(
                id = result.idUnique!!,
                name = result.shortDescription ?: "",
                description = result.description ?: "",
                category = check.category,
                categoryNumber = check.categoryNumber,
                ordinal = check.ordinal,
                yesNo = result.answerCheckList?.yesNo,
                comment = result.answerCheckList?.comments ?: "",
            )
        }
    }
}