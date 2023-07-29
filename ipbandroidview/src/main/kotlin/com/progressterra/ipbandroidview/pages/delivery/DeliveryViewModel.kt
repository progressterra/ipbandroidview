package com.progressterra.ipbandroidview.pages.delivery

import com.progressterra.ipbandroidview.entities.AddressUI
import com.progressterra.ipbandroidview.features.addresssuggestions.AddressSuggestionsEvent
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionUI
import com.progressterra.ipbandroidview.features.addresssuggestions.SuggestionsUseCase
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.UserData
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent

class DeliveryViewModel(
    private val addDeliveryToCartUseCase: AddDeliveryToCartUseCase,
    private val fetchShippingAddressUseCase: FetchShippingAddressUseCase,
    private val suggestionsUse: SuggestionsUseCase,
    private val commentUseCase: CommentUseCase
) : BaseViewModel<DeliveryState, DeliveryEvent>(), UseDelivery {

    override val initialState = DeliveryState()

    fun refresh() {
        onBackground {
            emitState { it.copy(screenState = ScreenState.LOADING) }
            fetchShippingAddressUseCase().onSuccess {
                emitState { it.copy(screenState = ScreenState.SUCCESS) }
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
                emitState { it.copy(screenState = ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: TopBarEvent) {
        postEffect(DeliveryEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "confirm" -> {
                    var isSuccess = true
                    addDeliveryToCartUseCase(state.value.suggestion).onFailure {
                        isSuccess = false
                    }
                    commentUseCase(state.value.commentary.text).onFailure {
                        isSuccess = false
                    }
                    if (isSuccess) {
                        postEffect(DeliveryEvent.Next)
                    }
                }
            }
        }
    }

    override fun handle(event: AddressSuggestionsEvent) {
        fastEmitState {
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
                "address" -> fastEmitState {
                    it.copy(
                        deliveryPicker = it.deliveryPicker.copy(
                            address = it.deliveryPicker.address.copy(
                                text = event.text
                            )
                        )
                    )
                }

                "commentary" -> fastEmitState {
                    it.copy(commentary = it.commentary.copy(text = event.text))
                }
            }

            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "address" -> fastEmitState {
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
        fastEmitState { it.copy(address = AddressUI(), suggestion = SuggestionUI()) }
        checkValid()
        updateSuggestions()
    }

    private fun updateSuggestions() {
        onBackground {
            suggestionsUse(state.value.deliveryPicker.address.text).onSuccess { suggestions ->
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
        fastEmitState {
            it.copy(confirm = it.confirm.copy(enabled = !state.value.address.isEmpty() || !state.value.suggestion.isEmpty()))
        }
    }
}