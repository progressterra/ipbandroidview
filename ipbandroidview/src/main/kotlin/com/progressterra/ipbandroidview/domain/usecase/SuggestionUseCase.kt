package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsRequest
import com.progressterra.ipbandroidview.domain.mapper.AddressesMapper
import com.progressterra.ipbandroidview.model.SuggestionUI

interface SuggestionUseCase {

    suspend fun suggestions(keyword: String): Result<List<SuggestionUI>>

    class Base(
        private val mapper: AddressesMapper,
        private val repo: SuggestionRepository,
    ) : SuggestionUseCase {

        override suspend fun suggestions(
            keyword: String
        ): Result<List<SuggestionUI>> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromDadata(
                DadataSuggestionsRequest(count = 3, query = keyword)
            ).getOrThrow()
            mapper.convertSuggestionsDtoToUIModels(suggestionsResult)
        }
    }
}