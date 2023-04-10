package com.progressterra.ipbandroidview.features.addresssuggestions

import com.progressterra.ipbandroidapi.api.suggestion.SuggestionRepository
import com.progressterra.ipbandroidapi.api.suggestion.model.DadataSuggestionsRequest
import com.progressterra.ipbandroidview.processes.mapper.AddressesMapper
import com.progressterra.ipbandroidview.entities.SuggestionUI

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