package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.currentcitizenship.CurrentCitizenshipEvent
import com.progressterra.ipbandroidview.features.currentcitizenship.FetchCitizenshipsUseCase
import com.progressterra.ipbandroidview.features.dialogpicker.DialogPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.SaveCitizenshipUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.documents.DocumentsEvent
import com.progressterra.ipbandroidview.widgets.documents.DocumentsUseCase

class DocumentsViewModel(
    private val documentsUseCase: DocumentsUseCase,
    private val saveCitizenshipUseCase: SaveCitizenshipUseCase,
    private val citizenshipsUseCase: FetchCitizenshipsUseCase
) : BaseViewModel<DocumentsScreenState, DocumentsScreenEvent>(), UseDocumentsScreen {

    override fun createInitialState() = DocumentsScreenState()

    fun refresh() {
        onBackground {
            emitState {
                it.copy(screen = ScreenState.LOADING)
            }
            var isSuccess = true
            documentsUseCase().onSuccess { docs ->
                emitState {
                    it.copy(documents = docs)
                }
            }.onFailure {
                isSuccess = false
            }
            citizenshipsUseCase().onSuccess { citizenship ->
                emitState {
                    it.copy(citizenship = citizenship)
                }
            }.onFailure {
                isSuccess = false
            }
            emitState {
                it.copy(screen = isSuccess.toScreenState())
            }
        }
    }

    override fun handle(event: CurrentCitizenshipEvent) {
        emitState {
            it.copy(citizenship = it.citizenship.copy(dialog = it.citizenship.dialog.copy(open = true)))
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(DocumentsScreenEvent.Back)
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: DialogPickerEvent) {
        when (event) {
            is DialogPickerEvent.Close -> emitState {
                it.copy(citizenship = it.citizenship.copy(dialog = it.citizenship.dialog.copy(open = false)))
            }

            is DialogPickerEvent.Select -> emitState {
                it.copy(
                    citizenship = it.citizenship.copy(
                        dialog = it.citizenship.dialog.copy(
                            selected = event.item
                        )
                    )
                )
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "apply" -> currentState.citizenship.dialog.selected?.let { newCitizenship ->
                    saveCitizenshipUseCase(newCitizenship).onSuccess {
                        emitState {
                            it.copy(
                                citizenship = it.citizenship.copy(
                                    dialog = it.citizenship.dialog.copy(
                                        open = false
                                    )
                                )
                            )
                        }
                        refresh()
                    }
                }
            }
        }
    }

    override fun handle(event: DocumentsEvent) {
        postEffect(DocumentsScreenEvent.OpenDocument(event.item.toDocumentDetailsState()))
    }
}