package com.progressterra.ipbandroidview.processes.docs

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.IncnomeDataCreateCharValue
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.processes.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.ManageResources
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface CreateAndSaveDocUseCase {

    suspend operator fun invoke(data: Document, typeId: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val gson: Gson,
        private val createId: CreateId,
        private val documentsRepository: DocumentsRepository,
        private val saveDocumentsUseCase: SaveDocumentsUseCase, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : CreateAndSaveDocUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(data: Document, typeId: String): Result<Unit> =
            withToken { token ->
                val realDoc = documentsRepository.createDoc(
                    accessToken = token,
                    income = IncnomeDataCreateCharValue(typeId)
                ).getOrThrow()?.toDocument(gson = gson, createId = createId)!!
                saveDocumentsUseCase(data.fromTemplateToReal(realDoc)).throwOnFailure()
            }
    }
}
