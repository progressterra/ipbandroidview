package com.progressterra.ipbandroidview.pages.signup

sealed class SignUpEvent {

    data object OnNext : SignUpEvent()

    data object OnSkip : SignUpEvent()

    data object OnBack : SignUpEvent()

    class OpenPhoto(val photo: String) : SignUpEvent()
}
