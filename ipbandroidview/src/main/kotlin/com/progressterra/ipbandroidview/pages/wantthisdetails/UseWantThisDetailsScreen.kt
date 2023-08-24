package com.progressterra.ipbandroidview.pages.wantthisdetails

import com.progressterra.ipbandroidview.features.attachablechat.UseAttachableChat
import com.progressterra.ipbandroidview.features.storecard.UseStoreCard
import com.progressterra.ipbandroidview.features.topbar.UseTopBar
import com.progressterra.ipbandroidview.shared.ui.textfield.UseTextField

interface UseWantThisDetailsScreen : UseTopBar, UseTextField, UseAttachableChat, UseStoreCard {

    fun handle(event: WantThisDetailsScreenEvent)
}