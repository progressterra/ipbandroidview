package com.progressterra.ipbandroidview.shared.mvi

/**
 * Abstract class for viewmodels that don't need input parameters
 */
abstract class AbstractNonInputViewModel<S : Any, E : Any> : AbstractViewModel<S, E>() {

    open fun refresh() = Unit
}