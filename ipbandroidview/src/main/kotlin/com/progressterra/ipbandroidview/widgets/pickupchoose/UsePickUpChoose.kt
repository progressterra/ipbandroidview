package com.progressterra.ipbandroidview.widgets.pickupchoose

interface UsePickUpChoose {

    fun handle(event: PickUpChooseEvent)

    class Empty : UsePickUpChoose {

        override fun handle(event: PickUpChooseEvent) = Unit
    }
}