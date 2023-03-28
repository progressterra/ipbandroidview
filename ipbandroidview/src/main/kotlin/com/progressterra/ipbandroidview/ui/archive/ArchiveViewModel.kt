package com.progressterra.ipbandroidview.ui.archive

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.AuditDocument
import com.progressterra.ipbandroidview.entities.ChecklistStatus
import com.progressterra.ipbandroidview.entities.Document
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ArchiveViewModel : ViewModel(), ContainerHost<ArchiveState, ArchiveEffect>,
    ArchiveInteractor {

    override val container: Container<ArchiveState, ArchiveEffect> = container(ArchiveState())

    fun setDocuments(title: String, docs: List<Document>) =
        intent { reduce { state.copy(title = title, documents = docs) } }

    override fun onBack() = intent { postSideEffect(ArchiveEffect.Back) }

    override fun openDocument(document: Document) = intent {
        postSideEffect(
            ArchiveEffect.OpenChecklist(
                AuditDocument(
                    checklistId = document.checklistId,
                    placeId = document.placeId,
                    documentId = document.documentId,
                    name = document.name
                ), initialStatus = ChecklistStatus.READ_ONLY
            )
        )
    }
}