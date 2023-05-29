package com.progressterra.ipbandroidview.widgets.peoplenearby

import androidx.compose.runtime.Immutable
import com.progressterra.ipbandroidview.features.avatar.AvatarState
import com.progressterra.ipbandroidview.features.interestsdiff.InterestsDiffState

@Immutable
data class PeopleNearbyState(
    val items: List<Item> = emptyList()
) {

    @Immutable
    data class Item(
        val id: String = "",
        val name: String = "",
        val avatar: AvatarState = AvatarState(),
        val interests: InterestsDiffState = InterestsDiffState(),
        val match: String = "",
        val distance: String = ""
    )
}
