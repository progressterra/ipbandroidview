package com.progressterra.ipbandroidview.pages.profiledetails

sealed class ProfileDetailsEvent {

    data object Back : ProfileDetailsEvent()

    class OpenPhoto(val photo: String) : ProfileDetailsEvent()
}
