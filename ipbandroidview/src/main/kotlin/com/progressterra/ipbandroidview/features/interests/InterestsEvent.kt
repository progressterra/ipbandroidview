package com.progressterra.ipbandroidview.features.interests

sealed class InterestsEvent(val id: String) {

    class Select(id: String) : InterestsEvent(id)
}