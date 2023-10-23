package com.progressterra.ipbandroidview.widgets.peoplenearby

import com.progressterra.ipbandroidview.entities.DatingUser


data class PeopleNearbyScreenState(
    val items: List<DatingUser> = emptyList(),
    val currentUser: DatingUser = DatingUser()
)