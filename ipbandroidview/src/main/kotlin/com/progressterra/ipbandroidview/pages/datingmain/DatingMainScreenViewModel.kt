package com.progressterra.ipbandroidview.pages.datingmain

import com.progressterra.ipbandroidview.processes.dating.DeleteReadyToMeetUseCase
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.processes.location.SubscribeOnLocationUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent

class DatingMainScreenViewModel(
    private val deleteReadyToMeetUseCase: DeleteReadyToMeetUseCase,
    private val readyToMeetUseCase: DeleteReadyToMeetUseCase,
    private val subscribeOnLocationUseCase: SubscribeOnLocationUseCase,
    private val usersAroundUseCase: UsersAroundUseCase
) : UseDatingMainScreen,
    AbstractNonInputViewModel<DatingMainScreenState, DatingMainScreenEffect>() {

    override fun createInitialState() = DatingMainScreenState()

    override fun handle(event: DatingMainScreenEvent) {

    }

    override fun handle(event: BrushedSwitchEvent) {
        TODO("Not yet implemented")
    }
}