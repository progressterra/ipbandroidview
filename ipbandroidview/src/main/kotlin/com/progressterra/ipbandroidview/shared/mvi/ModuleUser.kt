package com.progressterra.ipbandroidview.shared.mvi

interface ModuleUser<STATE : Any> {

    fun emitModuleState(reducer: (STATE) -> STATE)

    val moduleState: STATE
}