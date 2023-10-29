package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toReceiptItems
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase
import com.progressterra.ipbandroidview.shared.ManageResources

interface FetchReceiptUseCase {

    suspend operator fun invoke(): Result<ReceiptState>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartRepository, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : FetchReceiptUseCase, AbstractTokenUseCase(obtainAccessToken, makeToastUseCase,
        manageResources
    ) {

        override suspend fun invoke(): Result<ReceiptState> = withToken { token ->
            var total = SimplePrice()
            val items = cartRepository.cart(token).getOrThrow()!!.listDRSale?.map {
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

