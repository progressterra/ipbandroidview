package com.progressterra.ipbandroidview.processes.docs

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.processes.utils.CreateId
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.widgets.documents.DocumentsState

interface DocumentsUseCase {

    suspend operator fun invoke(): Result<DocumentsState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val repo: DocumentsRepository,
        private val gson: Gson,
        private val createId: CreateId, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources),
        DocumentsUseCase {

        override suspend fun invoke(): Result<DocumentsState> = withToken { token ->
            if (UserData.citizenship.isEmpty()) {
                DocumentsState()
            } else {
                val state =
                    DocumentsState(repo.docsBySpecification(token, UserData.citizenship.id)
                        .getOrThrow()?.listProductCharacteristic?.map { doc ->
                            doc.toDocument(gson, createId)
                        } ?: emptyList())
                state
            }
        }
    }
}
