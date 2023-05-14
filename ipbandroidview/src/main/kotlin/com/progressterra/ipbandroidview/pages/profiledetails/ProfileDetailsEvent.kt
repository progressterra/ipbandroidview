package com.progressterra.ipbandroidview.pages.profiledetails

sealed class ProfileDetailsEvent {

    object Back : ProfileDetailsEvent()

    class OpenPhoto(val photo: String) : ProfileDetailsEvent()
}
