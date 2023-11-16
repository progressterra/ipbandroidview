package com.progressterra.ipbandroidview.processes.order

import com.progressterra.ipbandroidapi.api.cart.CartService
import com.progressterra.ipbandroidapi.api.cart.models.IncomeDataComment
import com.progressterra.ipbandroidview.processes.utils.ManageResources
import com.progressterra.ipbandroidview.processes.utils.ObtainAccessToken
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractTokenUseCase

interface CommentUseCase {

    suspend operator fun invoke(comment: String): Result<Unit>

    class Base(
        obtainAccessToken: ObtainAccessToken,
        private val cartRepository: CartService, makeToastUseCase: MakeToastUseCase,
        manageResources: ManageResources
    ) : AbstractTokenUseCase(obtainAccessToken, makeToastUseCase, manageResources), CommentUseCase {

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