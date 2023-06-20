package com.progressterra.ipbandroidview.widgets.pickupchoose

sealed class PickUpChooseEvent {

    class Choose(val info: PickUpPointInfo) : PickUpChooseEvent()
}

