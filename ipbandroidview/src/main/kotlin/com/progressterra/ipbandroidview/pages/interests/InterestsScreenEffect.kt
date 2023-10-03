package com.progressterra.ipbandroidview.pages.interests

sealed class InterestsScreenEffect {

    data object OnNext : InterestsScreenEffect()

    data object OnSkip : InterestsScreenEffect()

    data object OnBack : InterestsScreenEffect()
}
