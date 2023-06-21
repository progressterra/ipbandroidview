package com.progressterra.ipbandroidview.widgets.documents

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.UserData

interface DocumentsUseCase {

    suspend operator fun invoke(): Result<DocumentsState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val repo: DocumentsRepository,
        private val gson: Gson,
        private val createId: CreateId
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), DocumentsUseCase {

        override suspend fun invoke(): Result<DocumentsState> = withToken { token ->
            if (UserData.citizenship.isEmpty()) {
                DocumentsState()
            } else {
                DocumentsState(items = repo.docsBySpecification(token, UserData.citizenship.id)
                    .getOrThrow()?.listProductCharacteristic?.map { doc ->
                        doc.toDocument(gson, createId)
                    } ?: emptyList())
            }
        }
    }
}
