package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataComment
import com.progressterra.ipbandroidapi.api.scrm.SCRMRepository
import com.progressterra.ipbandroidview.processes.location.ProvideLocation
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface CommentUseCase {

    suspend operator fun invoke(comment: String): Result<Unit>

    class Base(
        provideLocation: ProvideLocation,
        scrmRepository: SCRMRepository,
        private val cartRepository: CartRepository
    ) : AbstractTokenUseCase(scrmRepository, provideLocation), CommentUseCase {

        override suspend fun invoke(comment: String): Result<Unit> = withToken {
            cartRepository.addComment(
                accessToken = it,
                income = IncomeDataComment(
                    dataComment = comment
                )
            )
        }
    }
}