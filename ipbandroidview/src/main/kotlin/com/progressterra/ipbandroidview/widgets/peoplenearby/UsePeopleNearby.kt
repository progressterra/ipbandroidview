package com.progressterra.ipbandroidview.widgets.peoplenearby

import com.progressterra.ipbandroidview.features.avatar.AvatarEvent
import com.progressterra.ipbandroidview.features.avatar.UseAvatar

interface UsePeopleNearby : UseAvatar {

    class Empty : UsePeopleNearby {

        override fun handle(event: AvatarEvent) = Unit
    }
}