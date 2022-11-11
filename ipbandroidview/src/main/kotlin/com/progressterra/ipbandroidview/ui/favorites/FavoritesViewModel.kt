package com.progressterra.ipbandroidview.ui.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.usecase.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.replaceById
import com.progressterra.ipbandroidview.model.Goods
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

    override fun favoriteSpecific(item: Goods) = intent {
        modifyFavoriteUseCase.modifyFavorite(item.id, item.favorite).onSuccess {
            val newGoods = item.copy(favorite = !item.favorite)
            val newItems = state.items.replaceById(newGoods)
            reduce {
                state.copy(items = newItems)
            }
        }
    }

    override fun refresh() = intent {
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

    override fun goodsDetails(goods: Goods) = intent {
        postSideEffect(FavoritesEffect.GoodsDetails(goods))
    }
}