package com.progressterra.ipbandroidview.domain.usecase

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.domain.filter.SuggestionFilter
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface SuggestionUseCase {

    suspend fun suggestions(keyword: String): Result<List<Suggestion>>

    class Base(
        private val mapper: SuggestionMapper,
        private val repo: SuggestionRepository,
        private val filter: SuggestionFilter
    ) : SuggestionUseCase {

        override suspend fun suggestions(keyword: String): Result<List<Suggestion>> = runCatching {
            val suggestionsResult = repo.getSuggestionsAddressFromDadata(
                keyword, 3
            ).getOrThrow()
            suggestionsResult.filter { filter.filter(it) }.map { mapper.map(it) }
        }
    }
}