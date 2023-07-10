package com.progressterra.ipbandroidview.pages.delivery

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsEvent
import com.progressterra.ipbandroidview.features.addresssuggestions.ChooseSuggestionUseCase
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionsUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.user.SaveAddressUseCase
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class DeliveryViewModel(
    private val saveAddressUseCase: SaveAddressUseCase,
    private val suggestionsUse: SuggestionsUseCase,
    private val chooseSuggestionUseCase: ChooseSuggestionUseCase,
    private val commentUseCase: CommentUseCase
) : ViewModel(), ContainerHost<DeliveryState, DeliveryEvent>, UseDelivery {

    override val container = container<DeliveryState, DeliveryEvent>(DeliveryState())

    fun refresh() {
        intent {
            reduce {
                if (UserData.address.isEmpty()) {
                    state.uCommentaryText("")
                } else {
                    state.uDeliveryPickerAddressText(UserData.address.printAddress())
                        .uCommentaryText("")
                        .uAddress(UserData.address)
                }
            }
            checkValid()
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(DeliveryEvent.Back)
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "confirm" -> {
                    var isSuccess = true
                    saveAddressUseCase(state.address!!).onFailure {
                        isSuccess = false
                    }
                    commentUseCase(state.commentary.text).onFailure {
                        isSuccess = false
                    }
                    if (isSuccess) {
                        postSideEffect(DeliveryEvent.Next)
                    }
                }
            }
        }
    }

    override fun handle(event: AddressSuggestionsEvent) {
        intent {
            chooseSuggestionUseCase(event.suggestion).onSuccess {
                reduce { state.uAddress(it).uSuggestionsVisible(false) }
                checkValid()
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.TextChanged -> when (event.id) {
                    "address" -> reduce { state.uDeliveryPickerAddressText(event.text) }
                    "commentary" -> reduce { state.uCommentaryText(event.text) }
                }

                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "address" -> reduce { state.uDeliveryPickerAddressText("") }
                }
            }
            reduce { state.uAddress(null) }
            checkValid()
            updateSuggestions()
        }
    }

    private fun updateSuggestions() {
        intent {
            suggestionsUse(state.deliveryPicker.address.text).onSuccess {
                reduce { state.uSuggestions(it).uSuggestionsVisible(true) }
            }
        }
    }

    private fun checkValid() {
        intent {
            reduce { state.uConfirmEnabled(state.address != null) }
        }
    }
}