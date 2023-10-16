package com.progressterra.ipbandroidview.pages.datingmain

sealed class DatingMainScreenEffect {

    data class OnProfile(val id: String) : DatingMainScreenEffect()

    data object OnFilter : DatingMainScreenEffect()
}
