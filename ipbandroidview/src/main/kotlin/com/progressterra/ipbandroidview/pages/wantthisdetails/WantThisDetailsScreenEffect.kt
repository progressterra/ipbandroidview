package com.progressterra.ipbandroidview.pages.wantthisdetails

sealed class WantThisDetailsScreenEffect {

    data object Back : WantThisDetailsScreenEffect()

    data class GoodsDetails(val id: String) : WantThisDetailsScreenEffect()

    data class OpenPhoto(val image: String) : WantThisDetailsScreenEffect()
}
