package com.progressterra.ipbandroidview.ui.mainhaccp

import com.progressterra.ipbandroidview.composable.OrganizationsOverviewEvent
import com.progressterra.ipbandroidview.composable.PartnerBlockEvent
import com.progressterra.ipbandroidview.model.Partner

interface MainHaccpInteractor {

    fun onPartnerClick(partner: Partner)

    fun handleEvent(event: OrganizationsOverviewEvent)

    fun handleEvent(event: PartnerBlockEvent)

    fun refresh()
}