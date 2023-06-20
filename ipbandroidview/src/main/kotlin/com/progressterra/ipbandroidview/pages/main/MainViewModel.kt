package com.progressterra.ipbandroidview.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.widgets.offers.FetchOffersUseCase
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class MainViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val fetchBonusesUseCase: BonusesUseCase,
    private val fetchOffersUseCase: FetchOffersUseCase,
    private val fetchGalleriesUseCase: FetchGalleriesUseCase
) : UseMain, ContainerHost<MainState, MainEvent>, ViewModel() {

    override val container: Container<MainState, MainEvent> =
        container(MainState())

    fun refresh() {
        intent {
            var isSuccess = true
            fetchBonusesUseCase().onSuccess {
                reduce { state.uBonuses(it) }
            }.onFailure {
                isSuccess = false
            }
            if (isSuccess) fetchOffersUseCase().onSuccess {
                reduce { state.uOffers(it) }
            }.onFailure {
                isSuccess = false
            }
            if (isSuccess) fetchGalleriesUseCase().onSuccess {
                val cached =
                    it.map { item -> item.uItems(item.items.cachedIn(viewModelScope)) }
                reduce { state.uRecommended(cached) }
            }.onFailure {
                isSuccess = false
            }
            reduce { state.uStateBox(ScreenState.fromBoolean(isSuccess)) }
        }
    }

    override fun handle(event: BonusesEvent) {
        intent {
            when (event) {
                is BonusesEvent.Action -> postSideEffect(MainEvent.OnBonuses)
            }
        }
    }

    override fun handle(event: StoreCardEvent) {
        intent {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is StoreCardEvent.Open -> postSideEffect(MainEvent.OnItem(event.id))
            }
        }
    }

    override fun handle(event: CounterEvent) {
        intent {
            when (event) {
                is CounterEvent.Add -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is CounterEvent.Remove -> removeFromCartUseCase(event.id).onSuccess {
                    refresh()
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
}