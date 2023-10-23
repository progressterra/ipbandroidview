package com.progressterra.ipbandroidview.pages.datingprofile

sealed class DatingProfileScreenEvent {

    data object Edit : DatingProfileScreenEvent()

    data object OnSettings : DatingProfileScreenEvent()

    data object OnBack : DatingProfileScreenEvent()
}