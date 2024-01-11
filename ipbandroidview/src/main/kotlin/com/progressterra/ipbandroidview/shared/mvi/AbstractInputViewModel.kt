package com.progressterra.ipbandroidview.shared.mvi

/**
 * Abstract class for viewmodels that need input parameters
 */
abstract class AbstractInputViewModel<I : Any, S : Any, E : Any> : AbstractViewModel<S, E>() {

    abstract fun setup(data: I)
}