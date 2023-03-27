package com.progressterra.ipbandroidview.features.proshkasearch

sealed class ProshkaSearchEvent {

    class OnTextChanged(val text: String) : ProshkaSearchEvent()
}