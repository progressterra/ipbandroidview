package com.progressterra.ipbandroidview.pages.documents

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipEvent
import com.progressterra.ipbandroidview.features.currentcitizenship.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.features.currentcitizenship.dialog
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerEvent
import com.progressterra.ipbandroidview.features.dialogpicker.open
import com.progressterra.ipbandroidview.features.dialogpicker.selected
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
            reduce { DocumentsScreenState.screen.set(state, ScreenState.LOADING) }
            var isSuccess = true
            documentsUseCase().onSuccess {
                reduce { DocumentsScreenState.documents.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            citizenshipsUseCase().onSuccess {
                reduce { DocumentsScreenState.citizenship.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            reduce { DocumentsScreenState.screen.set(state, isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: CurrentCitizenshipEvent) {
        intent {
            reduce {
                DocumentsScreenState.citizenship.dialog.open.set(
                    state,
                    true
                )
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
                    DocumentsScreenState.citizenship.dialog.open.set(
                        state,
                        false
                    )
                }

                is DialogPickerEvent.Select -> reduce {
                    DocumentsScreenState.citizenship.dialog.selected.set(
                        state,
                        event.item
                    )
                }
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "apply" -> state.citizenship.dialog.selected?.let { newCitizenship ->
                    saveCitizenshipUseCase(newCitizenship).onSuccess {
                        DocumentsScreenState.citizenship.dialog.open.set(
                            state,
                            false
                        )
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