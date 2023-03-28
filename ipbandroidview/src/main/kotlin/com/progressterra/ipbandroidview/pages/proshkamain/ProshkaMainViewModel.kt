package com.progressterra.ipbandroidview.pages.proshkamain

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.proshkabonuses.ProshkaBonusesEvent
import com.progressterra.ipbandroidview.features.proshkaoffer.ProshkaOfferEvent
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardEvent
import com.progressterra.ipbandroidview.processes.usecase.bonus.ProshkaBonusesUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.FastAddToCartUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ui.StateBoxEvent
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class ProshkaMainViewModel(
    private val addToCartUseCase: FastAddToCartUseCase,
    private val removeFromCartUseCase: FastRemoveFromCartUseCase,
    private val fetchBonusesUseCase: ProshkaBonusesUseCase
) : UseProshkaMain, ContainerHost<ProshkaMainState, ProshkaMainEvent>, ViewModel() {

    override val container: Container<ProshkaMainState, ProshkaMainEvent> =
        container(ProshkaMainState())

    fun refresh() = intent {
        var isSuccess = true
        fetchBonusesUseCase().onSuccess {
            reduce { state.updateBonuses(it) }
        }.onFailure {
            isSuccess = false
        }

    }

    override fun handle(event: ProshkaBonusesEvent) = intent {
        when (event) {
            is ProshkaBonusesEvent.Action -> postSideEffect(ProshkaMainEvent.OnBonuses)
            is ProshkaBonusesEvent.Reverse -> reduce { state.reverseBonuses() }
        }
    }

    override fun handle(event: ProshkaOfferEvent) = intent {
        when (event) {
            is ProshkaOfferEvent.Clicked -> postSideEffect(ProshkaMainEvent.OnOffer(event.id))
        }
    }

    override fun handle(event: ProshkaStoreCardEvent) = intent {
        when (event) {
            is ProshkaStoreCardEvent.AddToCart -> addToCartUseCase(event.id)
            is ProshkaStoreCardEvent.Open -> postSideEffect(ProshkaMainEvent.OnItem(event.id))
        }
    }

    override fun handle(event: CounterEvent) = intent {
        when (event) {
            is CounterEvent.Add -> addToCartUseCase(event.id)
            is CounterEvent.Remove -> removeFromCartUseCase(event.id)
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}