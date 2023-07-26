package com.progressterra.ipbandroidview.features.addresssuggestions

interface UseAddressSuggestions {

    fun handle(event: AddressSuggestionsEvent)

    class Empty : UseAddressSuggestions {

        override fun handle(event: AddressSuggestionsEvent) = Unit
    }
}