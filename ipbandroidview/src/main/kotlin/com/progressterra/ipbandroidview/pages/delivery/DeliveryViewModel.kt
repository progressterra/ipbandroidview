package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsEvent
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionsUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.ScreenState
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class DeliveryViewModel(
    private val addDeliveryToCartUseCase: AddDeliveryToCartUseCase,
    private val fetchShippingAddressUseCase: FetchShippingAddressUseCase,
    private val suggestionsUse: SuggestionsUseCase,
    private val commentUseCase: CommentUseCase
) : AbstractNonInputViewModel<DeliveryScreenState, DeliveryScreenEffect>(), UseDelivery {

    override fun createInitialState() = DeliveryScreenState()

    override fun refresh() {
        onBackground {
            emitState { it.copy(screen = it.screen.copy(state = ScreenState.LOADING)) }
            fetchShippingAddressUseCase().onSuccess {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.SUCCESS)) }
                if (!UserData.shippingAddress.isEmpty()) {
                    emitState {
                        it.copy(
                            address = UserData.shippingAddress,
                            deliveryPicker = it.deliveryPicker.copy(
                                address = it.deliveryPicker.address.copy(text = UserData.shippingAddress.printAddress())
                            )
                        )
                    }
                }
                checkValid()
            }.onFailure {
                emitState { it.copy(screen = it.screen.copy(state = ScreenState.ERROR)) }
            }
        }
    }

    override fun handle(event: StateColumnEvent) {
        refresh()
    }

    override fun handle(event: TopBarEvent) {
        postEffect(DeliveryScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "confirm" -> {
                    var isSuccess = true
                    addDeliveryToCartUseCase(currentState.suggestion).onFailure {
                        isSuccess = false
                    }
                    commentUseCase(currentState.commentary.text).onFailure {
                        isSuccess = false
                    }
                    if (isSuccess) {
                        postEffect(DeliveryScreenEffect.Next)
                    }
                }
            }
        }
    }

    override fun handle(event: AddressSuggestionsEvent) {
        emitState {
            it.copy(
                suggestion = event.suggestion,
                address = AddressUI(),
                deliveryPicker = it.deliveryPicker.copy(
                    address = it.deliveryPicker.address.copy(text = event.suggestion.previewOfSuggestion),
                    suggestions = it.deliveryPicker.suggestions.copy(isVisible = false)
                )
            )
        }
        checkValid()
    }

    override fun handle(event: TextFieldEvent) {
        when (event) {
            is TextFieldEvent.TextChanged -> when (event.id) {
                "address" -> emitState {
                    it.copy(
                        deliveryPicker = it.deliveryPicker.copy(
                            address = it.deliveryPicker.address.copy(
                                text = event.text
                            )
                        )
                    )
                }

                "commentary" -> emitState {
                    it.copy(commentary = it.commentary.copy(text = event.text))
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "address" -> emitState {
                    it.copy(
                        deliveryPicker = it.deliveryPicker.copy(
                            address = it.deliveryPicker.address.copy(
                                text = ""
                            )
                        )
                    )
                }
            }
        }
        emitState { it.copy(address = AddressUI(), suggestion = SuggestionUI()) }
        checkValid()
        updateSuggestions()
    }

    private fun updateSuggestions() {
        onBackground {
            suggestionsUse(currentState.deliveryPicker.address.text).onSuccess { suggestions ->
                emitState {
                    it.copy(
                        deliveryPicker = it.deliveryPicker.copy(
                            suggestions = it.deliveryPicker.suggestions.copy(
                                isVisible = true,
                                suggestions = suggestions
                            )
                        )
                    )
                }
            }
        }
    }

    private fun checkValid() {
        emitState {
            it.copy(confirm = it.confirm.copy(enabled = !currentState.address.isEmpty() || !currentState.suggestion.isEmpty()))
        }
    }
}