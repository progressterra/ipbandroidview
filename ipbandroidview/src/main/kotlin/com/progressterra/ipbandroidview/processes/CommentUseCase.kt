package com.progressterra.ipbandroidview.processes

import com.progressterra.ipbandroidapi.api.cart.CartRepository
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataComment
import com.progressterra.ipbandroidview.shared.AbstractTokenUseCase

interface CommentUseCase {

    suspend operator fun invoke(comment: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartRepository
    ) : AbstractTokenUseCase(obtainAccessToken), CommentUseCase {

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