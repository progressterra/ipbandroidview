package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.entities.SimplePrice
import com.progressterra.ipbandroidview.entities.toReceiptItems
import com.progressterra.ipbandroidview.features.receipt.ReceiptState
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface FetchReceiptUseCase {

    suspend operator fun invoke(): Result<ReceiptState>

    class Base(
        scrmRepository: SCRMRepository,
        provideLocation: ProvideLocation,
        private val cartRepository: CartRepository
    ) : FetchReceiptUseCase, AbstractTokenUseCase(scrmRepository, provideLocation) {

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

