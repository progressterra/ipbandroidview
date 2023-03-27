package com.progressterra.ipbandroidview.core

interface ComponentEvent

interface ComponentEventHandler<T : ComponentEvent> {
    
    fun handle(event: T)
}