package com.progressterra.ipbandroidview.pages.pfppicker

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.dating.FetchDatingUserUseCase
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import kotlinx.coroutines.flow.collectLatest

class PfpPickerScreenViewModel(
    private val fetchDatingUserUseCase: FetchDatingUserUseCase,
    private val pickPhotoUseCase: PickPhotoUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase,
    private val makeToastUseCase: MakeToastUseCase
) :
    AbstractNonInputViewModel<PfpPickerScreenState, PfpPickerScreenEffect>(), UsePfpPickerScreen {

    override fun createInitialState() = PfpPickerScreenState()

    init {
        onBackground {
            fetchDatingUserUseCase.resultFlow.collectLatest { result ->
                result.onSuccess { user ->
                    emitState {
                        it.copy(
                            pfpPicker = it.pfpPicker.copy(url = user.avatar),
                            choose = it.choose.copy(enabled = user.avatar.isNotEmpty()),
                            screen = it.screen.copy(state = ScreenState.SUCCESS)
                        )
                    }
                }.onFailure {
                    emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
                }
            }
        }
    }

    override fun refresh() {
        onBackground {
            fetchDatingUserUseCase()
        }
    }

    override fun handle(event: PfpPickerEvent) {
        onBackground {
            pickPhotoUseCase().onSuccess { path ->
                saveAvatarUseCase(path).onSuccess {
                    emitState {
                        it.copy(
                            pfpPicker = it.pfpPicker.copy(url = path.toString()),
                            choose = it.choose.copy(enabled = true)
                        )
                    }
                    makeToastUseCase(R.string.success)
                }.onFailure {
                    makeToastUseCase(R.string.failure)
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(PfpPickerScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        if (event.id == "choose") postEffect(PfpPickerScreenEffect.Next)
        if (event.id == "skip") postEffect(PfpPickerScreenEffect.Skip)
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }
}

