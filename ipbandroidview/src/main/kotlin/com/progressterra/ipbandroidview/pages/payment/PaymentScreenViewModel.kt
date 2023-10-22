package com.progressterra.ipbandroidview.pages.payment

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.ConfirmOrderUseCase
import com.progressterra.ipbandroidview.processes.FetchBonusSwitchUseCase
import com.progressterra.ipbandroidview.processes.FetchReceiptUseCase
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.mvi.AbstractNonInputViewModel
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.statecolumn.StateColumnEvent

class PaymentScreenViewModel(
    private val fetchPaymentMethods: FetchPaymentMethods,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val openUrlUseCase: OpenUrlUseCase,
    private val fetchReceiptUseCase: FetchReceiptUseCase,
    private val fetchBonusSwitchUseCase: FetchBonusSwitchUseCase
) : AbstractNonInputViewModel<PaymentScreenState, PaymentScreenEffect>(), UsePaymentScreen {

    override fun createInitialState() = PaymentScreenState()

    override fun refresh() {
        onBackground {
            emitState { createInitialState() }
            var isSuccess = true
            fetchPaymentMethods().onSuccess { paymentMethods ->
                emitState {
                    it.copy(paymentMethod = paymentMethods)
                }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusSwitchUseCase().onSuccess { bonusSwitch ->
                emitState {
                    it.copy(bonusSwitch = bonusSwitch)
                }
            }.onFailure {
                isSuccess = false
            }
            fetchReceiptUseCase().onSuccess { receipt ->
                emitState {
                    it.copy(receipt = receipt)
                }
            }.onFailure {
                isSuccess = false
            }
            emitState {
                it.copy(screen = it.screen.copy(state = isSuccess.toScreenState()))
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(PaymentScreenEffect.Back)
    }

    override fun handle(event: ButtonEvent) {
        onBackground {
            when (event.id) {
                "pay" -> {
                    emitState { it.copy(receipt = it.receipt.copy(pay = it.receipt.pay.copy(enabled = false))) }
                    confirmOrderUseCase().onSuccess { orderId ->
                        postEffect(PaymentScreenEffect.Next(orderId))
                    }
                    emitState { it.copy(receipt = it.receipt.copy(pay = it.receipt.pay.copy(enabled = true))) }
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

    override fun handle(event: StateColumnEvent) {
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
