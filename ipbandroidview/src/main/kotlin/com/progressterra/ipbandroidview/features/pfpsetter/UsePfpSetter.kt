package com.progressterra.ipbandroidview.features.pfpsetter

interface UsePfpSetter {

    fun handle(event: PfpSetterEvent)

    class Empty : UsePfpSetter {

        override fun handle(event: PfpSetterEvent) = Unit
    }
}