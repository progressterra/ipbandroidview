package com.progressterra.ipbandroidview.shared.mvi

abstract class AbstractInputViewModel<I : Any, S : Any, E : Any> : AbstractViewModel<S, E>() {

    abstract fun setup(data: I)
}