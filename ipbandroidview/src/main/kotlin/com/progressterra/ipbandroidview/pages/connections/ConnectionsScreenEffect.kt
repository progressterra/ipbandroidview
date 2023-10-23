package com.progressterra.ipbandroidview.pages.connections

import com.progressterra.ipbandroidview.entities.DatingUser

sealed class ConnectionsScreenEffect {

    data class OnProfile(val user: DatingUser) : ConnectionsScreenEffect()
}