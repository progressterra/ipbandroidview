package com.progressterra.ipbandroidview.ui.favorites

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.FavoriteGoodsUseCase
import com.progressterra.ipbandroidview.domain.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.dto.Goods
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

    override fun favorite(goodsId: String, favorite: Boolean) = intent {
        modifyFavoriteUseCase.modifyFavorite(goodsId, favorite).onSuccess { refresh() }
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