package com.progressterra.ipbandroidview.pages.wantthisrequests

import com.google.gson.Gson
import com.progressterra.ipbandroidapi.api.documents.DocumentsRepository
import com.progressterra.ipbandroidapi.api.documents.models.FieldForFilter
import com.progressterra.ipbandroidapi.api.documents.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.documents.models.SortData
import com.progressterra.ipbandroidapi.api.documents.models.TypeComparison
import com.progressterra.ipbandroidapi.api.documents.models.TypeStatusDoc
import com.progressterra.ipbandroidapi.api.documents.models.TypeVariantSort
import com.progressterra.ipbandroidapi.api.product.ProductRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.entities.toWantThisCardState
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.CreateId

interface WantThisRequestsUseCase {

    suspend operator fun invoke(): Result<List<WantThisCardState>>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val documentsRepository: DocumentsRepository,
        private val productRepository: ProductRepository,
        private val gson: Gson,
        private val createId: CreateId
    ) : WantThisRequestsUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

        override suspend fun invoke(): Result<List<WantThisCardState>> = withToken { token ->
            documentsRepository.docs(
                accessToken = token,
                filter = FilterAndSort(
                    listFields = listOf(
                        FieldForFilter(
                            fieldName = "idrfCharacteristicType",
                            listValue = listOf(IpbAndroidViewSettings.WANT_THIS_DOC_TYPE_ID),
                            comparison = TypeComparison.EQUALS_STRONG
                        )
                    ),
                    sort = SortData(
                        fieldName = "dateUpdated",
                        variantSort = TypeVariantSort.ASC
                    ),
                    searchData = "",
                    skip = 0,
                    take = 100
                )
            ).getOrThrow()?.mapNotNull {
                val doc = it.toDocument(
                    gson = gson,
                    createId = createId
                )
                if (it.statusDoc != TypeStatusDoc.CONFIRMED) {
                    doc.toWantThisCardState()
                } else {
                    productRepository.productByNomenclatureId(token, doc.additionalValue).getOrThrow()?.toGoodsItem()
                        ?.toWantThisCardState()
                }
            } ?: emptyList()
        }
    }
}