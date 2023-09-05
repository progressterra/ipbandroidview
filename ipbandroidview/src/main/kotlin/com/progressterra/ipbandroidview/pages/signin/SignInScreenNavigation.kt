package com.progressterra.ipbandroidview.pages.signin

import com.progressterra.ipbandroidview.entities.SignInData
import com.progressterra.ipbandroidview.shared.mvi.OnMain

interface SignInScreenNavigation : OnMain {

    fun onCodeConfirmation(data: SignInData)
}