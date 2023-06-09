package com.progressterra.ipbandroidview.widgets.documents

import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface DocumentsUseCase {

    suspend operator fun invoke(): Result<DocumentsState>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        val repo: DocumentsRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), DocumentsUseCase {

        override suspend fun invoke(): Result<DocumentsState> = withToken { token ->
            DocumentsState(
                items = repo.docsBySpecification(token, UserData.docSpecId)
                    .getOrThrow()?.listProductCharacteristic?.map {
                        DocumentsState.Item(
                            status = it.characteristicValue?.statusDoc ?: TypeStatusDoc.NOT_FILL,
                            name = it.characteristicType?.name ?: "",
                            id = it.characteristicValue?.idUnique!!
                        )
                    } ?: emptyList()
            )
        }
    }
}