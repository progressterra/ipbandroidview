package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.entities.DatingTarget

sealed class DatingMainScreenEvent {

    data class SelectTarget(val data: DatingTarget) : DatingMainScreenEvent()

    data class PreChooseTier(val tier: Int): DatingMainScreenEvent()
}
