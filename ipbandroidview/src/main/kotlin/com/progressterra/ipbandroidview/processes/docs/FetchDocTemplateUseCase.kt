package com.progressterra.ipbandroidview.processes.docs

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchDocTemplateUseCase {

    suspend operator fun invoke(typeId: String): Result<Document>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val gson: Gson,
        private val createId: CreateId,
        private val documentsRepository: DocumentsRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchDocTemplateUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(typeId: String): Result<Document> = withToken { token ->
            documentsRepository.typeById(token, typeId).getOrThrow()?.toDocument(gson, createId)!!
        }
    }
}