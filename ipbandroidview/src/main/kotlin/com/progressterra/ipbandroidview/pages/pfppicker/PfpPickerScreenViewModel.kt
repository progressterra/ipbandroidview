package com.progressterra.ipbandroidview.pages.pfppicker

import com.progressterra.ipbandroidview.R
import com.progressterra.ipbandroidview.features.pfppicker.PfpPickerEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.PickPhotoUseCase
import com.progressterra.ipbandroidview.processes.user.SaveAvatarUseCase
import com.progressterra.ipbandroidview.processes.utils.MakeToastUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class PfpPickerScreenViewModel(
    private val pickPhotoUseCase: PickPhotoUseCase,
    private val saveAvatarUseCase: SaveAvatarUseCase,
    private val makeToastUseCase: MakeToastUseCase
) :
    AbstractNonInputViewModel<PfpPickerScreenState, PfpPickerScreenEffect>(), UsePfpPickerScreen {

    override fun createInitialState() = PfpPickerScreenState()

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
                    refresh()
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

