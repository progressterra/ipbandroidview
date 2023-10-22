package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidapi.api.scrm.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.scrm.models.SortData
import com.progressterra.ipbandroidapi.api.scrm.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.toAddressUiModel
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface FetchShippingAddressUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val scrmService: ScrmService
    ) : FetchShippingAddressUseCase, AbstractTokenUseCase(obtainAccessToken) {

        override suspend fun invoke(): Result<Unit> = withToken { token ->
            val address =
                scrmService.addressList(
                    token = token, body = FilterAndSort(
                        sort = SortData(
                            fieldName = "dateAdded",
                            variantSort = TypeVariantSort.DESC
                        ),
                        listFields = emptyList(),
                        skip = 0,
                        take = 1
                    )
                ).dataList?.firstOrNull()?.toAddressUiModel() ?: AddressUI()
            UserData.shippingAddress = address
        }
    }
}