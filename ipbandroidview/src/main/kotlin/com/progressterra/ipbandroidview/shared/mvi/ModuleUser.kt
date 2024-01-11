package com.progressterra.ipbandroidview.shared.mvi

/**
 * Interface for module users, in this case, viewmodels
 */
interface ModuleUser<STATE : Any> {

    fun emitModuleState(reducer: (STATE) -> STATE)

    val moduleState: STATE
}