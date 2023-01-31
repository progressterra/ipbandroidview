package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.domain.usecase.store.FastAddToCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsByColorUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsColorsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.domain.usecase.store.SizeTableUseCase
import com.progressterra.ipbandroidview.ext.toScreenState
import com.progressterra.ipbandroidview.model.store.GoodsColor
import com.progressterra.ipbandroidview.model.store.GoodsSize
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val fastAddToCartUseCase: FastAddToCartUseCase,
    private val fastRemoveFromCartUseCase: FastRemoveFromCartUseCase,
    private val goodsByColorUseCase: GoodsByColorUseCase,
    private val goodsColorsUseCase: GoodsColorsUseCase,
    private val sizeTableUseCase: SizeTableUseCase
) : ViewModel(), ContainerHost<GoodsDetailsScreenState, GoodsDetailsEffect>,
    GoodsDetailsInteractor {

    override val container: Container<GoodsDetailsScreenState, GoodsDetailsEffect> =
        container(GoodsDetailsScreenState())

    fun setGoodsId(goodsId: String) = intent {
        reduce { state.copy(id = goodsId) }
        refresh()
    }

    override fun refresh() = intent {
        reduce { state.copy(screenState = ScreenState.LOADING) }
        var isSuccess = true
        goodsDetailsUseCase(state.id).onSuccess { reduce { state.copy(goodsDetails = it) } }
            .onFailure {
                isSuccess = false
            }
        goodsColorsUseCase(state.id).onSuccess { reduce { state.copy(colors = it) } }.onFailure {
            isSuccess = false
        }
        sizeTableUseCase(state.id).onSuccess { reduce { state.copy(sizeTable = it) } }.onFailure {
            isSuccess = false
        }
        reduce { state.copy(screenState = isSuccess.toScreenState()) }
    }

    override fun onBack() = intent {
        postSideEffect(GoodsDetailsEffect.Back)
    }

    override fun add() = intent {
        fastAddToCartUseCase(state.id).onSuccess {
            reduce { state.copy(goodsDetails = state.goodsDetails.addOne()) }
        }
    }

    override fun remove() = intent {
        fastRemoveFromCartUseCase(state.id).onSuccess {
            reduce { state.copy(goodsDetails = state.goodsDetails.removeOne()) }
        }
    }

    override fun favorite() = intent {
        modifyFavoriteUseCase(state.id, state.goodsDetails.favorite).onSuccess {
            reduce { state.copy(goodsDetails = state.goodsDetails.reverseFavorite()) }
        }
    }

    override fun chooseColor(color: GoodsColor) = intent {
        if (color.name != state.goodsDetails.color) goodsByColorUseCase(
            state.id,
            color.name
        ).onSuccess {
            reduce { state.copy(id = it) }
            refresh()
        }
    }

    override fun chooseSize(size: GoodsSize) {

    }
}