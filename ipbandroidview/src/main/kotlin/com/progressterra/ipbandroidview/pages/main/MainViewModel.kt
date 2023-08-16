package com.progressterra.ipbandroidview.pages.main

import com.progressterra.ipbandroidview.entities.toScreenState
import com.progressterra.ipbandroidview.features.bonuses.BonusesEvent
import com.progressterra.ipbandroidview.features.bonuses.BonusesUseCase
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.galleries.FetchGalleriesUseCase

class MainViewModel(
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
    private val fetchBonusesUseCase: BonusesUseCase,
    private val fetchGalleriesUseCase: FetchGalleriesUseCase
) : UseMain, BaseViewModel<MainState, MainEvent>() {

    override fun createInitialState() = MainState()

    fun refresh() {
        onBackground {
            emitState { it.copy(stateBox = ScreenState.LOADING) }
            var isSuccess = true
            fetchBonusesUseCase().onSuccess { bonuses ->
                emitState { it.copy(bonuses = bonuses) }
            }.onFailure {
                isSuccess = false
            }
            if (isSuccess) fetchGalleriesUseCase().onSuccess { galleries ->
                val cachedGalleries = galleries.map { gallery ->
                    gallery.copy(items = cachePaging(gallery.items))
                }
                emitState { it.copy(recommended = cachedGalleries) }
            }.onFailure {
                isSuccess = false
            }
            emitState { it.copy(stateBox = isSuccess.toScreenState()) }
        }
    }

    override fun handle(event: BonusesEvent) {
        postEffect(MainEvent.OnBonuses)
    }

    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }

                is StoreCardEvent.Open -> postEffect(MainEvent.OnItem(event.id))
            }
        }
    }

    override fun handle(event: CounterEvent) {
        onBackground {
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