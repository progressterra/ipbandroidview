package com.progressterra.ipbandroidview.processes.docs

import android.Manifest
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.documentphoto.UseDocumentPhoto
import com.progressterra.ipbandroidview.processes.media.MakePhotoUseCase
import com.progressterra.ipbandroidview.processes.permission.AskPermissionUseCase
import com.progressterra.ipbandroidview.processes.permission.CheckPermissionUseCase
import com.progressterra.ipbandroidview.shared.mvi.Operations
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField
import com.progressterra.ipbandroidview.shared.updateById

class DocsModule(
    private val documentValidationUseCase: DocumentValidationUseCase,
    private val checkPermissionUseCase: CheckPermissionUseCase,
    private val askPermissionUseCase: AskPermissionUseCase,
    private val makePhotoUseCase: MakePhotoUseCase,
    operations: Operations,
    user: DocsModuleUser,
) : DocsModuleUser by user, Operations by operations, UseTextField, UseDocumentPhoto {

    fun setup(newDocument: Document) {
        emitModuleState { newDocument }
        validation()
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> {
                emitModuleState {
                    it.copy(
                        entries = it.entries.updateById(event) { field ->
                            field.copy(text = event.text)
                        })
                }
                validation()
            }

            else -> Unit
        }
    }

    private fun validation() {
        onBackground {
            val valid = documentValidationUseCase(moduleState)
            isValid(valid.isSuccess)
        }
    }

    override fun handle(event: DocumentPhotoEvent) {
        onBackground {
            when (event) {
                is DocumentPhotoEvent.MakePhoto -> checkPermissionUseCase(Manifest.permission.CAMERA).onSuccess {
                    makePhotoUseCase().onSuccess { photo ->
                        emitModuleState {
                            it.copy(
                                photo = it.photo.copy(
                                    items = it.photo.items + photo
                                )
                            )
                        }
                    }
                    validation()
                }.onFailure { askPermissionUseCase(Manifest.permission.CAMERA) }

                is DocumentPhotoEvent.Select -> openPhoto(event.image.url)
            }
        }
    }
}