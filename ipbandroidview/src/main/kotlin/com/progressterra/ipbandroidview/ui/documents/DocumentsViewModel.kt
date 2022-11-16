package com.progressterra.ipbandroidview.ui.documents

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.checklist.AllDocumentsUseCase
import com.progressterra.ipbandroidview.domain.usecase.checklist.DocumentChecklistUseCase
import com.progressterra.ipbandroidview.model.AuditDocument
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class DocumentsViewModel(
    private val allDocumentsUseCase: AllDocumentsUseCase,
    private val documentChecklistUseCase: DocumentChecklistUseCase
) : ViewModel(), ContainerHost<DocumentsState, DocumentsEffect> {

    override val container: Container<DocumentsState, DocumentsEffect> = container(DocumentsState())

    init {
        refresh()
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        allDocumentsUseCase.allDocuments().onSuccess {
            reduce {
                state.copy(documents = it, screenState = ScreenState.SUCCESS)
            }
            postSideEffect(DocumentsEffect.UpdateCounter(it.filter { doc -> doc.finishDate == null }.size))
        }.onFailure { reduce { state.copy(screenState = ScreenState.ERROR) } }
    }


    fun openDocument(document: Document) = intent {
        postSideEffect(
            DocumentsEffect.OpenChecklist(
                AuditDocument(
                    checklistId = document.checklistId,
                    placeId = document.placeId,
                    documentId = document.documentId,
                    name = document.name,
                    readOrCompleteOnly = true,
                    ongoing = document.finishDate == null
                )
            )
        )
    }

    fun openOrganizations() = intent {
        postSideEffect(DocumentsEffect.OpenOrganizations)
    }
}