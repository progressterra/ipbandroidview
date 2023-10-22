package com.progressterra.ipbandroidview.pages.support

sealed class SupportScreenEffect {

    data class OnNext(val id: String) : SupportScreenEffect()

    data object OnBack : SupportScreenEffect()
}