package com.progressterra.ipbandroidview.pages.signup

import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OpenPhoto

interface SignUpScreenNavigation : OnBack, OpenPhoto {

    fun onNext()

    fun onSkip()
}