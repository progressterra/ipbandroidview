package com.progressterra.ipbandroidview.domain.fetchexisting

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ext.toYesNo
import com.progressterra.ipbandroidview.ui.checklist.Check

interface FetchExistingAuditUseCase {

    suspend fun fetchExistingAudit(idPlace: String, idChecklist: String): Result<List<Check>>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        manageResources: ManageResources,
        private val repo: ChecklistRepository
    ) : AbstractUseCaseWithToken(scrmRepository, provideLocation), FetchExistingAuditUseCase {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun fetchExistingAudit(
            idPlace: String,
            idChecklist: String
        ): Result<List<Check>> = handle {
            val result = withToken { repo.activeDoc(it, idPlace, idChecklist) }.getOrNull()
                ?: throw NoActiveAuditException()
            val activeChecklist =
                withToken { repo.checklistForDoc(it, result.idUnique!!) }.getOrNull()
            buildList {
                activeChecklist?.map { check ->
                    add(
                        Check(
                            id = check.idUnique!!,
                            category = check.parameter?.internalName ?: noData,
                            name = check.shortDescription ?: noData,
                            yesNo = check.answerCheckList?.yesNo.toYesNo(),
                            comment = check.answerCheckList?.comments ?: "",
                            description = check.description ?: noData
                        )
                    )

                }
            }
        }
    }
}