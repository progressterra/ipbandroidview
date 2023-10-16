package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.AnotherUser

sealed class DatingMainScreenEffect {

    data class OnProfile(val data: AnotherUser) : DatingMainScreenEffect()

    data object OnFilter : DatingMainScreenEffect()
}
