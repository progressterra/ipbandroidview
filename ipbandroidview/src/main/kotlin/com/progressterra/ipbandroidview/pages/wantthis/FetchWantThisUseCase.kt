package com.progressterra.ipbandroidview.pages.wantthis

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId

interface FetchWantThisUseCase {

    suspend operator fun invoke(): Result<WantThisScreenState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val documentsRepository: DocumentsRepository,
        private val gson: Gson,
        private val createId: CreateId
    ) : FetchWantThisUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<WantThisScreenState> = withToken { token ->
            documentsRepository.typeById(
                accessToken = token,
                id = IpbAndroidViewSettings.WANT_THIS_DOC_TYPE_ID
            ).getOrThrow()?.toDocument(gson, createId)?.toWantThisScreenState()
                ?: WantThisScreenState()
        }
    }
}
