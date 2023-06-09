package com.progressterra.ipbandroidview.pages.documents

import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidview.pages.documentdetails.DocumentDetailsState
import com.progressterra.ipbandroidview.shared.AbstractLoggingUseCase
import com.progressterra.ipbandroidview.shared.ui.button.ButtonState
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

interface OpenDocumentUseCase {

    suspend operator fun invoke(data: DocumentsState.Item): Result<DocumentDetailsState>

    class Base : OpenDocumentUseCase, AbstractLoggingUseCase() {

        override suspend fun invoke(data: DocumentsState.Item): Result<DocumentDetailsState> =
            handle {
                DocumentDetailsState(
                    id = data.id,
                    docName = data.name,
                    entries = data.entries,
                    photo = data.photo,
                    apply = ButtonState(
                        id = "apply",
                        enabled = when (data.status) {
                            TypeStatusDoc.NOT_FILL -> true
                            TypeStatusDoc.WAIT_IMAGE -> true
                            TypeStatusDoc.WAIT_REVIEW -> false
                            TypeStatusDoc.REJECTED -> true
                            TypeStatusDoc.CONFIRMED -> false
                        }
                    )
                )
            }
    }
}