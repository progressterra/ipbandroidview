package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.DatingUser

sealed class DatingMainScreenEffect {

    data class OnProfile(val data: DatingUser) : DatingMainScreenEffect()

    data object OnFilter : DatingMainScreenEffect()
}
