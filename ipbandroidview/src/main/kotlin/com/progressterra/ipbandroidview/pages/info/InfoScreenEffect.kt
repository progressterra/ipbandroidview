package com.progressterra.ipbandroidview.pages.info

sealed class InfoScreenEffect {

    data object OnSkip : InfoScreenEffect()

    data object OnBack : InfoScreenEffect()

    data object OnNext : InfoScreenEffect()
}