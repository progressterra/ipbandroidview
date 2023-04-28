package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.entities.MultisizedImage

sealed class SignUpEvent {

    object OnNext : SignUpEvent()

    object OnSkip : SignUpEvent()

    object OnBack : SignUpEvent()

    class OpenPhoto(val photo: MultisizedImage) : SignUpEvent()
}
