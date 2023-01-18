package com.progressterra.ipbandroidview.domain.usecase.suggestion

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsRequest
import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.model.address.SuggestionUI

interface SuggestionUseCase {

    suspend operator fun invoke(keyword: String): Result<List<SuggestionUI>>

    class Base(
        private val mapper: AddressesMapper,
        private val repo: SuggestionRepository,
    ) : SuggestionUseCase {

        override suspend fun invoke(
            keyword: String
        ): Result<List<SuggestionUI>> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromDadata(
                DadataSuggestionsRequest(count = 3, query = keyword)
            ).getOrThrow()
            mapper.convertSuggestionsDtoToUIModels(suggestionsResult)
        }
    }
}