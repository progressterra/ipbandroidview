package com.progressterra.ipbandroidview.processes

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.shared.CreateId
import com.progressterra.ipbandroidview.shared.PagingUseCase

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