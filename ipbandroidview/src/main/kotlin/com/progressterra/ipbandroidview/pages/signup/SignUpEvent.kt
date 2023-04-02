package com.progressterra.ipbandroidview.pages.signup

sealed class SignUpEvent {

    object OnNext : SignUpEvent()

    object OnSkip : SignUpEvent()
}
