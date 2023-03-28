package com.progressterra.ipbandroidview.ui.mainhaccp

import com.progressterra.ipbandroidview.composable.OrganizationsOverviewEvent
import com.progressterra.ipbandroidview.composable.PartnerBlockEvent
import com.progressterra.ipbandroidview.entities.Partner

interface MainHaccpInteractor {

    fun onPartnerClick(partner: Partner)

    fun handle(event: OrganizationsOverviewEvent)

    fun handle(event: PartnerBlockEvent)

    fun refresh()

    class Empty : MainHaccpInteractor {
        override fun onPartnerClick(partner: Partner) = Unit
        override fun handle(event: OrganizationsOverviewEvent) = Unit
        override fun handle(event: PartnerBlockEvent) = Unit
        override fun refresh() = Unit
    }
}