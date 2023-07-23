package com.progressterra.ipbandroidview.pages.documents

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipEvent
import com.progressterra.ipbandroidview.features.currentcitizenship.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.SaveCitizenshipUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.documents.DocumentsEvent
import com.progressterra.ipbandroidview.widgets.documents.DocumentsUseCase
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class DocumentsViewModel(
    private val documentsUseCase: DocumentsUseCase,
    private val saveCitizenshipUseCase: SaveCitizenshipUseCase,
    private val citizenshipsUseCase: FetchCitizenshipsUseCase
) : ContainerHost<DocumentsScreenState, DocumentsScreenEvent>, ViewModel(), UseDocumentsScreen {

    override val container =
        container<DocumentsScreenState, DocumentsScreenEvent>(DocumentsScreenState())

    fun refresh() {
        intent {
            reduce { state.uScreenState(ScreenState.LOADING) }
            var isSuccess = true
            documentsUseCase().onSuccess {
                reduce { state.uDocuments(it) }
            }.onFailure {
                isSuccess = false
            }
            citizenshipsUseCase().onSuccess {
                reduce { state.uCurrent(it) }
            }.onFailure {
                isSuccess = false
            }
            reduce { state.uScreenState(ScreenState.fromBoolean(isSuccess)) }
        }
    }

    override fun handle(event: CurrentCitizenshipEvent) {
        intent {
            reduce {
                state.uOpen(true)
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(DocumentsScreenEvent.Back)
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: DialogPickerEvent) {
        intent {
            when (event) {
                is DialogPickerEvent.Close -> reduce {
                    state.uOpen(false)
                }

                is DialogPickerEvent.Select -> reduce {
                    state.uSelected(event.item)
                }
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "apply" -> state.citizenship.dialog.selected?.let { newCitizenship ->
                    saveCitizenshipUseCase(newCitizenship).onSuccess {
                        reduce { state.uOpen(false) }
                        refresh()
                    }
                }
            }
        }
    }

    override fun handle(event: DocumentsEvent) {
        intent {
            postSideEffect(DocumentsScreenEvent.OpenDocument(event.item.toDocumentDetailsState()))
        }
    }
}