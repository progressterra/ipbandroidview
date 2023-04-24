package com.progressterra.ipbandroidview.features.trace

interface UseTrace {

    fun handle(event: TraceEvent)

    class Empty : UseTrace {

        override fun handle(event: TraceEvent) = Unit
    }
}