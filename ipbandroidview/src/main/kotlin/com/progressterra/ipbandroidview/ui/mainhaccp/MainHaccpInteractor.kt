package com.progressterra.ipbandroidview.ui.mainhaccp

import com.progressterra.ipbandroidview.composable.OrganizationsOverviewEvent
import com.progressterra.ipbandroidview.composable.PartnerBlockEvent
import com.progressterra.ipbandroidview.model.Partner

interface MainHaccpInteractor {

    fun onPartnerClick(partner: Partner)

    fun handleEvent(event: OrganizationsOverviewEvent)

    fun handleEvent(event: PartnerBlockEvent)

    fun refresh()

    class Empty : MainHaccpInteractor {
        override fun onPartnerClick(partner: Partner) = Unit
        override fun handleEvent(event: OrganizationsOverviewEvent) = Unit
        override fun handleEvent(event: PartnerBlockEvent) = Unit
        override fun refresh() = Unit
    }
}