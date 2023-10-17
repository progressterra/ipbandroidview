package com.progressterra.ipbandroidview.pages.occupacion

sealed class OccupationScreenEffect {

    data object OnNext : OccupationScreenEffect()

    data object OnSkip : OccupationScreenEffect()

    data object OnBack : OccupationScreenEffect()
}
