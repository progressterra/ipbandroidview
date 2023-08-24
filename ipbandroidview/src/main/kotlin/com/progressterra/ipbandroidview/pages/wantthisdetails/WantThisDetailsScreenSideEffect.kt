package com.progressterra.ipbandroidview.pages.wantthisdetails

sealed class WantThisDetailsScreenSideEffect {

    data object Back : WantThisDetailsScreenSideEffect()

    data class GoodsDetails(val id: String) : WantThisDetailsScreenSideEffect()

    data class OpenPhoto(val image: String) : WantThisDetailsScreenSideEffect()
}
