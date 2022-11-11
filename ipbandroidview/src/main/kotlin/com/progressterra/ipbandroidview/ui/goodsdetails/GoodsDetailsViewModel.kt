package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.domain.usecase.FastAddToCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.FastRemoveFromCartUseCase
import com.progressterra.ipbandroidview.domain.usecase.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.model.Goods
import com.progressterra.ipbandroidview.model.GoodsColor
import com.progressterra.ipbandroidview.model.GoodsSize
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

//TODO BY ID request all details

class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val fastAddToCartUseCase: FastAddToCartUseCase,
    private val fastRemoveFromCartUseCase: FastRemoveFromCartUseCase
) : ViewModel(),
    ContainerHost<GoodsDetailsScreenState, GoodsDetailsEffect>, GoodsDetailsInteractor {

    override val container: Container<GoodsDetailsScreenState, GoodsDetailsEffect> =
        container(GoodsDetailsScreenState())

    @Suppress("unused")
    fun setGoods(goods: Goods) = intent {
        reduce { GoodsDetailsScreenState(goods) }
    }

    override fun back() = intent {
        postSideEffect(GoodsDetailsEffect.Back)
    }

    override fun add() = {

    }

    override fun remove() {

    }

    override fun favorite() = intent {
        modifyFavoriteUseCase.modifyFavorite(state.id, state.favorite)
            .onSuccess { reduce { state.copy(favorite = !state.favorite) } }
    }

    override fun color(color: GoodsColor) {

    }

    override fun size(size: GoodsSize) {

    }

    override fun sizeTable() {

    }
}