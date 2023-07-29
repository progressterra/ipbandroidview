package com.progressterra.ipbandroidview.pages.favorites

import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.BaseViewModel
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent

class FavoritesViewModel(
    private val favoriteGoodsUseCase: FavoriteGoodsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : BaseViewModel<FavoritesState, FavoritesEvent>(), UseFavorites {

    override fun createInitialState() = FavoritesState()

    fun refresh() {
        onBackground {
            this.emitState { it.copy(stateBox = ScreenState.LOADING) }
            favoriteGoodsUseCase().onSuccess { nonCached ->
                this.emitState {
                    it.copy(
                        stateBox = ScreenState.SUCCESS,
                        items = it.items.copy(items = cachePaging(nonCached))
                    )
                }
            }.onFailure {
                this.emitState { it.copy(stateBox = ScreenState.ERROR) }
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


    override fun handle(event: StoreCardEvent) {
        onBackground {
            when (event) {
                is StoreCardEvent.Open -> postEffect(FavoritesEvent.GoodsDetails(event.id))
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        postEffect(FavoritesEvent.Back)
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}