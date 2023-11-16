package com.progressterra.ipbandroidview.processes.order

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.StatusResult
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toReceiptItems
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.ToastedException
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface FetchReceiptUseCase {

    suspend operator fun invoke(): Result<ReceiptState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchReceiptUseCase, AbstractTokenUseCase(
        obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<ReceiptState> = withToken { token ->
            var total = SimplePrice()
            val items = cartRepository.cart(token).also {
                if (it.result?.status != StatusResult.SUCCESS) throw ToastedException(
                    it.result?.message ?: ""
                )
            }.data?.listDRSale?.map {
                it.toReceiptItems()
            } ?: emptyList()
            items.forEach { total += it.price }
            ReceiptState(
                total = total,
                items = items
            )
        }
    }
}

