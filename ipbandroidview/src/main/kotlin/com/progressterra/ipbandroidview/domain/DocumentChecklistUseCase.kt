package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.core.AbstractUseCaseWithToken
import com.progressterra.ipbandroidview.core.ManageResources
import com.progressterra.ipbandroidview.data.ProvideLocation
import com.progressterra.ipbandroidview.ui.auditchecks.AuditState
import com.progressterra.ipbandroidview.ui.auditchecks.Check
import com.progressterra.ipbandroidview.ui.auditchecks.CheckState

interface DocumentChecklistUseCase {

    suspend fun documentChecklist(id: String, auditState: AuditState): Result<List<Check>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        manageResources: ManageResources,
        private val repo: ChecklistRepository
    ) : DocumentChecklistUseCase, AbstractUseCaseWithToken(scrmRepository, provideLocation) {

        private val noData = manageResources.string(R.string.no_data)

        override suspend fun documentChecklist(
            id: String, auditState: AuditState
        ): Result<List<Check>> {
            val responseChecklist = withToken { repo.checklistForDoc(it, id) }
            if (responseChecklist.isFailure) return Result.failure(responseChecklist.exceptionOrNull()!!)
            return Result.success(
                buildList {
                    responseChecklist.getOrNull()!!.map { check ->
                        check.idUnique?.let { id ->
                            add(
                                when (check.answerCheckList?.yesNo) {
                                    true -> Check(
                                        id = id,
                                        category = check.parameter?.internalName ?: noData,
                                        name = check.shortDescription ?: noData,
                                        state = CheckState.SUCCESSFUL,
                                        comment = check.answerCheckList?.comments ?: ""
                                    )
                                    false -> Check(
                                        id = id,
                                        category = check.parameter?.internalName ?: noData,
                                        name = check.shortDescription ?: noData,
                                        state = CheckState.FAILED,
                                        comment = check.answerCheckList?.comments ?: ""
                                    )
                                    null -> Check(
                                        id = id,
                                        category = check.parameter?.internalName ?: noData,
                                        name = check.shortDescription ?: noData,
                                        state = if (auditState == AuditState.NONE) CheckState.NONE else CheckState.ONGOING,
                                        comment = check.answerCheckList?.comments ?: ""
                                    )
                                }
                            )
                        }
                    }
                }
            )
        }
    }
}
