package com.progressterra.ipbandroidview.pages.datingprofile

sealed class DatingProfileScreenEffect {

    data object OnBack : DatingProfileScreenEffect()

    data object OnToChat : DatingProfileScreenEffect()
}