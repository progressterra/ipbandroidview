package com.progressterra.ipbandroidview.domain.usecase

import android.util.Log
import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DRAnswerChekListItemEntity
import com.progressterra.ipbandroidapi.api.ipbmediadata.IPBMediaDataRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.FileExplorer
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.core.ProvideLocation
import com.progressterra.ipbandroidview.ext.toBoolean
import com.progressterra.ipbandroidview.ext.toYesNo
import com.progressterra.ipbandroidview.ui.checklist.Check
import com.progressterra.ipbandroidview.ui.checklist.CurrentCheckMedia
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody

interface UpdateAnswerUseCase {

    suspend fun update(check: Check, checkDetails: CurrentCheckMedia): Result<Check>

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

        override suspend fun update(
            check: Check,
            checkDetails: CurrentCheckMedia
        ): Result<Check> = runCatching {
            Log.d("CHECK", "income $check")
            checkDetails.voices.forEach { voice ->
                if (voice.local)
                    withToken { token ->
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
                        )
                    }
                if (!voice.local)
                    withToken { token ->
                        mediaDataRepository.deleteMediaData(token, voice.id)
                    }
            }
            checkDetails.pictures.forEach { picture ->
                if (picture.local)
                    withToken { token ->
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
                        )
                    }
                if (!picture.local)
                    withToken { token ->
                        mediaDataRepository.deleteMediaData(token, picture.id)
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
            val output = Check(
                id = result?.idUnique!!,
                name = result.shortDescription ?: noData,
                description = result.description ?: noData,
                category = check.category,
                categoryNumber = check.categoryNumber,
                ordinal = check.ordinal,
                result.answerCheckList?.yesNo.toYesNo(),
                result.answerCheckList?.comments ?: "",
            )
            Log.d("CHECK", "output $output")
            output
        }
    }
}