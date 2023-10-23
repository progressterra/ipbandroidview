package com.progressterra.ipbandroidview.pages.datingprofile

sealed class DatingProfileScreenEffect {

    data object OnBack : DatingProfileScreenEffect()

    data class OnChat(val id: String) : DatingProfileScreenEffect()

    data object OnSettings : DatingProfileScreenEffect()
}