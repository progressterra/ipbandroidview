package com.progressterra.ipbandroidview.core

interface ComponentEvent

interface ComponentEventHandler<T : ComponentEvent> {
    
    fun handleEvent(event: T)
}