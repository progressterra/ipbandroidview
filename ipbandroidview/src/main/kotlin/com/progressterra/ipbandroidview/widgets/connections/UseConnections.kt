package com.progressterra.ipbandroidview.widgets.connections

import com.progressterra.ipbandroidview.features.avatar.AvatarEvent
import com.progressterra.ipbandroidview.features.avatar.UseAvatar

interface UseConnections : UseAvatar {

    class Empty : UseConnections {

        override fun handle(event: AvatarEvent) = Unit
    }
}