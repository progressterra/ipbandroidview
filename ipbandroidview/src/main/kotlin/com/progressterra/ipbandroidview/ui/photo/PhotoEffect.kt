package com.progressterra.ipbandroidview.ui.photo

sealed class PhotoEffect {

    object Back : PhotoEffect()

    object Remove : PhotoEffect()
}
