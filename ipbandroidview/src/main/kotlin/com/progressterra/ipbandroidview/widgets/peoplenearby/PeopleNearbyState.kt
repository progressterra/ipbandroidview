package com.progressterra.ipbandroidview.widgets.peoplenearby

import com.progressterra.ipbandroidview.features.avatar.AvatarState
import com.progressterra.ipbandroidview.features.interestsdiff.InterestsDiffState


data class PeopleNearbyState(
    val items: List<Item> = emptyList()
) {


    data class Item(
        val id: String = "",
        val name: String = "",
        val avatar: AvatarState = AvatarState(),
        val interests: InterestsDiffState = InterestsDiffState(),
        val match: String = "",
        val distance: String = ""
    )
}
