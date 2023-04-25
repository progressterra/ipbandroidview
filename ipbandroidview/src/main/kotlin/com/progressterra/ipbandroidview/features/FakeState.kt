package com.progressterra.ipbandroidview.features

import com.progressterra.processors.IpbState

@IpbState
data class FakeState(
    val id: String
) {

    fun test() {
    }
}

fun FakeState.check() = Unit
