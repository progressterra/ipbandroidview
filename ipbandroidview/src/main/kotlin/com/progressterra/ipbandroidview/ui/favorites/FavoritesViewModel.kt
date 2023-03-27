package com.progressterra.ipbandroidview.ui.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.composable.component.StoreCardComponentState
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.usecase.store.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.replaceById
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class FavoritesViewModel(
    private val favoriteGoodsUseCase: FavoriteGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<FavoritesState, FavoritesEffect>, FavoritesInteractor {

    override val container: Container<FavoritesState, FavoritesEffect> = container(FavoritesState())

    init {
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        favoriteGoodsUseCase().onSuccess {
            reduce {
                state.copy(
                    items = it, screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun onClick(storeCard: StoreCardComponentState) = intent {
        postSideEffect(FavoritesEffect.GoodsDetails(storeCard.id))
    }

    override fun favorite(storeCard: StoreCardComponentState) = intent {
        modifyFavoriteUseCase(storeCard.id, storeCard.favorite).onSuccess {
            val newList = state.items.replaceById(storeCard.reverseFavorite())
            reduce { state.copy(items = newList) }
        }
    }
}