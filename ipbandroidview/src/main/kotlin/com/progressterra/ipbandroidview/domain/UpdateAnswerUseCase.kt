package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DRAnswerChekListItemEntity
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.Photo
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ext.toBoolean
import com.progressterra.ipbandroidview.ext.toYesNo
import com.progressterra.ipbandroidview.ui.checklist.Check
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface UpdateAnswerUseCase {

    suspend fun update(check: Check, photos: List<Photo>, voiceWasCreated: Boolean): Result<Check>

    class Base(
        manageResources: ManageResources,
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val fileExplorer: FileExplorer,
        private val repo: ChecklistRepository,
        private val mediaDataRepository: IPBMediaDataRepository,
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation),
        UpdateAnswerUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun update(
            check: Check,
            photos: List<Photo>,
            voiceWasCreated: Boolean
        ): Result<Check> = handle {
            if (voiceWasCreated) {
                withToken { token ->
                    mediaDataRepository.attachToEntity(
                        token,
                        check.id,
                        typeContent = 6,
                        "DrCheckListItem",
                        "",
                        0,
                        MultipartBody.Part.createFormData(
                            "file",
                            fileExplorer.obtainOrCreateAudioFile(check.id).name,
                            fileExplorer.obtainOrCreateAudioFile(check.id)
                                .asRequestBody("audio/*".toMediaTypeOrNull())
                        )
                    )
                }
            }
            photos.map { it.id }.forEach { photoId ->
                withToken { token ->
                    mediaDataRepository.attachToEntity(
                        token,
                        check.id,
                        0,
                        "DrCheckListItem",
                        "",
                        0,
                        MultipartBody.Part.createFormData(
                            "file",
                            fileExplorer.obtainPictureFile(photoId).name,
                            fileExplorer.obtainPictureFile(photoId)
                                .asRequestBody("image/*".toMediaTypeOrNull())
                        )
                    )
                }
            }
            val result = withToken {
                repo.createOrUpdateAnswer(
                    it, DRAnswerChekListItemEntity(
                        yesNo = check.yesNo.toBoolean(),
                        comments = check.comment,
                        rangeValue = 0,
                        specificMeaning = 0.0,
                        specificFreeMeaning = "",
                        iddrCheckListItem = check.id
                    )
                )
            }.getOrThrow()
            Check(
                yesNo = result?.answerCheckList?.yesNo.toYesNo(),
                id = result?.idUnique!!,
                category = "${result.parameter?.indexName ?: noData}. ${result.parameter?.internalName ?: noData}",
                name = result.shortDescription ?: noData,
                comment = result.answerCheckList?.comments ?: "",
                description = result.description ?: noData
            )
        }
    }
}