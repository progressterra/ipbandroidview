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
import com.progressterra.ipbandroidview.IpbAndroidViewSettings
import com.progressterra.ipbandroidview.entities.toDocument
import com.progressterra.ipbandroidview.entities.toGoodsItem
import com.progressterra.ipbandroidview.features.wantthiscard.WantThisCardState
import com.progressterra.ipbandroidview.processes.ObtainAccessToken
import com.progressterra.ipbandroidview.shared.AbstractSource
import com.progressterra.ipbandroidview.shared.CreateId

class WantThisRequestsSource(
    private val documentsRepository: DocumentsRepository,
    private val productRepository: ProductRepository,
    private val gson: Gson,
    private val createId: CreateId,
    private val obtainAccessToken: ObtainAccessToken
) : AbstractSource<Nothing, WantThisCardState>() {

    override val pageSize = 10

    override suspend fun loadPage(skip: Int, take: Int): Result<List<WantThisCardState>> =
        runCatching {
            val token = obtainAccessToken().getOrThrow()
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
                        variantSort = TypeVariantSort.DESC
                    ),
                    searchData = "",
                    skip = skip,
                    take = take
                )
            ).getOrThrow()?.mapNotNull {
                val doc = it.toDocument(
                    gson = gson,
                    createId = createId
                )
                if (it.statusDoc != TypeStatusDoc.CONFIRMED || doc.additionalValue.isBlank()) {
                    doc.toWantThisCardState()
                } else {
                    productRepository.productByNomenclatureId(token, doc.additionalValue)
                        .getOrThrow()?.toGoodsItem()
                        ?.toWantThisCardState()
                }
            } ?: emptyList()
        }
}