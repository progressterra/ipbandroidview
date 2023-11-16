package com.progressterra.ipbandroidview.processes.order

import com.progressterra.ipbandroidapi.api.scrm.ScrmService
import com.progressterra.ipbandroidapi.api.scrm.models.FilterAndSort
import com.progressterra.ipbandroidapi.api.scrm.models.SortData
import com.progressterra.ipbandroidapi.api.scrm.models.TypeVariantSort
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.entities.toAddressUiModel
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.UserData

interface FetchShippingAddressUseCase {

    suspend operator fun invoke(): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val scrmService: ScrmService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchShippingAddressUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

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