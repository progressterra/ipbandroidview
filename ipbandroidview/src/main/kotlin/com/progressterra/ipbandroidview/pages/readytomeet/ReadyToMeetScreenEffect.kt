package com.progressterra.ipbandroidview.pages.readytomeet

sealed class ReadyToMeetScreenEffect {

    data object OnBack : ReadyToMeetScreenEffect()

    data object OnNext : ReadyToMeetScreenEffect()
}