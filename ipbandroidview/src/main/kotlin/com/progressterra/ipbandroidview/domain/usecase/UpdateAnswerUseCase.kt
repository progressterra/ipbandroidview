package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DRAnswerChekListItemEntity
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.model.Check
import com.progressterra.ipbandroidview.ui.checklist.CurrentCheckMedia
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface UpdateAnswerUseCase {

    suspend operator fun invoke(check: Check, checkDetails: CurrentCheckMedia): Result<Check>

    class Base(
        manageResources: ManageResources,
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val fileExplorer: FileExplorer,
        private val repo: ChecklistRepository,
        private val mediaDataRepository: IPBMediaDataRepository,
    ) : AbstractUseCase(scrmRepository, provideLocation),
        UpdateAnswerUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun invoke(
            check: Check,
            checkDetails: CurrentCheckMedia
        ): Result<Check> = withToken { token ->
            checkDetails.voices.forEach { voice ->
                if (voice.local)
                    mediaDataRepository.attachToEntity(
                        accessToken = token,
                        idEntity = check.id,
                        typeContent = 6,
                        entityTypeName = "DrCheckListItem",
                        alias = "",
                        tag = 0,
                        MultipartBody.Part.createFormData(
                            name = "file",
                            filename = fileExplorer.audioFile(voice.id).name,
                            body = fileExplorer.audioFile(voice.id)
                                .asRequestBody("audio/*".toMediaTypeOrNull())
                        )
                    ).onFailure { throw it }
                if (!voice.local)
                    mediaDataRepository.deleteMediaData(token, voice.id).onFailure { throw it }
            }
            checkDetails.pictures.forEach { picture ->
                if (picture.local)
                    mediaDataRepository.attachToEntity(
                        accessToken = token,
                        idEntity = check.id,
                        typeContent = 0,
                        entityTypeName = "DrCheckListItem",
                        alias = "",
                        tag = 0,
                        MultipartBody.Part.createFormData(
                            name = "file",
                            filename = fileExplorer.pictureFile(picture.id).name,
                            body = fileExplorer.pictureFile(picture.id)
                                .asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    ).onFailure { throw it }
                if (!picture.local)
                    mediaDataRepository.deleteMediaData(token, picture.id).onFailure { throw it }
            }
            val result = repo.createOrUpdateAnswer(
                token, DRAnswerChekListItemEntity(
                    yesNo = check.yesNo,
                    comments = check.comment,
                    rangeValue = 0,
                    specificMeaning = 0.0,
                    specificFreeMeaning = "",
                    iddrCheckListItem = check.id
                )
            ).getOrThrow()
            Check(
                id = result?.idUnique!!,
                name = result.shortDescription ?: noData,
                description = result.description ?: noData,
                category = check.category,
                categoryNumber = check.categoryNumber,
                ordinal = check.ordinal,
                yesNo = result.answerCheckList?.yesNo,
                comment = result.answerCheckList?.comments ?: "",
            )
        }
    }
}