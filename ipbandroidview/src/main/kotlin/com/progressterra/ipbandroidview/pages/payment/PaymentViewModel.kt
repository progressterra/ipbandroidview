package com.progressterra.ipbandroidview.pages.payment

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.features.paymentmethod.FetchPaymentMethods
import com.progressterra.ipbandroidview.features.paymentmethod.PaymentMethodEvent
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarEvent
import com.progressterra.ipbandroidview.shared.ui.button.ButtonEvent
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
    private val fetchPaymentMethods: FetchPaymentMethods
) : ViewModel(), ContainerHost<PaymentState, PaymentEvent>, UsePayment {

    override val container = container<PaymentState, PaymentEvent>(PaymentState())

    fun refresh() = intent {
        reduce { state.updateStateBoxState(ScreenState.LOADING) }
        fetchPaymentMethods().onSuccess {
            reduce { state.updateStateBoxState(ScreenState.SUCCESS).updatePaymentMethodState(it) }
        }.onFailure {
            reduce { state.updateStateBoxState(ScreenState.ERROR) }
        }
    }

    override fun handle(event: ProshkaTopBarEvent) = intent {
        when (event) {
            is ProshkaTopBarEvent.Back -> postSideEffect(PaymentEvent.Back)
        }
    }

    override fun handle(event: ButtonEvent) = intent {
        when (event) {
            is ButtonEvent.Click -> when (event.id) {
                "confirm" -> postSideEffect(PaymentEvent.Next)
            }
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }

    override fun handle(event: TextFieldEvent) = blockingIntent {
        when (event) {
            is TextFieldEvent.TextChanged -> when (event.id) {
                "city" -> reduce { state.updateCity(event.text) }
                "home" -> reduce { state.updateHome(event.text) }
                "entrance" -> reduce { state.updateEntrance(event.text) }
                "apartment" -> reduce { state.updateApartment(event.text) }
            }
            is TextFieldEvent.Action -> Unit
            is TextFieldEvent.AdditionalAction -> when (event.id) {
                "city" -> reduce { state.updateCity("") }
                "home" -> reduce { state.updateHome("") }
                "entrance" -> reduce { state.updateEntrance("") }
                "apartment" -> reduce {
                    state.updateApartment("")
                }
            }
        }
    }

    override fun handle(event: PaymentMethodEvent) = intent {
        when (event) {
            is PaymentMethodEvent.PaymentMethodSelected -> reduce { state.updatePaymentMethod(event.paymentMethod) }
        }
    }
}