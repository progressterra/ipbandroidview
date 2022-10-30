package com.progressterra.ipbandroidview.ui.confirmationcode

import com.progressterra.ipbandroidview.actions.Back
import com.progressterra.ipbandroidview.actions.Next

interface ConfirmationCodeInteractor : Next, Back {

    fun resend()

    fun editCode(code: String)

    class Empty : ConfirmationCodeInteractor {

        override fun resend() = Unit

        override fun next() = Unit

        override fun editCode(code: String) = Unit

        override fun back() = Unit
    }
}