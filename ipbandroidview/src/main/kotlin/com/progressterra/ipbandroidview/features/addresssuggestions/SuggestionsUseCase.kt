package com.progressterra.ipbandroidview.features.addresssuggestions

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsRequest
import com.progressterra.ipbandroidview.entities.convertSuggestionsDtoToUIModels

interface SuggestionsUseCase {

    suspend operator fun invoke(keyword: String): Result<List<SuggestionUI>>

    class Base(
        private val repo: SuggestionRepository,
    ) : SuggestionsUseCase {

        override suspend fun invoke(
            keyword: String
        ): Result<List<SuggestionUI>> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromDadata(
                DadataSuggestionsRequest(count = 3, query = keyword)
            ).getOrThrow()
            suggestionsResult?.map { it.convertSuggestionsDtoToUIModels() } ?: emptyList()
        }
    }
}