package com.progressterra.ipbandroidview.pages.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.storecard.StoreCardEvent
import com.progressterra.ipbandroidview.features.topbar.TopBarEvent
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import com.progressterra.ipbandroidview.widgets.storeitems.items
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FavoritesViewModel(
    private val favoriteGoodsUseCase: FavoriteGoodsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase
) : ViewModel(), ContainerHost<FavoritesState, FavoritesEvent>, UseFavorites {

    override val container: Container<FavoritesState, FavoritesEvent> = container(FavoritesState())

    fun refresh() {
        intent {
            reduce { FavoritesState.stateBox.set(state, ScreenState.LOADING) }
            favoriteGoodsUseCase().onSuccess {
                reduce { FavoritesState.stateBox.set(state, ScreenState.SUCCESS) }
                reduce { FavoritesState.items.items.set(state, it) }
            }.onFailure {
                reduce { FavoritesState.stateBox.set(state, ScreenState.ERROR) }
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


    override fun handle(event: StoreCardEvent) {
        intent {
            when (event) {
                is StoreCardEvent.Open -> postSideEffect(FavoritesEvent.GoodsDetails(event.id))
                is StoreCardEvent.AddToCart -> addToCartUseCase(event.id).onSuccess {
                    refresh()
                }
            }
        }
    }

    override fun handle(event: TopBarEvent) {
        intent {
            postSideEffect(FavoritesEvent.Back)
        }
    }

    override fun handle(event: StateBoxEvent) {
        refresh()
    }
}