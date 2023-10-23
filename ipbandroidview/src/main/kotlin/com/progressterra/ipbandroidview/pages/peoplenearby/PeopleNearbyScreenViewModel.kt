package com.progressterra.ipbandroidview.widgets.peoplenearby

import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.UsersAroundUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel

class PeopleNearbyScreenViewModel(
    private val usersAroundUseCase: UsersAroundUseCase,
) :
    AbstractNonInputViewModel<PeopleNearbyScreenState, PeopleNearbyScreenEffect>(),
    UsePeopleNearbyScreen {

    init {
        onBackground {
            usersAroundUseCase.resultFlow.collect { result ->
                result.onSuccess { newUsers ->
                    emitState { it.copy(items = newUsers) }
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            usersAroundUseCase()
        }
    }

    override fun handle(event: TopBarEvent) = Unit

    override fun createInitialState() = PeopleNearbyScreenState()

    override fun handle(event: PeopleNearbyScreenEvent) {
        postEffect(PeopleNearbyScreenEffect.OnProfile(event.user))
    }
}