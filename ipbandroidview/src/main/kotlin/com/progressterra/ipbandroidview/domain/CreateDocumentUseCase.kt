package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.Constants
import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DHCheckPerformedEntityCreate
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.core.AbstractUseCase
import com.progressterra.ipbandroidview.core.ProvideLocation
import java.util.*

interface CreateDocumentUseCase {

    suspend fun createDocument(idChecklist: String, idPlace: String): Result<String>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), CreateDocumentUseCase {

        override suspend fun createDocument(
            idChecklist: String,
            idPlace: String
        ): Result<String> = runCatching {
            val result = withToken {
                repo.createDoc(
                    it, DHCheckPerformedEntityCreate(
                        idChecklist,
                        Constants.EMPTY_ID,
                        idPlace,
                        Date(System.currentTimeMillis()).format(),
                        "",
                        ""
                    )
                )
            }.getOrThrow()
            result?.idUnique!!
        }
    }
}