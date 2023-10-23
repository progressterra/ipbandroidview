package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.DatingTarget
import com.progressterra.ipbandroidview.entities.DatingUser

sealed class DatingMainScreenEvent {

    data class SelectTarget(val data: DatingTarget) : DatingMainScreenEvent()

    data object OnOwnProfile : DatingMainScreenEvent()

    data class OnProfile(val user: DatingUser): DatingMainScreenEvent()
}
