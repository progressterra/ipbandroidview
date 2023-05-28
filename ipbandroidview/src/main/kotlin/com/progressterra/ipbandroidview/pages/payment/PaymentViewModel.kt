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
import com.progressterra.ipbandroidview.shared.ui.textfield.TextFieldEvent
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.annotation.OrbitExperimental
import org.orbitmvi.orbit.syntax.simple.blockingIntent
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@OptIn(OrbitExperimental::class)
class PaymentViewModel(
    private val fetchPaymentMethods: FetchPaymentMethods,
    private val confirmOrderUseCase: ConfirmOrderUseCase,
    private val openUrlUseCase: OpenUrlUseCase
) : ViewModel(), ContainerHost<PaymentState, PaymentEvent>, UsePayment {

    override val container = container<PaymentState, PaymentEvent>(PaymentState())

    fun refresh() {
        intent {
            reduce { state.uStateBoxState(ScreenState.LOADING) }
            fetchPaymentMethods().onSuccess {
                reduce { state.uStateBoxState(ScreenState.SUCCESS).uPaymentMethodState(it) }
            }.onFailure {
                reduce { state.uStateBoxState(ScreenState.ERROR) }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            when (event) {
                is TopBarEvent.Back -> postSideEffect(PaymentEvent.Back)
            }
        }
    }

    override fun handle(event: ButtonEvent) {
        intent {
            when (event) {
                is ButtonEvent.Click -> when (event.id) {
                    "confirm" -> confirmOrderUseCase().onSuccess {
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
                    "receiveReceipt" -> reduce { state.reverseReceiveReceipt() }
                }
            }
        }
    }

    override fun handle(event: StateBoxEvent) {
        intent {
            when (event) {
                is StateBoxEvent.Refresh -> refresh()
            }
        }
    }

    override fun handle(event: TextFieldEvent) {
        blockingIntent {
            when (event) {
                is TextFieldEvent.Action -> Unit
                is TextFieldEvent.AdditionalAction -> Unit
                is TextFieldEvent.TextChanged -> when (event.id) {
                    "email" -> reduce { state.uEmail(event.text) }
                }
            }
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