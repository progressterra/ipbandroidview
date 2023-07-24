package com.progressterra.ipbandroidview.pages.delivery

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsEvent
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionsUseCase
import com.progressterra.ipbandroidview.features.addresssuggestions.isVisible
import com.progressterra.ipbandroidview.features.addresssuggestions.suggestions
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.button.enabled
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.text
import com.progressterra.ipbandroidview.widgets.deliverypicker.address
import com.progressterra.ipbandroidview.widgets.deliverypicker.suggestions
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class DeliveryViewModel(
    private val addDeliveryToCartUseCase: AddDeliveryToCartUseCase,
    private val fetchShippingAddressUseCase: FetchShippingAddressUseCase,
    private val suggestionsUse: SuggestionsUseCase,
    private val commentUseCase: CommentUseCase
) : ViewModel(), ContainerHost<DeliveryState, DeliveryEvent>, UseDelivery {

    override val container = container<DeliveryState, DeliveryEvent>(DeliveryState())

    fun refresh() {
        intent {
            reduce { DeliveryState.screenState.set(state, ScreenState.LOADING) }
            fetchShippingAddressUseCase().onSuccess {
                reduce { DeliveryState.screenState.set(state, ScreenState.SUCCESS) }
                if (!UserData.shippingAddress.isEmpty()) {
                    reduce { DeliveryState.address.set(state, UserData.shippingAddress) }
                    reduce {
                        DeliveryState.deliveryPicker.address.text.set(
                            state,
                            UserData.shippingAddress.printAddress()
                        )
                    }
                }
                checkValid()
            }.onFailure {
                reduce { DeliveryState.screenState.set(state, ScreenState.ERROR) }
            }

        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
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
                    addDeliveryToCartUseCase(state.suggestion).onFailure {
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
            reduce { DeliveryState.suggestion.set(state, event.suggestion) }
            reduce { DeliveryState.address.set(state, AddressUI()) }
            reduce {
                DeliveryState.deliveryPicker.address.text.set(
                    state,
                    event.suggestion.previewOfSuggestion
                )
            }
            reduce { DeliveryState.deliveryPicker.suggestions.isVisible.set(state, false) }
            checkValid()
        }
    }

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.TextChanged -> when (event.id) {
                    "address" -> reduce {
                        DeliveryState.deliveryPicker.address.text.set(state, event.text)
                    }

                    "commentary" -> reduce { DeliveryState.commentary.text.set(state, event.text) }
                }

                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> when (event.id) {
                    "address" -> reduce {
                        DeliveryState.deliveryPicker.address.text.set(state, "")
                    }
                }
            }
            reduce { DeliveryState.address.set(state, AddressUI()) }
            reduce { DeliveryState.suggestion.set(state, SuggestionUI()) }
            checkValid()
            updateSuggestions()
        }
    }

    private fun updateSuggestions() {
        intent {
            suggestionsUse(state.deliveryPicker.address.text).onSuccess {
                reduce { DeliveryState.deliveryPicker.suggestions.isVisible.set(state, true) }
                reduce { DeliveryState.deliveryPicker.suggestions.suggestions.set(state, it) }
            }
        }
    }

    private fun checkValid() {
        intent {
            reduce {
                DeliveryState.confirm.enabled.set(
                    state,
                    !state.address.isEmpty() || !state.suggestion.isEmpty()
                )
            }
        }
    }
}