package com.progressterra.ipbandroidview.shared.mvi

abstract class AbstractNonInputViewModel<S : Any, E : Any> : AbstractViewModel<S, E>() {

    open fun refresh() = Unit
}