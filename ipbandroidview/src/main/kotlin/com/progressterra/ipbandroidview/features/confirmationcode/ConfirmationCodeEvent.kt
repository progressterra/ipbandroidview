package com.progressterra.ipbandroidview.features.confirmationcode

sealed class ConfirmationCodeEvent {

    object Back : ConfirmationCodeEvent()

    class CodeChanged(val code: String) : ConfirmationCodeEvent()
}