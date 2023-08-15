package com.progressterra.ipbandroidview.pages.bankcarddetails

import com.progressterra.ipbandroidview.features.documentphoto.DocumentPhotoEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class BankCardDetailsScreenViewModel(
    private val fetchCardTemplateUseCase: FetchCardTemplateUseCase
) : UseBankCardDetailsScreen,
    BaseViewModel<BankCardDetailsScreenState, BankCardDetailsScreenEvent>() {

    override fun createInitialState() = BankCardDetailsScreenState()

    fun setup(newState: BankCardDetailsScreenState) {
        if (newState.isEmpty()) {
            refresh()
        } else {
            emitState { newState.copy(screen = ScreenState.SUCCESS) }
        }
    }

    fun refresh() {
        onBackground {
            emitState { it.copy(screen = ScreenState.LOADING) }
            fetchCardTemplateUseCase().onSuccess { newState ->
                emitState { newState.copy(screen = ScreenState.SUCCESS) }
            }.onFailure { emitState { it.copy(screen = ScreenState.ERROR) } }
        }
    }

    override fun handle(event: DocumentPhotoEvent) {

    }

    override fun handle(event: TopBarEvent) {
        postEffect(BankCardDetailsScreenEvent.Back)
    }

    override fun handle(event: ButtonEvent) {

    }

    override fun handle(event: TextFieldEvent) {

    }
}