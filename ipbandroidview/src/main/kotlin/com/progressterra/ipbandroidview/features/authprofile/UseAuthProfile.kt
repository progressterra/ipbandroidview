package com.progressterra.ipbandroidview.features.authprofile

interface UseAuthProfile {

    fun handle(event: AuthProfileEvent)

    class Empty : UseAuthProfile {

        override fun handle(event: AuthProfileEvent) = Unit
    }
}