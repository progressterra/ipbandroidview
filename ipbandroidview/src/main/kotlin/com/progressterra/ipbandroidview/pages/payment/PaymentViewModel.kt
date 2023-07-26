package com.progressterra.ipbandroidview.pages.payment

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bonusswitch.useBonuses
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodEvent
import com.progressterra.ipbandroidview.features.paymentmethod.selectedPaymentMethod
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.enabled
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
import com.progressterra.ipbandroidview.shared.ui.linktext.LinkTextEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class PaymentViewModel(
    private val fetchPaymentMethods: FetchPaymentMethods,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val openUrlUseCase: OpenUrlUseCase,
    private val fetchReceiptUseCase: FetchReceiptUseCase,
    private val fetchBonusSwitchUseCase: FetchBonusSwitchUseCase
) : ViewModel(), ContainerHost<PaymentState, PaymentEvent>, UsePayment {

    override val container = container<PaymentState, PaymentEvent>(PaymentState())

    fun refresh() {
        intent {
            reduce { PaymentState.screenState.set(state, ScreenState.LOADING) }
            var isSuccess = true
            fetchPaymentMethods().onSuccess {
                reduce { PaymentState.paymentMethod.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusSwitchUseCase().onSuccess {
                reduce { PaymentState.bonusSwitch.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            fetchReceiptUseCase().onSuccess {
                reduce { PaymentState.receipt.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            reduce { PaymentState.screenState.set(state, isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(PaymentEvent.Back)
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event.id) {
                "pay" -> {
                    confirmOrderUseCase().onSuccess {
                        postSideEffect(PaymentEvent.Next(it))
                    }
                }
            }
        }
    }

    override fun handle(event: BrushedSwitchEvent) {
        intent {
            when (event.id) {
                "useBonuses" -> reduce {
                    PaymentState.bonusSwitch.useBonuses.enabled.set(
                        state,
                        !state.bonusSwitch.useBonuses.enabled
                    )
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }

    override fun handle(event: LinkTextEvent) {
        intent {
            openUrlUseCase(event.url)
        }
    }

    override fun handle(event: PaymentMethodEvent) {
        intent {
            reduce { PaymentState.paymentMethod.selectedPaymentMethod.set(state, event.type) }
        }
    }
}
