package com.progressterra.ipbandroidview.ui.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.store.StoreGoods
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

    override fun favoriteSpecific(goods: StoreGoods) = intent {
        modifyFavoriteUseCase(goods.id, goods.favorite).onSuccess {
            val newList = state.items.replaceById(goods.reverseFavorite())
            reduce { state.copy(items = newList) }
        }
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        favoriteGoodsUseCase().onSuccess {
            reduce {
                state.copy(
                    items = it,
                    screenState = ScreenState.SUCCESS
                )
            }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    override fun openDetails(goods: StoreGoods) = intent {
        postSideEffect(FavoritesEffect.GoodsDetails(goods.id))
    }
}