package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class PaymentViewModel(
    private val fetchPaymentMethods: FetchPaymentMethods,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val openUrlUseCase: OpenUrlUseCase,
    private val fetchReceiptUseCase: FetchReceiptUseCase,
    private val fetchBonusSwitchUseCase: FetchBonusSwitchUseCase
) : BaseViewModel<PaymentState, PaymentEvent>(), UsePayment {

    override fun createInitialState() = PaymentState()

    fun refresh() {
        onBackground {
            this.emitState {
                it.copy(screenState = ScreenState.LOADING)
            }
            var isSuccess = true
            fetchPaymentMethods().onSuccess { paymentMethods ->
                this.emitState {
                    it.copy(paymentMethod = paymentMethods)
                }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusSwitchUseCase().onSuccess { bonusSwitch ->
                this.emitState {
                    it.copy(bonusSwitch = bonusSwitch)
                }
            }.onFailure {
                isSuccess = false
            }
            fetchReceiptUseCase().onSuccess { receipt ->
                this.emitState {
                    it.copy(receipt = receipt)
                }
            }.onFailure {
                isSuccess = false
            }
            this.emitState {
                it.copy(screenState = isSuccess.toScreenState())
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(PaymentEvent.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "pay" -> {
                    confirmOrderUseCase().onSuccess { orderId ->
                        postEffect(PaymentEvent.Next(orderId))
                    }
                }
            }
        }
    }

    override fun handle(event: BrushedSwitchEvent) {
        when (event.id) {
            "useBonuses" -> emitState {
                it.copy(
                    bonusSwitch = it.bonusSwitch.copy(
                        useBonuses = it.bonusSwitch.useBonuses.copy(
                            enabled = !it.bonusSwitch.useBonuses.enabled
                        )
                    )
                )
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: LinkTextEvent) {
        onBackground {
            openUrlUseCase(event.url)
        }
    }

    override fun handle(event: PaymentMethodEvent) {
        emitState {
            it.copy(paymentMethod = it.paymentMethod.copy(selectedPaymentMethod = event.type))
        }
    }
}
