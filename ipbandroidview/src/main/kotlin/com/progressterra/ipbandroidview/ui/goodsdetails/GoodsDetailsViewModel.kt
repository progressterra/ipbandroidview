package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.FastAddToCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

@Suppress("unused", "MemberVisibilityCanBePrivate", "UNUSED_PARAMETER")
class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val fastAddToCartUseCase: FastAddToCartUseCase,
    private val fastRemoveFromCartUseCase: FastRemoveFromCartUseCase
) : ViewModel(),
    ContainerHost<GoodsDetailsScreenState, GoodsDetailsEffect> {

    override val container: Container<GoodsDetailsScreenState, GoodsDetailsEffect> =
        container(GoodsDetailsScreenState())

    fun setGoodsId(goodsId: String) = intent {
        reduce { state.copy(id = goodsId, screenState = ScreenState.LOADING) }
        goodsDetailsUseCase.goodsDetails(goodsId).onSuccess {
            reduce { state.copy(screenState = ScreenState.SUCCESS, goodsDetails = it) }
        }.onFailure {
            reduce { state.copy(screenState = ScreenState.ERROR) }
        }
    }

    fun back() = intent {
        postSideEffect(GoodsDetailsEffect.Back)
    }

    fun add() = intent {
        fastAddToCartUseCase.add(state.id).onSuccess {
            reduce { state.copy(goodsDetails = state.goodsDetails.addOne()) }
        }
    }

    fun remove() = intent {
        fastRemoveFromCartUseCase.remove(state.id).onSuccess {
            reduce { state.copy(goodsDetails = state.goodsDetails.removeOne()) }
        }
    }

    fun favorite() = intent {
        modifyFavoriteUseCase.modifyFavorite(state.id, state.favorite).onSuccess {
            reduce { state.copy(goodsDetails = state.goodsDetails.reverseFavorite()) }
        }
    }

    fun color(color: GoodsColor) {

    }

    fun size(size: GoodsSize) {

    }

    fun sizeTable() {

    }
}