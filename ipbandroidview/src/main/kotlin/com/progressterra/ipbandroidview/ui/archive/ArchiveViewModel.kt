package com.progressterra.ipbandroidview.ui.archive

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.model.AuditDocument
import com.progressterra.ipbandroidview.model.ChecklistStatus
import com.progressterra.ipbandroidview.model.Document
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ArchiveViewModel : ViewModel(), ContainerHost<ArchiveState, ArchiveEffect> {

    override val container: Container<ArchiveState, ArchiveEffect> = container(ArchiveState())

    fun setDocuments(docs: List<Document>) = intent { reduce { state.copy(documents = docs) } }

    fun back() = intent { postSideEffect(ArchiveEffect.Back) }

    fun openDocument(document: Document) = intent {
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