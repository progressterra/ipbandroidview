package com.progressterra.ipbandroidview.processes.docs

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId

interface FetchDocTemplateUseCase {

    suspend operator fun invoke(typeId: String): Result<Document>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val gson: Gson,
        private val createId: CreateId,
        private val documentsRepository: DocumentsRepository
    ) : FetchDocTemplateUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(typeId: String): Result<Document> = withToken { token ->
            documentsRepository.typeById(token, typeId).getOrThrow()?.toDocument(gson, createId)!!
        }
    }
}