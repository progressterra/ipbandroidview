package com.progressterra.ipbandroidview.ui.goodsdetails

import androidx.lifecycle.ViewModel
import com.progressterra.ipbandroidview.core.ScreenState
import com.progressterra.ipbandroidview.processes.cart.AddToCartUseCase
import com.progressterra.ipbandroidview.processes.cart.RemoveFromCartUseCase
import com.progressterra.ipbandroidview.processes.goods.GoodsDetailsUseCase
import com.progressterra.ipbandroidview.processes.usecase.store.ModifyFavoriteUseCase
import com.progressterra.ipbandroidview.ext.toScreenState
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class GoodsDetailsViewModel(
    private val modifyFavoriteUseCase: ModifyFavoriteUseCase,
    private val goodsDetailsUseCase: GoodsDetailsUseCase,
    private val addToCartUseCase: AddToCartUseCase,
    private val removeFromCartUseCase: RemoveFromCartUseCase,
//    private val sizeTableUseCase: SizeTableUseCase
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
        goodsDetailsUseCase(state.id).onSuccess { reduce { state.copy(goodsItem = it) } }
            .onFailure {
                isSuccess = false
            }
//        sizeTableUseCase(state.id).onSuccess { reduce { state.copy(sizeTable = it) } }.onFailure {
//            isSuccess = false
//        }
        reduce { state.copy(screenState = isSuccess.toScreenState()) }
    }

    override fun onBack() = intent {
        postSideEffect(GoodsDetailsEffect.Back)
    }

    override fun add() = intent {
        addToCartUseCase(state.id).onSuccess {
            reduce { state.copy(goodsItem = state.goodsItem.addOne()) }
        }
    }

    override fun remove() = intent {
        removeFromCartUseCase(state.id).onSuccess {
            reduce { state.copy(goodsItem = state.goodsItem.removeOne()) }
        }
    }

    override fun favorite() = intent {
        modifyFavoriteUseCase(state.id, state.goodsItem.favorite).onSuccess {
            reduce { state.copy(goodsItem = state.goodsItem.reverseFavorite()) }
        }
    }

//    override fun chooseSize(size: GoodsSize) {
//
//    }
}