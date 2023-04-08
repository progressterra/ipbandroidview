package com.progressterra.ipbandroidview.widgets.pickupchoose

import com.progressterra.ipbandroidview.entities.PickUpPointInfo

sealed class PickUpChooseEvent {

    class Choose(val info: PickUpPointInfo) : PickUpChooseEvent()
}

