package com.progressterra.ipbandroidview.domain

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidview.base.AbstractUseCase
import com.progressterra.ipbandroidview.base.UseCaseException
import com.progressterra.ipbandroidview.domain.mapper.SuggestionMapper
import com.progressterra.ipbandroidview.ui.city.Suggestion

interface SuggestionUseCase {

    suspend fun suggestions(keyword: String): Result<List<Suggestion>>

    class Base(
        private val mapper: SuggestionMapper, private val repo: SuggestionRepository
    ) : SuggestionUseCase, AbstractUseCase() {

        override suspend fun suggestions(keyword: String): Result<List<Suggestion>> = handle {
            val suggestionsResult = repo.getSuggestionsAddressFromDadata(
                keyword
            )
            if (suggestionsResult.isFailure) throw UseCaseException()
            suggestionsResult.getOrNull()!!.map { mapper.map(it) }
        }
    }
}