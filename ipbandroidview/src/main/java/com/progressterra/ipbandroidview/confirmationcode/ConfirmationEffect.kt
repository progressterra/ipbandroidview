package com.progressterra.ipbandroidview.confirmationcode

sealed class ConfirmationEffect {

    object Back : ConfirmationEffect()

    object Next : ConfirmationEffect()
}
