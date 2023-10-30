package com.progressterra.ipbandroidview.pages.pfppicker

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.FetchAvatarUseCase
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class PfpPickerScreenViewModel(
    private val fetchAvatarUseCase: FetchAvatarUseCase,
    private val pickPhotoUseCase: PickPhotoUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase,
    private val makeToastUseCase: MakeToastUseCase
) :
    AbstractNonInputViewModel<PfpPickerScreenState, PfpPickerScreenEffect>(), UsePfpPickerScreen {

    override fun createInitialState() = PfpPickerScreenState()

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchAvatarUseCase().onSuccess { url ->
                emitState {
                    it.copy(
                        screen = it.screen.copy(state = ScreenState.SUCCESS),
                        pfpPicker = it.pfpPicker.copy(url = url),
                        choose = it.choose.copy(enabled = url.isNotEmpty())
                    )
                }
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
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

