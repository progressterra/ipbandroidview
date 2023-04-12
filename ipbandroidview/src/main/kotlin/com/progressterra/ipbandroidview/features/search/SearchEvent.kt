package com.progressterra.ipbandroidview.features.search

sealed class SearchEvent {

    class OnTextChanged(val text: String) : SearchEvent()
}