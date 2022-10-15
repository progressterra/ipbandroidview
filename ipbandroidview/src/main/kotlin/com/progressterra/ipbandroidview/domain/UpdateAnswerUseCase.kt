package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DRAnswerChekListItemEntity
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ext.toBoolean
import com.progressterra.ipbandroidview.ext.toYesNo
import com.progressterra.ipbandroidview.ui.checklist.Check

interface UpdateAnswerUseCase {

    suspend fun update(check: Check): Result<Check>

    class Base(
        manageResources: ManageResources,
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), UpdateAnswerUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun update(check: Check): Result<Check> = handle {
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