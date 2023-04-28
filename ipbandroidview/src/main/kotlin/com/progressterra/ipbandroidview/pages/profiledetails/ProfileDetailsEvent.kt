package com.progressterra.ipbandroidview.pages.profiledetails

import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class ProfileDetailsEvent {

    object Back : ProfileDetailsEvent()

    class OpenPhoto(val photo: MultisizedImage) : ProfileDetailsEvent()
}
