package com.progressterra.ipbandroidview.pages.wantthis

import com.progressterra.ipbandroidview.shared.mvi.OnBack
import com.progressterra.ipbandroidview.shared.mvi.OpenPhoto

interface WantThisScreenNavigation : OnBack, OpenPhoto {

    fun onWantThisRequests()
}