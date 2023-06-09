package com.progressterra.ipbandroidview.pages.documentdetails

import android.Manifest
import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.uText
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class DocumentDetailsViewModel(
    private val saveDocumentsUseCase: SaveDocumentsUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase
) : ContainerHost<DocumentDetailsState, DocumentDetailsEvent>,
    ViewModel(), UseDocumentDetails {

    override val container =
        container<DocumentDetailsState, DocumentDetailsEvent>(DocumentDetailsState())

    fun refresh(state: DocumentDetailsState) {
        intent { reduce { state } }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(DocumentDetailsEvent.Back)
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event) {
                is ButtonEvent.Click -> when (event.id) {
                    "apply" -> saveDocumentsUseCase(state).onSuccess {
                        postSideEffect(DocumentDetailsEvent.Back)
                    }
                }
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
                is TextFieldEvent.TextChanged -> {
                    reduce {
                        state.updateById(event) {
                            it.uText(event.text)
                        }
                    }
                }
            }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        intent {
            when (event) {
                is DocumentPhotoEvent.MakePhoto -> checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                    makePhotoUseCase().onSuccess { reduce { state.addPhoto(it) } }
                }.onFailure { askPermissionUseCase(Manifest.permission.CAMERA) }

                is DocumentPhotoEvent.Select -> postSideEffect(DocumentDetailsEvent.OpenPhoto(event.image.url))
            }
        }

    }
}