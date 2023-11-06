package com.progressterra.ipbandroidview.pages.peoplenearby

import com.progressterra.ipbandroidview.entities.DatingUser

sealed class PeopleNearbyScreenEffect {

    data class OnProfile(val user: DatingUser) : PeopleNearbyScreenEffect()
}