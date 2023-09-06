package com.progressterra.ipbandroidview.pages.goodsdetails

interface ModifyFavoriteUseCase {

    suspend operator fun invoke(id: String, favorite: Boolean): Result<Unit>

    class Base : ModifyFavoriteUseCase {

        override suspend fun invoke(
            id: String, favorite: Boolean
        ): Result<Unit> = Result.success(Unit)
    }
}