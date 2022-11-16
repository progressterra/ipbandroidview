package com.progressterra.ipbandroidview.domain.usecase.store

import com.progressterra.core.domain.IUseCase
import com.progressterra.data.contracts.IRepository
import com.progressterra.ipbandroidview.utils.SResult
import com.progressterra.ipbandroidview.utils.extensions.toFailedResult
import com.progressterra.ipbandroidview.utils.extensions.toSuccessResult
import javax.inject.Inject

internal class GetSizeTableForItemUseCase @Inject constructor(
    private val repo: IRepository.SizeTable
) : IGetSizeTableForItemUseCase {
    override suspend fun invoke(param: String): SResult<String> {
        val result = repo.getSizeTableForItem(param)
        return result.data?.toSuccessResult() ?: result.toFailedResult()
    }
}

interface IGetSizeTableForItemUseCase : IUseCase.InOut<String, SResult<String>>