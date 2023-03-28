package com.progressterra.ipbandroidview.shared.ui.counter

interface UseCounter {

    fun handle(event: CounterEvent)

    class Empty : UseCounter {
        override fun handle(event: CounterEvent) = Unit
    }
}