package com.progressterra.ipbandroidview.ui.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.StoreGoods
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate")
class FavoritesViewModel(
    private val favoriteGoodsUseCase: FavoriteGoodsUseCase,
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase
) : ViewModel(), ContainerHost<FavoritesState, FavoritesEffect> {

    override val container: Container<FavoritesState, FavoritesEffect> = container(FavoritesState())

    init {
        refresh()
    }

    fun favoriteSpecific(item: StoreGoods) = intent {
        modifyFavoriteUseCase.modifyFavorite(item.id, item.favorite).onSuccess {
            val newList = state.items.replaceById(item.reverseFavorite())
            reduce { state.copy(items = newList) }
        }
    }

    fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        favoriteGoodsUseCase.favoriteGoods().onSuccess {
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

    fun openDetails(item: StoreGoods) = intent {
        postSideEffect(FavoritesEffect.GoodsDetails(item.id))
    }
}