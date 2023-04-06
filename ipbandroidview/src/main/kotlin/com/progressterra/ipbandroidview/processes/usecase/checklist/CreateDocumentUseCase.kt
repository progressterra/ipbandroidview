package com.progressterra.ipbandroidview.processes.usecase.checklist

import com.progressterra.ipbandroidapi.api.checklist.ChecklistRepository
import com.progressterra.ipbandroidapi.api.checklist.model.DHCheckPerformedEntityCreate
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidapi.ext.format
import com.progressterra.ipbandroidview.shared.AbstractUseCase
import com.progressterra.ipbandroidview.shared.ProvideLocation
import com.progressterra.ipbandroidview.shared.Constants
import java.util.Date

interface CreateDocumentUseCase {

    suspend operator fun invoke(idChecklist: String, idPlace: String): Result<String>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val repo: ChecklistRepository
    ) : AbstractUseCase(scrmRepository, provideLocation), CreateDocumentUseCase {

        override suspend fun invoke(
            idChecklist: String,
            idPlace: String
        ): Result<String> = withToken { token ->
            val result = repo.createDoc(
                token, DHCheckPerformedEntityCreate(
                    idChecklist,
                    Constants.DEFAULT_ID,
                    idPlace,
                    Date(System.currentTimeMillis()).format(),
                    "",
                    ""
                )
            ).getOrThrow()
            result?.idUnique!!
        }
    }
}