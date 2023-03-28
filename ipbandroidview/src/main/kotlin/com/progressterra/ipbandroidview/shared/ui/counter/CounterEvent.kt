package com.progressterra.ipbandroidview.shared.ui.counter

sealed class CounterEvent(val id: String) {

    class Add(id: String) : CounterEvent(id)

    class Remove(id: String) : CounterEvent(id)
}