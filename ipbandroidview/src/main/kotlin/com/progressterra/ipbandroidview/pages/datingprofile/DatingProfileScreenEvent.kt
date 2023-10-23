package com.progressterra.ipbandroidview.pages.datingprofile

import com.progressterra.ipbandroidview.entities.Interest

sealed class DatingProfileScreenEvent {

    data class PickInterest(val interest: Interest) : DatingProfileScreenEvent()

    data object Edit : DatingProfileScreenEvent()

    data object OnSettings : DatingProfileScreenEvent()

    data object OnBack : DatingProfileScreenEvent()
}