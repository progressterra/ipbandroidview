package com.progressterra.ipbandroidview.pages.datingprofile

sealed class DatingProfileScreenEvent {

    data object OnBack : DatingProfileScreenEvent()

    data object SendRequest : DatingProfileScreenEvent()

    data object AcceptRequest : DatingProfileScreenEvent()

    data object OnToChat : DatingProfileScreenEvent()
}