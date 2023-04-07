package com.progressterra.ipbandroidview.pages.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.features.proshkastorecard.ProshkaStoreCardEvent
import com.progressterra.ipbandroidview.features.proshkatopbar.ProshkaTopBarEvent
import com.progressterra.ipbandroidview.shared.ScreenState
import com.progressterra.ipbandroidview.shared.ui.counter.CounterEvent
import com.progressterra.ipbandroidview.shared.ui.statebox.StateBoxEvent
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FavoritesViewModel(
    private val favoriteGoodsUseCase: FavoriteGoodsUseCase
) : ViewModel(), ContainerHost<FavoritesState, FavoritesEvent>, UseFavorites {

    override val container: Container<FavoritesState, FavoritesEvent> = container(FavoritesState())

    fun refresh() = intent {
        reduce { state.updateScreenState(ScreenState.LOADING) }
        favoriteGoodsUseCase().onSuccess {
            reduce { state.updateScreenState(ScreenState.SUCCESS).updateItemsState(it) }
        }.onFailure {
            reduce { state.updateScreenState(ScreenState.ERROR) }
        }
    }

    override fun handle(event: CounterEvent) {
        TODO("Not yet implemented")
    }

    override fun handle(event: ProshkaStoreCardEvent) = intent {
        when (event) {
            is ProshkaStoreCardEvent.Open -> postSideEffect(FavoritesEvent.GoodsDetails(event.id))
            is ProshkaStoreCardEvent.AddToCart -> Unit
        }
    }

    override fun handle(event: ProshkaTopBarEvent) = intent {
        when (event) {
            is ProshkaTopBarEvent.Back -> Unit
        }
    }

    override fun handle(event: StateBoxEvent) = intent {
        when (event) {
            is StateBoxEvent.Refresh -> refresh()
        }
    }
}