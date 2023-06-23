package com.progressterra.ipbandroidview.pages.payment

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.utils.OpenUrlUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.brushedswitch.BrushedSwitchEvent
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
            reduce { state.uStateBoxState(ScreenState.LOADING) }
            var isSuccess = true
            fetchPaymentMethods().onSuccess {
                reduce { state.uPaymentMethodState(it) }
            }.onFailure {
                isSuccess = false
            }
            fetchBonusSwitchUseCase().onSuccess {
                reduce { state.uBonusSwitch(it) }
            }.onFailure {
                isSuccess = false
            }
            fetchReceiptUseCase().onSuccess {
                reduce { state.uReceiveReceipt(it) }
            }.onFailure {
                isSuccess = false
            }
            reduce { state.uStateBoxState(ScreenState.fromBoolean(isSuccess)) }
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
            when (event) {
                is BrushedSwitchEvent.Click -> when (event.id) {
                    "useBonuses" -> reduce { state.reverseBonusSwitch() }
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            refresh()
        }
    }

    override fun handle(event: LinkTextEvent) {
        intent {
            when (event) {
                is LinkTextEvent.Click -> openUrlUseCase(event.url)
            }
        }
    }

    override fun handle(event: PaymentMethodEvent) {
        intent {
            when (event) {
                is PaymentMethodEvent.Select -> reduce { state.uPaymentMethod(event.type) }
            }
        }
    }
}