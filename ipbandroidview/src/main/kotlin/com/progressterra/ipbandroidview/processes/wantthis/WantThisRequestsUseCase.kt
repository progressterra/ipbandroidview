package com.progressterra.ipbandroidview.processes.wantthis

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.CreateId
import com.progressterra.ipbandroidview.shared.mvi.PagingUseCase

interface WantThisRequestsUseCase : PagingUseCase<Nothing, WantThisCardState> {

    class Base(
        private val documentsRepository: DocumentsRepository,
        private val productRepository: ProductRepository,
        private val gson: Gson,
        private val createId: CreateId,
        private val obtainAccessToken: ObtainAccessToken
    ) : WantThisRequestsUseCase,
        PagingUseCase.Abstract<Nothing, WantThisCardState>() {

        override fun createSource() = WantThisRequestsSource(
            documentsRepository = documentsRepository,
            productRepository = productRepository,
            gson = gson,
            createId = createId,
            obtainAccessToken = obtainAccessToken
        )
    }
}