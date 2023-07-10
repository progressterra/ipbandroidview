package com.progressterra.ipbandroidview.pages.wantthis

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.IncnomeDataCreateCharValue
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.Document
import com.progressterra.ipbandroidview.entities.fromTemplateToReal
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.pages.documentdetails.SaveDocumentsUseCase
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.throwOnFailure

interface CreateWantThisRequestUseCase {

    suspend operator fun invoke(data: Document): Result<Unit>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val gson: Gson,
        private val createId: CreateId,
        private val documentsRepository: DocumentsRepository,
        private val saveDocumentsUseCase: SaveDocumentsUseCase
    ) : CreateWantThisRequestUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(data: Document): Result<Unit> = withToken { token ->
            val realDoc = documentsRepository.createDoc(
                accessToken = token,
                income = IncnomeDataCreateCharValue(IpbAndroidViewSettings.WANT_THIS_DOC_TYPE_ID)
            ).getOrThrow()?.toDocument(gson = gson, createId = createId)!!
            saveDocumentsUseCase(data.fromTemplateToReal(realDoc)).throwOnFailure()
        }
    }
}
