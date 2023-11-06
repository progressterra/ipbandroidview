package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.entities.SignInData

sealed class SignInScreenEffect {

    class Next(val data: SignInData) : SignInScreenEffect()

    data object Skip : SignInScreenEffect()

}
