package com.progressterra.ipbandroidview.features.profilebutton

sealed class ProfileButtonEvent(val id: String) {

    class Click(id: String) : ProfileButtonEvent(id)
}