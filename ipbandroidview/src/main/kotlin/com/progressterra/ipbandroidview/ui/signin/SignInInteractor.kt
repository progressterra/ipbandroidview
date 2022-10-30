package com.progressterra.ipbandroidview.ui.signin

import com.progressterra.ipbandroidview.actions.Next
import com.progressterra.ipbandroidview.actions.Skip

interface SignInInteractor : Skip, Next {

    fun editPhoneNumber(phoneNumber: String)

    class Empty : SignInInteractor {

        override fun next() = Unit

        override fun skip() = Unit

        override fun editPhoneNumber(phoneNumber: String) = Unit
    }
}