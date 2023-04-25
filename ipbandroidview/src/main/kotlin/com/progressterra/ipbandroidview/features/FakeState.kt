package com.progressterra.ipbandroidview.features

import com.progressterra.processors.IpbState
import com.progressterra.processors.IpbSubState

@IpbState
data class FakeSubState(
    val id: String
)

data class FakeState(
    @IpbSubState val subState1: FakeSubState,
    @IpbSubState val subState2: FakeSubState
)

data class FakeStateLevel3(
    @IpbSubState val subState1: FakeState,
    @IpbSubState val subState2: FakeState
) {

    fun test() {
    }
}

