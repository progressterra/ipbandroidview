package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.entities.SignInData

interface SignInScreenNavigation {

    fun onNext(data: SignInData)

    fun onSkip()
}