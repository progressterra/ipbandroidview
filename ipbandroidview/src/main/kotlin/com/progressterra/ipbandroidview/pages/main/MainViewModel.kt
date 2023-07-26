package com.progressterra.ipbandroidview.pages.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase
import com.progressterra.ipbandroidview.widgets.galleries.GalleriesState
import com.progressterra.ipbandroidview.widgets.galleries.items
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
    private val fetchGalleriesUseCase: FetchGalleriesUseCase
) : UseMain, ContainerHost<MainState, MainEvent>, ViewModel() {

    override val container: Container<MainState, MainEvent> =
        container(MainState())

    fun refresh() {
        intent {
            var isSuccess = true
            fetchBonusesUseCase().onSuccess {
                reduce { MainState.bonuses.set(state, it) }
            }.onFailure {
                isSuccess = false
            }
            if (isSuccess) fetchGalleriesUseCase().onSuccess {
                val cachedGalleries = it.map { gallery ->
                    val cached = gallery.items.cachedIn(viewModelScope)
                    GalleriesState.items.set(gallery, cached)
                }
                reduce { MainState.recommended.set(state, cachedGalleries) }
            }.onFailure {
                isSuccess = false
            }

            reduce { MainState.stateBox.set(state, isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: BonusesEvent) {
        intent {
            postSideEffect(MainEvent.OnBonuses)
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
        refresh()
    }
}